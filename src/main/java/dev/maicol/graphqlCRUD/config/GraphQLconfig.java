package dev.maicol.graphqlCRUD.config;

import dev.maicol.graphqlCRUD.interpolator.CustomValidationMessageInterpolator;
import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
@RequiredArgsConstructor
public class GraphQLconfig {

    private final CustomValidationMessageInterpolator customValidationMessageInterpolator;

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        ValidationRules validationRules = ValidationRules.newValidationRules()
                .onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
                .messageInterpolator(customValidationMessageInterpolator)
                .build();
        ValidationSchemaWiring validationSchemaWiring = new ValidationSchemaWiring(validationRules);

        return builder -> builder.directiveWiring(validationSchemaWiring).build();
    }

}
