package com.bewell.ms.users.config;

import com.bewell.ms.users.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("Microservice-users: AuthenticationFilter");

//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            final String jwt = authHeader.substring(7);
//            System.out.println("jwt: " + jwt);
//            final String userEmail = jwtService.extractUsername(jwt);
//            System.out.println("userEmail: " + userEmail);
//
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            System.out.println("authentication: " + authentication);
//
//            if (userEmail != null && authentication == null) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
//                System.out.println("userDetails: " + userDetails);
//
//                if (jwtService.isTokenValid(jwt, userDetails)) {
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//                    System.out.println("authToken: " + authToken);
//
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    System.out.println("authToken: " + authToken);
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }

            filterChain.doFilter(request, response);
//        } catch (Exception exception) {
//            handlerExceptionResolver.resolveException(request, response, null, exception);
//        }
    }
}