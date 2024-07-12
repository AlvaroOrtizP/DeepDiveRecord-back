package com.record.DeepDiveRecord.service.adapters.diveday;

import com.record.DeepDiveRecord.core.model.common.Checker;
import com.record.DeepDiveRecord.core.model.diveday.InCreateDiveDay;
import com.record.DeepDiveRecord.core.model.diveday.OutCreateDiveDay;
import com.record.DeepDiveRecord.core.ports.diveday.CreateDiveDayPort;
import com.record.DeepDiveRecord.core.ports.fishing.CreateFishingDayPort;
import com.record.DeepDiveRecord.entity.DiveDayAndFishingEntity;
import com.record.DeepDiveRecord.entity.DiveDayEntity;
import com.record.DeepDiveRecord.mapper.DiveDayMapper;
import com.record.DeepDiveRecord.mapper.FishingMapper;
import com.record.DeepDiveRecord.repository.DiveDayRepository;
import com.record.DeepDiveRecord.repository.FishRepository;
import com.record.DeepDiveRecord.repository.FishingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class CreateDiveDayAdapter implements CreateDiveDayPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateDiveDayAdapter.class);

    DiveDayMapper mapper;
    FishingMapper fishingMapper;
    FishRepository fishRepository;
    FishingRepository fishingRepository;
    DiveDayRepository diveDayRepository;
    CreateFishingDayPort createFishingDayPort;

    public CreateDiveDayAdapter(DiveDayMapper mapper, DiveDayRepository diveDayRepository, FishingMapper fishingMapper,
                                CreateFishingDayPort createFishingDayPort, FishRepository fishRepository, FishingRepository fishingRepository) {
        this.mapper = mapper;
        this.diveDayRepository = diveDayRepository;
        this.fishingMapper = fishingMapper;
        this.createFishingDayPort = createFishingDayPort;
        this.fishRepository = fishRepository;
        this.fishingRepository = fishingRepository;

    }

    /**
     * Metodo que obtiene los datos del dia de buceo, crea los registros de Dia de Pesca y registra el dia de buceo
     * @param input Datos del dia de buceo y la lista de pesca
     * @return Devuelve la nueva entidad creada indicando si se pudo hacer el registro o no (indicando en caso de no el error)
     */
    @Override
    @Transactional
    public OutCreateDiveDay createDiveDay(InCreateDiveDay input) {
        LOGGER.info("Comienza el metodo createDiveDay con input: {}", input);

        try {
            //Mapeamos la entidad para obtener el core
            DiveDayEntity diveDayEntity = mapper.fromCreateCoreToEntity(input);

            //Creamos los registros de pesca (ya que un dia de buceo puede tener 0 o N pescas) indicando el dia de pesca y la lista de pescas
            List<DiveDayAndFishingEntity> diveDayAndFishingList = createFishingDayPort.createFishingDay(diveDayEntity, input.getDiveDay().getFishingList());

            //Añadimos las relaciones creadas
            diveDayEntity.setDiveDayAndFishingList(diveDayAndFishingList);

            //Guardamos el registro del dia de buceo
            diveDayEntity = diveDayRepository.save(diveDayEntity);

            //Guardamos el indicador nuevo (existente o no)
            input.getDiveDay().setDiveDayId(diveDayEntity.getDiveDayId());

            LOGGER.info("Finaliza createDiveDay con resultado correcto");
            //Creamos el resultado con el dato correcto
            return new OutCreateDiveDay(mapper.fromCoreToOutModelCore(input).getDiveDay(), new Checker(true, "CORRECT"));
        } catch (Exception e) {
            LOGGER.info("Finaliza createDiveDay con resultado fallido {}", e.getMessage());
            //Creamos el resultado con el dato fallido y mensaje de error
            return new OutCreateDiveDay(mapper.fromCoreToOutModelCore(input).getDiveDay(), new Checker(false, "ERROR " + e.getMessage()));
        }
    }
}
