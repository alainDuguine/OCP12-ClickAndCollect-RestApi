package org.clickandcollect.webservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegistrationFormDto {
    @NotNull
    @Size(min = 3, max = 100)
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 6)
    private String password;
}
