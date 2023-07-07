package dev.maicol.graphqlCRUD.service;

import dev.maicol.graphqlCRUD.dto.BrandDTO;
import dev.maicol.graphqlCRUD.entity.Brand;
import dev.maicol.graphqlCRUD.enums.Country;
import dev.maicol.graphqlCRUD.repository.BrandRepository;
import dev.maicol.graphqlCRUD.repository.ModelRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

        return brandRepository.findById(id).orElseThrow(() -> new RuntimeException("id no existe"));
    }

    public Brand createBrand(BrandDTO brandDTO) {

        Brand brand = Brand.builder().name(brandDTO.getName()).country(brandDTO.getCountry()).build();
        return brandRepository.save(brand);
    }

    public Brand updateBrand(Long id, BrandDTO brandDTO) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("id no existe"));
        brand.setName(brandDTO.getName());
        brand.setCountry(brandDTO.getCountry());
        return brandRepository.save(brand);
    }

    public Brand deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("id no existe"));
        brandRepository.delete(brand);
        return brand;
    }

    //    suscription

    public Flux<Brand> findAllBrandsFlux() {
        return Flux.fromStream(brandRepository.findAll().stream())
                .delayElements(java.time.Duration.ofSeconds(1)).take(10);
    }

    public Mono<Brand> findByIdMono(Long id) {
        return Mono.justOrEmpty(brandRepository.findById(id))
                .switchIfEmpty(Mono.error(new RuntimeException("id no existe")));
    }

    @PostConstruct
    private void loadData(){
//        createBrand("Ferrari", Country.ITALY);
//        createBrand("Lamborghini", Country.ITALY);
//        createBrand("Porsche", Country.GERMANY);
//        createBrand("Audi", Country.GERMANY);
//        createBrand("BMW", Country.GERMANY);
//        createBrand("Mercedes", Country.GERMANY);
//        createBrand("Ford", Country.USA);
        createBrand(new BrandDTO("Ferrari", Country.ITALY));
        createBrand(new BrandDTO("Lamborghini", Country.ITALY));
        createBrand(new BrandDTO("Porsche", Country.GERMANY));
        createBrand(new BrandDTO("Audi", Country.GERMANY));
        createBrand(new BrandDTO("BMW", Country.GERMANY));
        createBrand(new BrandDTO("Mercedes", Country.GERMANY));
        createBrand(new BrandDTO("Ford", Country.USA));
    }
}
