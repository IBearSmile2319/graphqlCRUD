package dev.maicol.graphqlCRUD.context;

import graphql.servlet.context.GraphQLContext;
import graphql.servlet.context.GraphQLServletContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoaderRegistry;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class CustomGraphQLContext implements GraphQLServletContext {

    private final String token;
    private final GraphQLServletContext context;

    @Override
    public List<Part> getFileParts() {
        return context.getFileParts();
    }

    @Override
    public Map<String, List<Part>> getParts() {
        return context.getParts();
    }

    @Override
    public HttpServletRequest getHttpServletRequest() {
        return context.getHttpServletRequest();
    }

    @Override
    public HttpServletResponse getHttpServletResponse() {
        return context.getHttpServletResponse();
    }

    @Override
    public Optional<Subject> getSubject() {
        return context.getSubject();
    }

    @Override
    public Optional<DataLoaderRegistry> getDataLoaderRegistry() {
        return context.getDataLoaderRegistry();
    }
}
