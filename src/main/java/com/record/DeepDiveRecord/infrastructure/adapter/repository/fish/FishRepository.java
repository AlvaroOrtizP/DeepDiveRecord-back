package com.record.DeepDiveRecord.infrastructure.adapter.repository.fish;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.FishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<FishEntity, Integer> {

}