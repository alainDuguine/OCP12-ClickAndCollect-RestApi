package org.clickandcollect.webservice.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String ERROR = "error";
    @Resource(name = "authService")
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(JwtUtil.getHeaderString());
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(JwtUtil.getTokenPrefix())) {
            authToken = header.replace(JwtUtil.getTokenPrefix(), "");
            try {
                username = this.jwtUtil.getUsernameFromToken(authToken);
            } catch (ExpiredJwtException e) {
                request.setAttribute(ERROR, "EXPIRED_JWT");
                log.warn("The token is expired", e);
            } catch (SignatureException e) {
                request.setAttribute(ERROR, "WRONG_JWT");
                log.error("Trying to connect with wrong jwt");
            } catch (BadCredentialsException e) {
                request.setAttribute(ERROR, "BAD_CREDENTIALS");
                log.warn("bad credentials");
            } catch (Exception e) {
                request.setAttribute(ERROR, "WRONG_JWT");
                log.error("an error occured during extraction username from token", e);

            }
        } else {
            log.debug("no token found");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("Authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
