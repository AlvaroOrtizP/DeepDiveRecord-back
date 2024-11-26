package com.record.DeepDiveRecord.domain.model.dto.port.historic;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CombinedItemDto {
    private List<WindConditionsEntity> windConditionsEntityList;
    private List<TideTableEntity> tideTableEntityList;
    private List<GeographicalLocationEntity> geographicalLocationEntityList;
    private List<FishEntity> fishEntityList;
    private List<FishingEntity> fishingEntityList;
    private List<DiveDayEntity> diveDayEntityList;
    private List<ConfigurationDataEntity> configurationDataEntityList;
}
