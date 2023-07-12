package dev.maicol.graphqlCRUD.request;

import dev.maicol.graphqlCRUD.enums.Role;


public record RegisterRequest(
    String username,
    String password,
    Role role
) {
}
