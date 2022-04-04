package co.com.sofka.biblioteca.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.sofka.biblioteca.models.ResourceDTO;
import co.com.sofka.biblioteca.useCases.implementation.GetAllResourcesUseCaseImplementation;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetAllResourcesRouter {
    @Bean
    public RouterFunction<ServerResponse> getAll(GetAllResourcesUseCaseImplementation useCase) {
        return route(
                GET("/resources").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), ResourceDTO.class))
        );
    }



}