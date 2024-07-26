package com.record.DeepDiveRecord.repository;

import com.record.DeepDiveRecord.entity.TideTableEntity;
import com.record.DeepDiveRecord.entity.TideTableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TideTableRepository extends JpaRepository<TideTableEntity, TideTableId> {
}
