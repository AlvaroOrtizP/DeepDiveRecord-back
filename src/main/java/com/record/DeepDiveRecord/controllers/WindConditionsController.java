package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.api.domain.InDeepDiveLogger;
import com.record.DeepDiveRecord.api.domain.OutDeepDiveLogger;
import com.record.DeepDiveRecord.core.model.windconditions.OutForecast;
import com.record.DeepDiveRecord.core.usecase.windconditions.WindConditionsUseCase;
import com.record.DeepDiveRecord.mapper.DeepDiveLoggerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/windwu")
public class WindConditionsController {
    WindConditionsUseCase windConditionsUseCase;
    DeepDiveLoggerMapper deepDiveLoggerMapper;

    public WindConditionsController(WindConditionsUseCase windConditionsUseCase, DeepDiveLoggerMapper deepDiveLoggerMapper ){
        this.windConditionsUseCase = windConditionsUseCase;
        this.deepDiveLoggerMapper = deepDiveLoggerMapper;
    }

    @PostMapping("/runPythonScript")
    public ResponseEntity<OutDeepDiveLogger> runPythonScript(@RequestBody InDeepDiveLogger deepDiveLogger) {
        System.out.println("Entra en runPythonScript");
        OutForecast res = windConditionsUseCase.createWindCondition(deepDiveLoggerMapper.fromDomainToCore(deepDiveLogger));
        if (res.isOk()) {
            System.out.println("runPythonScript OK");
            return ResponseEntity.status(HttpStatus.CREATED).body(new OutDeepDiveLogger(true, "Operación exitosa", null));
        }
        System.out.println("runPythonScript NOK " + res.getErros().get(0));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OutDeepDiveLogger(false, null, res.getErros().get(0)));
    }

}