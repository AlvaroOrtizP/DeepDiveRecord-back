package com.record.DeepDiveRecord.infrastructure.adapter.repository.fishing;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingRepository extends JpaRepository<FishingEntity, Integer> {
}