package com.ozo.security.jwt;

import com.ozo.security.domain.dto.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
    private final long tokenValidityInMilliseconds;
    private static final String AUTHORITIES_KEY = "roles";
    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(
        @Value("${jwt.secretKey}") String secretKey,
        @Value("${jwt.token-validity-in-milli-seconds}") long tokenValidityInMilliseconds,
        UserDetailsService userDetailsService
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.userDetailsService = userDetailsService;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }

    public String createToken(Authentication authentication) {

        return Jwts.builder()
                   .setSubject(authentication.getName())
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(getValdityDate())
                   .signWith(secretKey, SignatureAlgorithm.HS256)
                   .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        UserContext userDetails = (UserContext) userDetailsService.loadUserByUsername(claims.getSubject());
        List<SimpleGrantedAuthority> authorities = List.of();
        Object rolesObject = claims.get(AUTHORITIES_KEY);
        if (rolesObject instanceof List<?> rolesList) {
            authorities = rolesList.stream()
                                   .filter(role -> role instanceof String)
                                   .map(role -> new SimpleGrantedAuthority((String) role))
                                   .toList();
        }
        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Date getValdityDate() {
        final long now = new Date().getTime();

        return new Date(now + this.tokenValidityInMilliseconds);
    }

    private List<String> getAuthoritiesToString(final Authentication authentication) {
        return Optional.ofNullable(authentication)
                       .map(auth -> auth.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                       .orElseGet(Collections::emptyList);
    }

}
