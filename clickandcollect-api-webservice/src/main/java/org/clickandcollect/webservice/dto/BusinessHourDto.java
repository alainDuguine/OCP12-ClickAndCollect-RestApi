package org.clickandcollect.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessHourDto {
    @NotNull
    private Integer startDay;
    @NotNull
    private Integer endDay;
    @NotNull
    private String startTime;
    @NotNull
    private String endTime;
}
