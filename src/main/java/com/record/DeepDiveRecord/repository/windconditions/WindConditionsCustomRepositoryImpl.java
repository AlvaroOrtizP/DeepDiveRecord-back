package com.record.DeepDiveRecord.repository.windconditions;

import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WindConditionsCustomRepositoryImpl implements WindConditionsCustomRepository {
    @PersistenceContext
    EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(WindConditionsCustomRepositoryImpl.class);
    @Override
    public Page<WindConditionsEntity> customWindConditionsSearch(Integer fromYear, Integer fromMonth, Integer fromDay, Integer toYear, Integer toMonth, Integer toDay, String site, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<WindConditionsEntity> dataQuery = cb.createQuery(WindConditionsEntity.class);
        Root<WindConditionsEntity> windConditionsRoot = dataQuery.from(WindConditionsEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        // Añade las condiciones para filtrar entre las dos fechas
        predicates.add(cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("year"), fromYear));
        predicates.add(cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("month"), fromMonth));
        predicates.add(cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("day"), fromDay));

        predicates.add(cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("year"), toYear));
        predicates.add(cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("month"), toMonth));
        predicates.add(cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("day"), toDay));


        predicates.add(cb.equal(windConditionsRoot.get("id").get("site"), site));

        // Añade las condiciones para filtrar entre las horas 5 y 22
        predicates.add(cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("time"), 5));
        predicates.add(cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("time"), 22));


        dataQuery.where(predicates.toArray(new Predicate[0]));

        // Ejecuta la consulta y aplica paginación
        List<WindConditionsEntity> result = em.createQuery(dataQuery)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        // Recuenta el total de resultados
        Long count = customWindConditionsCount(fromYear.toString(), fromMonth.toString(), fromDay.toString(), toYear.toString(), toMonth.toString(), toDay.toString(), site);

        return new PageImpl<>(result, pageable, count);
    }

    private Long customWindConditionsCount(String fromYear, String fromMonth, String fromDay,
                                           String toYear, String toMonth, String toDay,
                                           String site) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<WindConditionsEntity> windConditionsRoot = countQuery.from(WindConditionsEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        // Añade las condiciones para filtrar entre las dos fechas
        predicates.add(cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("year"), fromYear));
        predicates.add(cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("month"), fromMonth));
        predicates.add(cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("day"), fromDay));

        predicates.add(cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("year"), toYear));
        predicates.add(cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("month"), toMonth));
        predicates.add(cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("day"), toDay));

        predicates.add(cb.equal(windConditionsRoot.get("id").get("site"), site));

        // Añade las condiciones para filtrar entre las horas 5 y 22
        predicates.add(cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("time"), 5));
        predicates.add(cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("time"), 22));

        countQuery.select(cb.count(windConditionsRoot)).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(countQuery).getSingleResult();
    }



}
