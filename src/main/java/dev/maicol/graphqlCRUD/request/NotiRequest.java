package dev.maicol.graphqlCRUD.request;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotiRequest {
    // array de tokens
    private List<String> tokens;
    private String titulo;
    private String mensaje;
}
