package com.record.DeepDiveRecord.repository;

import com.record.DeepDiveRecord.entity.FishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<FishEntity, Integer> {
}