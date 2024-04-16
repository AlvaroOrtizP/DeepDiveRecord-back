package com.record.DeepDiveRecord.repository;

import com.record.DeepDiveRecord.entity.FishingEntity;
import com.record.DeepDiveRecord.entity.WeatherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishingRepository extends JpaRepository<FishingEntity, Integer>{
}
