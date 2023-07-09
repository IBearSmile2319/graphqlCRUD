package dev.maicol.graphqlCRUD.context;

import graphql.servlet.context.DefaultGraphQLServletContext;
import graphql.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.servlet.context.GraphQLContext;
import graphql.servlet.context.GraphQLContextBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Component
@Slf4j
public class CustomGraphQLContextBuilder implements GraphQLContextBuilder {

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String Token = httpServletRequest.getHeader("Authorization");

        var context = DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .build();

        return new CustomGraphQLContext(Token, context);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext()
                .with(session)
                .with(handshakeRequest)
                .build();
    }

    @Override
    public GraphQLContext build() {
        throw  new IllegalStateException("Unsupported");
    }
}
