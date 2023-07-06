package dev.maicol.graphqlCRUD.controller;

import dev.maicol.graphqlCRUD.entity.Model;
import dev.maicol.graphqlCRUD.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ModelController {
    @Autowired
    ModelService modelService;

    @QueryMapping
    public List<Model> findAllModels() {
        return modelService.findAllModels();
    }

    @QueryMapping
    public Model findModelById(@Argument Long id) {
        return modelService.findById(id);
    }

    @MutationMapping
    public Model createModel(@Argument String name, @Argument Long brandId) {
        return modelService.createModel(name, brandId);
    }

    @MutationMapping
    public Model updateModel(@Argument Long id,@Argument String name) {
        return modelService.updateModel(id, name);
    }

    @MutationMapping
    public Model deleteModel(@Argument Long id) {
        return modelService.deleteModel(id);
    }

}
