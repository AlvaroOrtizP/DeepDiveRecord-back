package com.record.DeepDiveRecord.repository.windconditions;

import com.record.DeepDiveRecord.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.entity.WindConditionsId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class WindConditionsCustomRepositoryImpl implements  WindConditionsCustomRepository{
    @PersistenceContext
    EntityManager em;
    @Override
    public Page<WindConditionsEntity> customWindConditionsSearch(String fromYear, String fromMonth, String fromDay,
                                                                 String toYear, String toMonth, String toDay,
                                                                 String site, Pageable pageable) {

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


        predicates.add(cb.equal(windConditionsRoot.get("site"), site));


        dataQuery.where(predicates.toArray(new Predicate[0]));

        // Ejecuta la consulta y aplica paginación
        List<WindConditionsEntity> result = em.createQuery(dataQuery)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // Recuenta el total de resultados
        Long count = customWindConditionsCount(fromYear, fromMonth, fromDay, toYear, toMonth, toDay, site);

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

        // Agrega la condición del sitio si es necesaria
        if (site != null && !site.isEmpty()) {
            predicates.add(cb.equal(windConditionsRoot.get("site"), site));
        }

        countQuery.select(cb.count(windConditionsRoot)).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(countQuery).getSingleResult();
    }


}
