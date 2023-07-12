package dev.maicol.graphqlCRUD.service;

import dev.maicol.graphqlCRUD.entity.User;
import dev.maicol.graphqlCRUD.repository.UserRepository;
import dev.maicol.graphqlCRUD.request.LoginRequest;
import dev.maicol.graphqlCRUD.request.RegisterRequest;
import dev.maicol.graphqlCRUD.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;


    public AuthResponse register(RegisterRequest dto) {
        User user = User.builder()
                .username(dto.username())
                .password(dto.password())
                .role(dto.role())
                .build();
        User NewUser = userRepository.save(user);
        return AuthResponse.builder()
                .token("token")
                .build();
    }

    public AuthResponse login(LoginRequest dto) {
           log.info("Login request: {}", dto);

           UserDetails user = userRepository.findByUsername(dto.username())
                   .orElseThrow(() -> new IllegalArgumentException("User not found"));

            return AuthResponse.builder()
                    .token("login")
                    .build();
    }



}
