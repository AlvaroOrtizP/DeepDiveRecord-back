package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.api.domain.windconditions.InDeepDiveLogger;
import com.record.DeepDiveRecord.api.domain.windconditions.InGetDataWeek;
import com.record.DeepDiveRecord.api.domain.windconditions.OutDeepDiveLogger;
import com.record.DeepDiveRecord.api.domain.windconditions.OutGetDataList;
import com.record.DeepDiveRecord.core.model.windconditions.InDataZoneRangeWC;
import com.record.DeepDiveRecord.core.model.windconditions.OutDailyStatistics;
import com.record.DeepDiveRecord.core.model.windconditions.OutForecast;
import com.record.DeepDiveRecord.core.usecase.windconditions.WindConditionsUseCase;
import com.record.DeepDiveRecord.mapper.PaginationMapper;
import com.record.DeepDiveRecord.mapper.WindConditionsMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/windwu")//TODO cambiar las url ya que solo existen 2 y deberia ser get"" y post ""
public class WindConditionsController {
    WindConditionsUseCase windConditionsUseCase;
    WindConditionsMapper windConditionsMapper;
    PaginationMapper paginationMapper;

    public WindConditionsController(WindConditionsUseCase windConditionsUseCase, WindConditionsMapper windConditionsMapper, PaginationMapper paginationMapper) {
        this.windConditionsUseCase = windConditionsUseCase;
        this.windConditionsMapper = windConditionsMapper;
        this.paginationMapper = paginationMapper;
    }

    /**
     * Metodo que ejecuta una serie de scripts de python los cuales recopilan una serie de datos de varias webs y los guardan en la tabla wind_conditions
     *
     * @param deepDiveLogger objeto que contiene los parametros necesarios para ejecutar los scripts. Ejemplo: (idWindwuru : "487006", idAemet : "play_v2_3900602", lugar : "Ajo, Cantabria")
     * @return Retorna Ok en caso de guardar todos los datos de forma incorrecta y NOK si existio algun error
     */
    @PostMapping("/runPythonScript")
    public ResponseEntity<OutDeepDiveLogger> createWindCondition(@RequestBody InDeepDiveLogger deepDiveLogger) {
        System.out.println("Entra en createWindCondition");
        OutForecast res = windConditionsUseCase.createWindCondition(windConditionsMapper.fromDomainToCore(deepDiveLogger));
        if (res.isOk()) {
            System.out.println("createWindCondition OK");
            return ResponseEntity.status(HttpStatus.CREATED).body(new OutDeepDiveLogger(true, "Operación exitosa", null));
        }
        System.out.println("createWindCondition NOK " + res.getErros().get(0));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OutDeepDiveLogger(false, null, res.getErros().get(0)));
    }
    //Se usa post ya que los parametros a mandar para identificar el dato son demasiados
    @PostMapping("/getDataWeek")
    public ResponseEntity<OutGetDataList> getDataWeek(@RequestBody InGetDataWeek inGetDataWeek) {

        System.out.println("Entra en getDataWeek");

        InDataZoneRangeWC inDataZoneRangeWC = windConditionsMapper.mapToInDataZoneRangeWCFromInGetDataWeek(inGetDataWeek);


        Page<OutDailyStatistics> res =  windConditionsUseCase.getDeepDiveDataByDaysPort(inDataZoneRangeWC, inGetDataWeek.getPage(), inGetDataWeek.getSize());

        OutGetDataList response = new OutGetDataList();
        response.setOutGetDataList(windConditionsMapper.mapOutDailyStatisticsListToOutGetDataList(res.getContent()));
        response.setPagination(paginationMapper.toAPIDomain(res));

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}