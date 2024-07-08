package com.record.DeepDiveRecord.api.domain.windconditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
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
