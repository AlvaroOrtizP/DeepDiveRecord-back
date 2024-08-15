package com.record.DeepDiveRecord.infrastructure.adapter.repository.wind_conditions;

import java.util.ArrayList;
import java.util.List;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
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

@Repository
public class WindConditionsCustomRepositoryImpl implements WindConditionsCustomRepository {
    @PersistenceContext
    EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(WindConditionsCustomRepositoryImpl.class);

    @Override
    public Page<WindConditionsEntity> customWindConditionsSearch(Integer fromYear, Integer fromMonth, Integer fromDay,
                                                                 Integer toYear, Integer toMonth, Integer toDay,
                                                                 String site, Pageable pageable) {


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<WindConditionsEntity> dataQuery = cb.createQuery(WindConditionsEntity.class);
        Root<WindConditionsEntity> windConditionsRoot = dataQuery.from(WindConditionsEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(windConditionsRoot.get("id").get("site"), site));

        Predicate fromCondition = cb.and(
                cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("year"), fromYear),
                cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("month"), fromMonth),
                cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("day"), fromDay)
        );

        Predicate toCondition = cb.and(
                cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("year"), toYear),
                cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("month"), toMonth),
                cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("day"), toDay)
        );

        predicates.add(cb.or(fromCondition, toCondition));

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

    private Long customWindConditionsCount(Integer fromYear, Integer fromMonth, Integer fromDay,
                                           Integer toYear, Integer toMonth, Integer toDay,
                                           String site) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<WindConditionsEntity> windConditionsRoot = countQuery.from(WindConditionsEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(windConditionsRoot.get("id").get("site"), site));

        Predicate fromCondition = cb.and(
                cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("year"), fromYear),
                cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("month"), fromMonth),
                cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("day"), fromDay)
        );

        Predicate toCondition = cb.and(
                cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("year"), toYear),
                cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("month"), toMonth),
                cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("day"), toDay)
        );

        predicates.add(cb.or(fromCondition, toCondition));

        countQuery.select(cb.count(windConditionsRoot)).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(countQuery).getSingleResult();
    }
}