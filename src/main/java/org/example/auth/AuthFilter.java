package org.example.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.equals("/api/camions")) {
            chain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token introuvable ou incorrect");
            return;
        } else if (!authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token introuvable ou incorrect");
            return;
        }
        String token = authHeader.substring(7);
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            String texte = claims.getSubject();
            request.setAttribute("texte",texte);
            chain.doFilter(request, response);
        } catch ( JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token introuvable ou incorrect");
            return;
        }

    }

}
