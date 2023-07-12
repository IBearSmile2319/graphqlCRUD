package dev.maicol.graphqlCRUD.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CarsExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

        return new GraphQLError() {
            @Override
            public String getMessage() {
                log.error("Error: {}", ex.getMessage());
                return ex.getMessage();
            }

            @Override
            public List<SourceLocation> getLocations() {
                return null;
            }

            @Override
            public ErrorClassification getErrorType() {
                return ErrorType.BAD_REQUEST;
            }

            @Override
            public Map<String, Object> getExtensions() {
                Map<String, Object> attr = new LinkedHashMap<>();
                attr.put("message", HttpStatus.BAD_REQUEST.value());
                return attr;
            }

        };
    }
}
