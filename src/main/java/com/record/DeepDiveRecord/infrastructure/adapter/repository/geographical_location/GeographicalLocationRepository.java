package com.record.DeepDiveRecord.infrastructure.adapter.repository.geographical_location;


import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeographicalLocationRepository extends JpaRepository<GeographicalLocationEntity, Integer> {


}