package dev.maicol.graphqlCRUD.controller;

import dev.maicol.graphqlCRUD.entity.Brand;
import dev.maicol.graphqlCRUD.publisher.BrandPublisher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BrandSubscription {

    private final BrandPublisher brandPublisher;

    @SubscriptionMapping
    public Publisher<Brand> brandList(DataFetchingEnvironment environment) {
        return brandPublisher.getBrandPublisher();
    }

    @SubscriptionMapping
    public Publisher<Brand> brandById(@Argument Long id) {
        return brandPublisher.getBrandPublisherFor(id);
    }


}
