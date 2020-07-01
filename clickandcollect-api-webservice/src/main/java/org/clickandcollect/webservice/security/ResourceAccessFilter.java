package org.clickandcollect.webservice.security;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.exception.UnauthorizedResourceException;
import org.clickandcollect.model.entity.Restaurant;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ResourceAccessFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Restaurant auth = (Restaurant) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (url.contains("/restaurants/") && !auth.hasRole("ROLE_ADMIN")) {
                int startIndex = url.indexOf("/restaurants/");
                Long id = Long.valueOf(url.substring(startIndex + 13).split("/")[0]);
                if (!auth.getId().equals(id)) {
                    log.error("Attempt to access an illegal resource");
                    request.setAttribute("error", "UNAUTHORIZED_RESOURCE");
                    throw new UnauthorizedResourceException("The Resource cannot be accessed by this user");
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
