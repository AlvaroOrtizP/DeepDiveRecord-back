package com.record.DeepDiveRecord.controllers;

import com.record.DeepDiveRecord.application.usecase.FishUseCase;
import com.record.DeepDiveRecord.domain.model.dto.response.fish.FishResponse;
import com.record.DeepDiveRecord.infrastructure.rest.controller.FishController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
class FishControllerTest {
    @Mock
    private FishUseCase fishUseCase;
    @InjectMocks
    private FishController fishController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void tesGetAllFishList_Success() {
        List<FishResponse> fishList = new ArrayList<>();
        FishResponse fish = new FishResponse();
        fishList.add(fish);
        when(fishUseCase.getAllFishList()).thenReturn(fishList);

        ResponseEntity<List<FishResponse>> res = fishController.getAllFishList();
        assertAll("Verificaciones de respuesta",
                () -> assertEquals(1, res.getBody().size(), "Devuelve el DiveDay"),
                () -> assertEquals(HttpStatus.OK, res.getStatusCode(), "Devuelve el estado 200 indicando que fue obtenido un registro")
        );
    }
}
