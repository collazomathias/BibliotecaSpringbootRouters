package co.com.sofka.biblioteca.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.sofka.biblioteca.useCases.implementation.GetDisponibilityResourceUseCaseImplementation;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
public class GetDisponibilityResourceRouter {
    @Bean
    public RouterFunction<ServerResponse> disponibilidadRecurso(GetDisponibilityResourceUseCaseImplementation useCase){
        return route(GET("/resources/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.isBorrowed(request.pathVariable("id")), String.class))
                        .onErrorResume(error -> ServerResponse.badRequest().build()));
    }
}
