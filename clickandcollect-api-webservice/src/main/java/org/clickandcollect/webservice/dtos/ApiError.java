package org.clickandcollect.dtos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiError {
    private HttpStatus status;
    private String message;
    private String debugMessage;
    private List<String> errors;
    private Map<String, String> errorMap;
}
