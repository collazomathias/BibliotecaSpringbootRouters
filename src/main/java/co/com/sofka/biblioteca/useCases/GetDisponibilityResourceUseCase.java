package co.com.sofka.biblioteca.useCases;

import reactor.core.publisher.Mono;


@FunctionalInterface
public interface GetDisponibilityResourceUseCase {

    Mono<String> isBorrowed(String id);
    
}
