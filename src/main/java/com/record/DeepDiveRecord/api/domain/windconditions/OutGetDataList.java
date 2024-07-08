package com.record.DeepDiveRecord.api.domain.windconditions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutGetDataList {
    private List<OutGetData> OutGetDataList;
    private Pagination pagination;
}
