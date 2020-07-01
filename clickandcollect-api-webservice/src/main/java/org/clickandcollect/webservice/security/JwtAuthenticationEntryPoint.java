package org.clickandcollect.webservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clickandcollect.webservice.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String message = null;
        if (request.getAttribute("error") != null) {
            message = request.getAttribute("error").toString();
        }

        if (message != null && message.equals("UNAUTHORIZED_RESOURCE")) {
            prepareResponse(response, HttpStatus.FORBIDDEN, "The Resource cannot be accessed by this user");
        } else if (message != null) {
            prepareResponse(response, HttpStatus.UNAUTHORIZED, message);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }

    private void prepareResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(status.value());
        ApiError error = new ApiError(status, message, null);
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, error);
        out.flush();
    }

}
