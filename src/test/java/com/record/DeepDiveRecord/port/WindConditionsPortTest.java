package com.record.DeepDiveRecord.port;

import com.record.DeepDiveRecord.domain.model.dto.port.wind_condition.FindDeepDiveDataByDays;
import com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl.WindConditionsRepositoryImpl;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.WindConditionsId;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.wind_conditions.WindConditionsCustomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
class WindConditionsPortTest {
    @Mock
    private WindConditionsCustomRepository windConditionsCustomRepository;

    @InjectMocks
    private WindConditionsRepositoryImpl windConditionsRepositoryImpl;

    private FindDeepDiveDataByDays findDeepDiveDataByDays;
    private WindConditionsEntity windConditionsEntity;

    @BeforeEach
    public void setUp() {
        findDeepDiveDataByDays = new FindDeepDiveDataByDays();
        findDeepDiveDataByDays.setFromYear(2023);
        findDeepDiveDataByDays.setFromMonth(8);
        findDeepDiveDataByDays.setFromDay(1);
        findDeepDiveDataByDays.setToYear(2023);
        findDeepDiveDataByDays.setToMonth(8);
        findDeepDiveDataByDays.setToDay(10);
        findDeepDiveDataByDays.setSite("Some Site");
        findDeepDiveDataByDays.setPage(0);
        findDeepDiveDataByDays.setSize(10);

        windConditionsEntity = new WindConditionsEntity();
        WindConditionsId windConditionsId = new WindConditionsId();
        windConditionsId.setDayOfYear(120);
        windConditionsId.setYear(2024);
        windConditionsId.setTime(2);
        windConditionsId.setSite("24823");
        windConditionsEntity.setId(windConditionsId);
    }

    @Test
    void testGetDeepDiveDataByDays_Success() {
        // Crear una lista de WindConditionsEntity simulada
        List<WindConditionsEntity> windConditionsEntities = Collections.singletonList(windConditionsEntity);

        // Crear una página simulada con la lista de entidades
        Page<WindConditionsEntity> expectedPage = new PageImpl<>(windConditionsEntities, PageRequest.of(0, 10), windConditionsEntities.size());

        // Configurar el mock de WindConditionsCustomRepository para devolver la página simulada
        when(windConditionsCustomRepository.customWindConditionsSearch(
                any(Integer.class),
                any(Integer.class),
                any(Integer.class),
                any(Integer.class),
                any(Integer.class),
                any(Integer.class),
                any(String.class),
                eq(true),
                any(PageRequest.class)
        )).thenReturn(expectedPage);

        // Ejecutar el método getDeepDiveDataByDays
        Page<WindConditionsEntity> result = windConditionsRepositoryImpl.getDeepDiveDataByDays(findDeepDiveDataByDays, true);

        // Verificar que el resultado sea el esperado
        assertEquals(expectedPage.getContent().size(), result.getContent().size(), "El número de resultados debería coincidir");
        assertEquals(expectedPage.getContent().get(0), result.getContent().get(0), "La entidad devuelta debería coincidir con la esperada");
    }

    @Test
    void testGetDeepDiveDataByDays_EmptyResult() {
        // Crear una página vacía simulada
        Page<WindConditionsEntity> expectedPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);

        // Configurar el mock de WindConditionsCustomRepository para devolver la página vacía simulada
        when(windConditionsCustomRepository.customWindConditionsSearch(
                any(Integer.class),
                any(Integer.class),
                any(Integer.class),
                any(Integer.class),
                any(Integer.class),
                any(Integer.class),
                any(String.class),
                eq(true),
                any(PageRequest.class)
        )).thenReturn(expectedPage);

        // Ejecutar el método getDeepDiveDataByDays
        Page<WindConditionsEntity> result = windConditionsRepositoryImpl.getDeepDiveDataByDays(findDeepDiveDataByDays, true);

        // Verificar que el resultado sea el esperado (vacío)
        assertEquals(0, result.getContent().size(), "El resultado debería estar vacío");
    }
}
