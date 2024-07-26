package com.record.DeepDiveRecord.repository;

import com.record.DeepDiveRecord.entity.FishingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingRepository extends JpaRepository<FishingEntity, Integer> {
}