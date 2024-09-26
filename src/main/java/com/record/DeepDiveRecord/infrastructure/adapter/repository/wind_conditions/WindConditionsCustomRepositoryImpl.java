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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
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
        Integer fromDayOfYear = obtenerDayOfYear(fromYear, fromMonth, fromDay);
        Integer toDayOfYear = obtenerDayOfYear(toYear, toMonth, toDay);
        logger.info("Se procede a obtener toDayOfYear: toYear {}, toMonth {}, toDay {}, se obtiene toDayOfYear {} ",toYear, toMonth, toDay, toDayOfYear);

        List<Predicate> predicates = getPredicates(fromDayOfYear,fromYear, toDayOfYear,toYear, site, cb, windConditionsRoot, onlyImpares);

        logger.info("Se procede a buscar las condiciones climaticas fromyear: {}, fromDayOfYear {}, toYear {}, toDayOfYear {}, site {} ",fromYear, fromDayOfYear,toYear, toDayOfYear,site);
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

    private static List<Predicate> getPredicates(Integer fromDayOfYear, Integer fromYear,
                                                 Integer toDayOfYear, Integer toYear,
                                                 String site, CriteriaBuilder cb,
                                                 Root<WindConditionsEntity> windConditionsRoot, boolean onlyImpares) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(windConditionsRoot.get("id").get("site"), site));

        Predicate fromCondition = cb.and(
                cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("year"), fromYear),
                cb.greaterThanOrEqualTo(windConditionsRoot.get("id").get("dayOfYear"), fromDayOfYear)
        );

        Predicate toCondition = cb.and(
                cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("year"), toYear),
                cb.lessThanOrEqualTo(windConditionsRoot.get("id").get("dayOfYear"), toDayOfYear)
        );
        if(onlyImpares){
            // Agregar el predicado para obtener solo valores impares
            Predicate timeCondition = cb.equal(
                    cb.mod(windConditionsRoot.get("id").get("time"), 2),
                    1
            );

            predicates.add(cb.and(fromCondition, toCondition, timeCondition));
        }else{
            predicates.add(cb.and(fromCondition, toCondition));
        }
        return predicates;
    }
    private Integer obtenerDayOfYear(  int year,
                               int month,
                               int day){
        // Crear instancia de Calendar
        Calendar calendar = Calendar.getInstance();

        // Establecer el año, mes y día en el objeto Calendar
        calendar.set(year, month - 1, day); // Meses en Calendar son 0-based

        // Obtener el día de la semana
        int dayOfWeek = calendar.get(Calendar.DAY_OF_YEAR);
        return dayOfWeek;
    }
    private Long customWindConditionsCount(Integer fromYear, Integer fromMonth, Integer fromDay,
                                           Integer toYear, Integer toMonth, Integer toDay,
                                           String site) {
        boolean onlyImpares = false;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<WindConditionsEntity> windConditionsRoot = countQuery.from(WindConditionsEntity.class);

        Integer fromDayOfYear = obtenerDayOfYear(fromYear, fromMonth, fromDay);
        Integer toDayOfYear = obtenerDayOfYear(toYear, toMonth, toDay);
        List<Predicate> predicates = getPredicates(fromDayOfYear,fromYear, toDayOfYear,toYear, site, cb, windConditionsRoot, onlyImpares);


        countQuery.select(cb.count(windConditionsRoot)).where(predicates.toArray(new Predicate[0]));

        return em.createQuery(countQuery).getSingleResult();
    }
}