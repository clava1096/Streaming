package com.clava1096.musicstreaming.config.jwt;

import com.clava1096.musicstreaming.services.security.UserValidationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
@Service
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenManager jwtTokenManager;

    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            try {
                username =jwtTokenManager.getUsernameFromToken(jwtToken);
            }
            catch (Exception e) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        final boolean canBeStartTokenValidation = Objects.nonNull(username) && Objects.isNull(securityContext.getAuthentication());
        if(!canBeStartTokenValidation) {
            filterChain.doFilter(request, response);
            return;
        }
        final UserDetails user = userDetailsService.loadUserByUsername(username);
        final boolean validToken = jwtTokenManager.validateToken(jwtToken, user.getUsername());

        if (!validToken) {
            filterChain.doFilter(request, response);
            return;
        }
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        securityContext.setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
