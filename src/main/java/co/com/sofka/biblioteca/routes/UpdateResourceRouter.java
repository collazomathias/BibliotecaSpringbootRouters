package co.com.sofka.biblioteca.routes;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.sofka.biblioteca.models.ResourceDTO;
import co.com.sofka.biblioteca.useCases.implementation.UpdateResourceUseCaseImplementation;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
public class UpdateResourceRouter {
    @Bean
    public RouterFunction<ServerResponse> update(UpdateResourceUseCaseImplementation useCase){
        return route(PUT("/resources/update/{id}").and(accept(MediaType.APPLICATION_JSON)), request -> request
                        .bodyToMono(ResourceDTO.class)
                        .flatMap(dto -> useCase.apply(dto)
                            .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result))));
    }
}
