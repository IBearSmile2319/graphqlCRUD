package dev.maicol.graphqlCRUD.service;

import dev.maicol.graphqlCRUD.entity.User;
import dev.maicol.graphqlCRUD.repository.UserRepository;
import dev.maicol.graphqlCRUD.request.LoginRequest;
import dev.maicol.graphqlCRUD.request.RegisterRequest;
import dev.maicol.graphqlCRUD.response.AuthResponse;
import dev.maicol.graphqlCRUD.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;


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

//           UserDetails user = userRepository.findByUsername(dto.username())
//                   .orElseThrow(() -> new IllegalArgumentException("User not found"));
        String token = jwtUtil.generateJwtToken(dto.username(), List.of("ADMIN"));

            return AuthResponse.builder()
                    .token(token)
                    .build();
    }



}
