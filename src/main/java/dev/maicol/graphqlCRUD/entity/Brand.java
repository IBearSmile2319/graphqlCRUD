package dev.maicol.graphqlCRUD.entity;

import dev.maicol.graphqlCRUD.enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Country country;
    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<Model> models;

}
