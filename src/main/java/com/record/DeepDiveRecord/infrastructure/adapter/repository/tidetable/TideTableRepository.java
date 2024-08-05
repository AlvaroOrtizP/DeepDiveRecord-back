package com.record.DeepDiveRecord.infrastructure.adapter.repository.tidetable;

import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.TideTableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TideTableRepository extends JpaRepository<TideTableEntity, TideTableId> {
}
