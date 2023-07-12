package dev.maicol.graphqlCRUD.security;

import dev.maicol.graphqlCRUD.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    try {
        var customAuthentication = (CustomAuthentication) authentication;

        var claims = jwtUtil.validateToken(customAuthentication.getCredentials());

        var  custom = new CustomAuthentication(
                true,
                null,
                claims.getSubject(),
                (Collection<String>) claims.get("roles")
        );

        return custom;

    } catch (Exception e) {
        throw new BadCredentialsException("Bad credentials provider");
    }

    }

    @Override
    public boolean supports(Class<?> authentication) {
       return CustomAuthentication.class.equals(authentication);
    }
}
