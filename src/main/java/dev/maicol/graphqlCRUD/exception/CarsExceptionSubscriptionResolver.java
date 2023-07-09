package dev.maicol.graphqlCRUD.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.SubscriptionExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CarsExceptionSubscriptionResolver extends SubscriptionExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable exception) {
        return new GraphQLError() {
            @Override
            public String getMessage() {
                log.error("Error Subscription: {}", exception.getMessage());
                return exception.getMessage();
            }

            @Override
            public List<SourceLocation> getLocations() {
                log.error("Error Subscription Location: {}", exception.getMessage());
                return null;
            }

            @Override
            public ErrorClassification getErrorType() {
                log.error("Error Subscription Type: {}", exception.getMessage());
                return null;
            }
        };
    }
}
