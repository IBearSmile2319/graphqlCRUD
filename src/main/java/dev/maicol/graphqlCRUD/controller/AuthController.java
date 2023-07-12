package dev.maicol.graphqlCRUD.controller;

import dev.maicol.graphqlCRUD.entity.User;
import dev.maicol.graphqlCRUD.enums.Role;
import dev.maicol.graphqlCRUD.request.LoginRequest;
import dev.maicol.graphqlCRUD.request.RegisterRequest;
import dev.maicol.graphqlCRUD.response.AuthResponse;
import dev.maicol.graphqlCRUD.service.UserService;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@Slf4j
public class AuthController {

    @Autowired
    UserService userService;


    @MutationMapping("register")
    public AuthResponse register(@Argument RegisterRequest dto) {
            return userService.register(dto);
    }

    @MutationMapping("login")
    public AuthResponse login(@Argument LoginRequest dto, Principal principal, DataFetchingEnvironment environment) {

        log.info("Login request: {}", dto);
        log.info("Principal: {}", principal.getName());
        log.info("DataFetchingEnvironment: {}", environment);

        return userService.login(dto);
    }
}
