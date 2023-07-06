package dev.maicol.graphqlCRUD.service;

import dev.maicol.graphqlCRUD.entity.Brand;
import dev.maicol.graphqlCRUD.entity.Model;
import dev.maicol.graphqlCRUD.repository.BrandRepository;
import dev.maicol.graphqlCRUD.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    @Autowired
    ModelRepository modelRepository;

    @Autowired
    BrandRepository brandRepository;

    public List<Model> findAllModels() {
        return modelRepository.findAll();
    }

    public Model findById(Long id) {
        return modelRepository.findById(id).orElse(null);
    }

    public Model createModel(String name, Long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new RuntimeException("id no existe"));
        Model model = Model.builder().name(name).brand(brand).build();
        return modelRepository.save(model);
    }

    public Model updateModel(Long id, String name) {

        Model model = modelRepository.findById(id).orElseThrow(() -> new RuntimeException("id no existe"));
        model.setName(name);
        return modelRepository.save(model);
    }

    public Model deleteModel(Long id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new RuntimeException("id no existe"));
        modelRepository.delete(model);
        return model;
    }

}
