package com.record.DeepDiveRecord.infrastructure.adapter.repository;


import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeographicalLocationRepository extends JpaRepository<GeographicalLocationEntity, Integer> {


}