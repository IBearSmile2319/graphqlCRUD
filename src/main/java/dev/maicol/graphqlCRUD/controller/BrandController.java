package dev.maicol.graphqlCRUD.controller;

import dev.maicol.graphqlCRUD.entity.Brand;
import dev.maicol.graphqlCRUD.enums.Country;
import dev.maicol.graphqlCRUD.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BrandController {

    @Autowired
    BrandService brandService;

    @QueryMapping
    public List<Brand> findAllBrands() {
        return brandService.findAllBrands();
    }

    @QueryMapping
    public Brand findBrandById(@Argument Long id) {
        return brandService.findById(id);
    }

    @MutationMapping
    public Brand createBrand(@Argument String name,@Argument Country country) {
        return brandService.createBrand(name, country);
    }

    @MutationMapping
    public Brand updateBrand(@Argument Long id,@Argument String name,@Argument Country country) {
        return brandService.updateBrand(id, name, country);
    }

    @MutationMapping
    public Brand deleteBrand(@Argument Long id) {
        return brandService.deleteBrand(id);
    }


}
