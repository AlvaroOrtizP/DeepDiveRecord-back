package com.record.DeepDiveRecord.infrastructure.adapter.repository.wind_conditions;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WindConditionsCustomRepositoryImpl implements WindConditionsCustomRepository {

    @PersistenceContext
    EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(WindConditionsCustomRepositoryImpl.class);

    @Override
    public Page<WindConditionsEntity> customWindConditionsSearch(Integer fromYear, Integer fromMonth, Integer fromDay,
                                                                 Integer toYear, Integer toMonth, Integer toDay,
                                                                 String site, boolean onlyImpares, Pageable pageable) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<WindConditionsEntity> dataQuery = cb.createQuery(WindConditionsEntity.class);
        Root<WindConditionsEntity> windConditionsRoot = dataQuery.from(WindConditionsEntity.class);

        List<Predicate> predicates = getPredicates(fromYear, fromMonth, fromDay, toYear, toMonth, toDay, site, cb, windConditionsRoot, onlyImpares);


        dataQuery.where(predicates.toArray(new Predicate[0]));

        // Ejecuta la consulta y aplica paginación
        List<WindConditionsEntity> result = em.createQuery(dataQuery)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        logger.info("Resultado: " + result.size());
        // Recuenta el total de resultados
        Long count = customWindConditionsCount(fromYear, fromMonth, fromDay,
                toYear, toMonth, toDay,
                site);

        return new PageImpl<>(result, pageable, count);
    }

    private static List<Predicate> getPredicates(Integer fromYear, Integer fromMonth, Integer fromDay,
                                                 Integer toYear, Integer toMonth, Integer toDay,
                                                 String site, CriteriaBuilder cb,
                                                 Root<WindConditionsEntity> windConditionsRoot, boolean onlyImpares) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(windConditionsRoot.get("id").get("site"), site));

        // Condiciones para fechas
        Predicate fromCondition;
        Predicate toCondition;

        // Mismo año
        if (fromYear.equals(toYear)) {
            // Mismo mes
            if (fromMonth.equals(toMonth)) {
                fromCondition = cb.and(
                        cb.equal(windConditionsRoot.get("id").get("year"), fromYear),
                        cb.equal(windConditionsRoot.get("id").get("month"), fromMonth),
                        cb.between(windConditionsRoot.get("id").get("day"), fromDay, toDay)
                );
                predicates.add(fromCondition);
            } else {
                // Diferente mes
                fromCondition = cb.and(
                        cb.equal(windConditionsRoot.get("id").get("year"), fromYear),
                        cb.or(
                                // Primer mes, días mayores o iguales al inicio
                                cb.and(
                                        cb.equal(windConditionsRoot.get("id").get("month"), fromMonth),
                                        cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("day"), fromDay)
                                ),
                                // Mes final, días menores o iguales al fin
                                cb.and(
                                        cb.equal(windConditionsRoot.get("id").get("month"), toMonth),
                                        cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("day"), toDay)
                                ),
                                // Meses intermedios
                                cb.and(
                                        cb.greaterThan(windConditionsRoot.get("id").get("month"), fromMonth),
                                        cb.lessThan(windConditionsRoot.get("id").get("month"), toMonth)
                                )
                        )
                );
                predicates.add(fromCondition);
            }
        } else {
            // Diferente año (si fuera necesario adaptar, aunque no lo mencionaste)
            fromCondition = cb.or(
                    // Año inicial, desde fromMonth/fromDay
                    cb.and(
                            cb.equal(windConditionsRoot.get("id").get("year"), fromYear),
                            cb.or(
                                    cb.and(
                                            cb.equal(windConditionsRoot.get("id").get("month"), fromMonth),
                                            cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("day"), fromDay)
                                    ),
                                    cb.greaterThan(windConditionsRoot.get("id").get("month"), fromMonth)
                            )
                    ),
                    // Años intermedios completos
                    cb.and(
                            cb.greaterThan(windConditionsRoot.get("id").get("year"), fromYear),
                            cb.lessThan(windConditionsRoot.get("id").get("year"), toYear)
                    ),
                    // Año final, hasta toMonth/toDay
                    cb.and(
                            cb.equal(windConditionsRoot.get("id").get("year"), toYear),
                            cb.or(
                                    cb.and(
                                            cb.equal(windConditionsRoot.get("id").get("month"), toMonth),
                                            cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("day"), toDay)
                                    ),
                                    cb.lessThan(windConditionsRoot.get("id").get("month"), toMonth)
                            )
                    )
            );
            predicates.add(fromCondition);
        }

        // Condición de impares, si es necesario
        if (onlyImpares) {
            Predicate timeCondition = cb.equal(
                    cb.mod(windConditionsRoot.get("id").get("time"), 2),
                    1
            );
            predicates.add(timeCondition);
        }

        return predicates;
    }

    private Long customWindConditionsCount(Integer fromYear, Integer fromMonth, Integer fromDay,
                                           Integer toYear, Integer toMonth, Integer toDay,
                                           String site) {
        boolean onlyImpares = false;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<WindConditionsEntity> windConditionsRoot = countQuery.from(WindConditionsEntity.class);

        List<Predicate> predicates = getPredicates(fromYear, fromMonth, fromDay, toYear, toMonth, toDay, site, cb, windConditionsRoot, onlyImpares);

        countQuery.select(cb.count(windConditionsRoot)).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(countQuery).getSingleResult();
    }
}