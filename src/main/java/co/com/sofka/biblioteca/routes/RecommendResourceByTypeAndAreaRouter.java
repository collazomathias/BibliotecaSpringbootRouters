package co.com.sofka.biblioteca.routes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.sofka.biblioteca.models.ResourceDTO;
import co.com.sofka.biblioteca.useCases.implementation.RecommendResourceByTypeAndAreaUseCaseImplementation;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
public class RecommendResourceByTypeAndAreaRouter {
    @Bean
    public RouterFunction<ServerResponse> recomendarRecurso(RecommendResourceByTypeAndAreaUseCaseImplementation useCase){

        return route(GET("/resources/{type}/{area}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(
                    useCase.apply(request.pathVariable("type"), 
                            request.pathVariable("area")), 
                            ResourceDTO.class)));
    }
}
