package org.clickandcollect.webservice.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String message = null;
        if (request.getAttribute("error") != null) {
            message = request.getAttribute("error").toString();
        }

        if (authException.getMessage().equals("Bad credentials")) {
            message = "BAD_CREDENTIALS";
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message != null ? message: authException.getMessage());
    }

}
