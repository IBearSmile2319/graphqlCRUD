package dev.maicol.graphqlCRUD.service;

import dev.maicol.graphqlCRUD.entity.Brand;
import dev.maicol.graphqlCRUD.entity.Model;
import dev.maicol.graphqlCRUD.enums.Country;
import dev.maicol.graphqlCRUD.repository.BrandRepository;
import dev.maicol.graphqlCRUD.repository.ModelRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ModelRepository modelRepository;

    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }

    public Brand findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Brand createBrand(String name, Country country) {
        Brand brand = Brand.builder().name(name).country(country).build();
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, String name, Country country) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("id no existe"));
        brand.setName(name);
        brand.setCountry(country);
        return brandRepository.save(brand);
    }

    public Brand deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("id no existe"));
        brandRepository.delete(brand);
        return brand;
    }


    @PostConstruct
    private void loadData(){
        createBrand("Ferrari", Country.ITALY);
        createBrand("Lamborghini", Country.ITALY);
        createBrand("Porsche", Country.GERMANY);
        createBrand("Audi", Country.GERMANY);
        createBrand("BMW", Country.GERMANY);
        createBrand("Mercedes", Country.GERMANY);
        createBrand("Ford", Country.USA);
    }
}
