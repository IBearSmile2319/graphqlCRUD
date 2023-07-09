package dev.maicol.graphqlCRUD.controller;

import dev.maicol.graphqlCRUD.context.CustomGraphQLContext;
import dev.maicol.graphqlCRUD.dto.BrandDTO;
import dev.maicol.graphqlCRUD.entity.Brand;
import dev.maicol.graphqlCRUD.enums.Country;
import dev.maicol.graphqlCRUD.publisher.BrandPublisher;
import dev.maicol.graphqlCRUD.service.BrandService;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Clock;
import java.util.List;

@Controller
@Slf4j
public class BrandController {

    @Autowired
    BrandService brandService;

    @QueryMapping
    public List<Brand> findAllBrands() {
        return brandService.findAllBrands();
    }

    @QueryMapping
    public Brand findBrandById(@Argument Long id, DataFetchingEnvironment environment) {
//        CustomGraphQLContext context = environment.getContext();
//        log.info("context: {}", context);
        var context = environment.getContext();
        log.info("context: {}", context);
        return brandService.findById(id);
    }

    @MutationMapping
    public Brand createBrand(@Argument BrandDTO dto) {
        return brandService.createBrand(dto);
    }

    @MutationMapping
    public Brand updateBrand(@Argument Long id, @Argument BrandDTO dto) {
        return brandService.updateBrand(id, dto);
    }

    @MutationMapping
    public Brand deleteBrand(@Argument Long id) {
        return brandService.deleteBrand(id);
    }

    //    suscription
    @SubscriptionMapping
    public Flux<Brand> findAllBrandsFlux() {
        return brandService.findAllBrandsFlux();
    }

    @SubscriptionMapping
    public Mono<Brand> findByIdMono(@Argument Long id) {
        return brandService.findByIdMono(id);
    }

}
