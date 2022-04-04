package co.com.sofka.biblioteca.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.sofka.biblioteca.models.ResourceDTO;
import co.com.sofka.biblioteca.useCases.implementation.AddNewResourceUseCaseImplementation;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AddNewResourceRouter {
    @Bean
    public RouterFunction<ServerResponse> addNewResource(AddNewResourceUseCaseImplementation useCase) {
        return route(POST("/resources").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ResourceDTO.class)
                        .flatMap(dto -> useCase.apply(dto)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.TEXT_PLAIN)
                                        .bodyValue(result))
                        )
        );
    }
}