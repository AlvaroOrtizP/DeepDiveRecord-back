package com.record.DeepDiveRecord.repository;

import com.record.DeepDiveRecord.entity.GeographicalLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeographicalLocationRepository extends JpaRepository<GeographicalLocationEntity, Integer> {
}
