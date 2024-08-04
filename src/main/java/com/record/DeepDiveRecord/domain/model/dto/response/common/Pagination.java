package com.record.DeepDiveRecord.domain.model.dto.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
@ToString
public class Pagination implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("totalPages")
    private Integer totalPages;

    @JsonProperty("totalElements")
    private Integer totalElements;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("numberOfElements")
    private Integer numberOfElements;

    @JsonProperty("isLast")
    private Boolean isLast;

    @JsonProperty("isFirst")
    private Boolean isFirst;

    @JsonProperty("isEmpty")
    private Boolean isEmpty;
}