package dev.maicol.graphqlCRUD.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (authentication != null) {
            if(customAuthenticationProvider.supports(authentication.getClass())){
                return customAuthenticationProvider.authenticate(authentication);
            }
        }

        throw new BadCredentialsException("Bad credentials manager");
    }
}
