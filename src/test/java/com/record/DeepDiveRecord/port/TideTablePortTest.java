package com.record.DeepDiveRecord.port;

import com.record.DeepDiveRecord.domain.model.dto.port.tide_table.FindTideTable;
import com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl.TideTableRepositoryImpl;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableId;
import com.record.DeepDiveRecord.infrastructure.adapter.mapper.TideTableMapper;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.tide_table.TideTableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
class TideTablePortTest {

    @Mock
    private TideTableRepository tideTableRepository;

    @Mock
    private TideTableMapper tideTableMapper;

    @InjectMocks
    private TideTableRepositoryImpl tideTableRepositoryImpl;

    private FindTideTable findTideTable;

    private TideTableEntity tideTableEntity;

    @BeforeEach
    public void setUp() {
        findTideTable = new FindTideTable();
        findTideTable.setYear("2024");
        findTideTable.setSite("123123");
        findTideTable.setMonth("12");
        findTideTable.setDay("1");

        tideTableEntity = new TideTableEntity();

        TideTableId id = new TideTableId();
        id.setYear("2024");
        id.setSite("123123");
        id.setMonth("12");
        id.setDay("1");
        tideTableEntity.setId(id);
    }

    @Test
    void testFindById_Success() {
        // Configurar el mock de TideTableMapper para devolver el ID correcto
        when(tideTableMapper.entityIdFromDtoPort(findTideTable)).thenReturn(tideTableEntity.getId());

        // Configurar el mock de TideTableRepository para devolver el TideTableEntity correcto
        when(tideTableRepository.findById(tideTableEntity.getId())).thenReturn(Optional.of(tideTableEntity));

        // Ejecutar el método findById
        Optional<TideTableEntity> result = tideTableRepositoryImpl.findById(findTideTable);

        // Verificar que el resultado sea el esperado
        assertTrue(result.isPresent());
        assertEquals(tideTableEntity, result.get());
    }

    @Test
    void testFindById_NotFound() {
        // Configurar el mock de TideTableMapper para devolver el ID correcto
        when(tideTableMapper.entityIdFromDtoPort(findTideTable)).thenReturn(tideTableEntity.getId());

        // Configurar el mock de TideTableRepository para devolver un Optional vacío
        when(tideTableRepository.findById(tideTableEntity.getId())).thenReturn(Optional.empty());

        // Ejecutar el método findById
        Optional<TideTableEntity> result = tideTableRepositoryImpl.findById(findTideTable);

        // Verificar que el resultado esté vacío
        assertTrue(result.isEmpty());
    }
}
