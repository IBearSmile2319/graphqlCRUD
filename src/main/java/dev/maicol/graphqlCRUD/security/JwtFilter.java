package dev.maicol.graphqlCRUD.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        check do we have Authorization header
//        yes -> Create Authentication(which is not authenticated)
//        Authentication manager(authentication) -> authentication provider (jwt Verify)
//        authentication (which is authenticated) from manager
//        set authentication to security context
        try{


        if (request.getHeader("Authorization") == null) {
            filterChain.doFilter(request, response);
            return;
        }
//        Bearer should be part of request as best practices
        var authentication = new CustomAuthentication(
                false,
                request.getHeader("Authorization"),
                null,
                null
        );

        var authenticated = customAuthenticationManager.authenticate(authentication);
        if (authenticated.isAuthenticated()) {
            var context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticated);
            SecurityContextHolder.setContext(context);
            filterChain.doFilter(request, response);
        }

        }catch(Exception e){
            filterChain.doFilter(request, response);
            
        }

    }
}
