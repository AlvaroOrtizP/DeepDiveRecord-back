package com.record.DeepDiveRecord.api.domain.windconditions;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutGetDataList {
    private List<OutGetData> OutGetDataList;
    private Pagination pagination;
}
