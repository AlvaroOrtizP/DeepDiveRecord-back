package com.record.DeepDiveRecord.infrastructure.adapter.repository.dive_day;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.DiveDayEntity;
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

import java.util.ArrayList;
import java.util.List;

@Repository
public class DiveDayCustomRepositoryImpl implements DiveDayCustomRepository {
    @PersistenceContext
    EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(DiveDayCustomRepositoryImpl.class);

    @Override
    public Page<DiveDayEntity> findDiveDaysByFilters(String site, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DiveDayEntity> dataQuery = cb.createQuery(DiveDayEntity.class);
        Root<DiveDayEntity> diveDayRoot = dataQuery.from(DiveDayEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        if (site != null) {
            predicates.add(cb.equal(diveDayRoot.get("site"), site));
        }

        dataQuery.where(predicates.toArray(new Predicate[0]));

        // Ejecuta la consulta y aplica paginación
        List<DiveDayEntity> result = em.createQuery(dataQuery)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        logger.info("Resultado: " + result.size());
        // Recuenta el total de resultados
        Long count = customWindConditionsCount(site);

        return new PageImpl<>(result, pageable, count);
    }

    private Long customWindConditionsCount(String site) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<DiveDayEntity> diveDayRoot = countQuery.from(DiveDayEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        if (site != null) {
            predicates.add(cb.equal(diveDayRoot.get("site"), site));
        }
        countQuery.select(cb.count(diveDayRoot)).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(countQuery).getSingleResult();
    }
}
