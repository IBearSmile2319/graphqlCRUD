package dev.maicol.graphqlCRUD.publisher;

import dev.maicol.graphqlCRUD.entity.Brand;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;

@Component
@Slf4j
public class BrandPublisher {

    private final FluxProcessor<Brand, Brand> brandProcessor;
    private final FluxSink<Brand> brandSink;

    public BrandPublisher() {
        this.brandProcessor = DirectProcessor.<Brand>create().serialize();
        this.brandSink = brandProcessor.sink();

    }

    public void publish(Brand brand) {
        log.info("Publishing brand {}", brand);
        brandSink.next(brand);
    }

    public Publisher<Brand> getBrandPublisher() {
        return brandProcessor.map(brand -> {
            log.info("Mapping brand {}", brand);
            return brand;
        });
    }

    public Publisher<Brand> getBrandPublisherFor(Long id) {
        return brandProcessor
                .filter(brand -> {
            log.info("Filtering brand {}", brand);
            return id.equals(brand.getId());
        }).map(brand -> {
            log.info("Mapping brand for ID {}", brand);
            return brand;
        });
    }


}
