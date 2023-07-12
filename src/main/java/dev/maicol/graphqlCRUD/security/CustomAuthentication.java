package dev.maicol.graphqlCRUD.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomAuthentication implements Authentication {
    private boolean authentication;
    private String token = null;
    private String username = null;
    private Collection<String> roles = new ArrayList<>();


//    public static void main(String[] args) {
//        CustomAuthentication customAuthentication = new CustomAuthentication();
//        customAuthentication.setAuthentication(true);
//        customAuthentication.setToken(null);
//        customAuthentication.setUsername(null);
//        System.out.println(customAuthentication);
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> (GrantedAuthority) () -> role).toList();
    }

    @Override
    public String getCredentials() {
        return token.isEmpty() ? null : token;
    }

    @Override
    public Object getDetails() {
        return username.isEmpty() ? null : username;
    }

    @Override
    public Object getPrincipal() {
        return username.isEmpty() ? null : username;
    }

    @Override
    public boolean isAuthenticated() {
        return authentication;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not allowed");

    }

    @Override
    public String getName() {
        return username.isEmpty() ? null : username;
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }
}
