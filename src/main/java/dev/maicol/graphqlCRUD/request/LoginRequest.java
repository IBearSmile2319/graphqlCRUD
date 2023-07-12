package dev.maicol.graphqlCRUD.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public record LoginRequest(
    String username,
    String password
) {
}
