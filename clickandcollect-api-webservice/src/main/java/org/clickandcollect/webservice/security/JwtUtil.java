package org.clickandcollect.webservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.clickandcollect.model.entity.Restaurant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.expiration_seconds}")
    private long JWT_EXPIRATION_SECONDS;
    @Value("${jwt.secret}")
    private String SIGNING_KEY;
    private static String TOKEN_PREFIX = "Bearer ";
    private static String HEADER_STRING = "Authorization";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Restaurant restaurant) {
        return doGenerateToken(restaurant.getEmail());
    }

    private String doGenerateToken(String subject) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public long getJwtExpiration() {
        return JWT_EXPIRATION_SECONDS;
    }

    public String getJwtSigningKey() {
        return SIGNING_KEY;
    }

    public static String getTokenPrefix() {
        return TOKEN_PREFIX;
    }

    public static String getHeaderString() {
        return HEADER_STRING;
    }
}
