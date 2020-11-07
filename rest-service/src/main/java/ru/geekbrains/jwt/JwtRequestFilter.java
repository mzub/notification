package ru.geekbrains.jwt;


import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.geekbrains.exceptions.JwtAuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@AllArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        Enumeration<String> headerNames = request.getHeaderNames();

        log.info("Authorization token " + authHeader);
        String username = null;
//        String jwt = null;
        if (authHeader != null) {
//            jwt = authHeader.substring(7);
            try {
                username = jwtTokenProvider.getUsername(authHeader);
            } catch (ExpiredJwtException ex) {
                log.info("Token is invalid: " + ex.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "{ msg: The token is expired }");
                return;
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    Authentication authentication = jwtTokenProvider.getAuthentication(authHeader);
                    if (authentication != null) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (JwtAuthenticationException e) {
                    SecurityContextHolder.clearContext();
                    response.sendError(e.getHttpStatus().value());
                    throw new JwtAuthenticationException("JWT token is expired or invalid");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
