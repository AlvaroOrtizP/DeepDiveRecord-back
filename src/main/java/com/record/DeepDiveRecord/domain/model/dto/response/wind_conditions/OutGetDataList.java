package com.record.DeepDiveRecord.domain.model.dto.response.wind_conditions;

import com.record.DeepDiveRecord.domain.model.dto.response.common.Pagination;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OutGetDataList {
    private List<OutGetData> OutGetDataList;
    private List<OutGetDataMedia> OutGetDataMediaList;
    private Pagination pagination;
}