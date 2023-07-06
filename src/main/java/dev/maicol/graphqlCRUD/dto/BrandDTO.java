package dev.maicol.graphqlCRUD.dto;

import dev.maicol.graphqlCRUD.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandDTO {
    private String name;
    private Country country;
}
