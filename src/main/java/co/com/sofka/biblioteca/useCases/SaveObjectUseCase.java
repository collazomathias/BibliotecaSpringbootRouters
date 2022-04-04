package co.com.sofka.biblioteca.useCases;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveObjectUseCase {
    public Mono<Object> apply( String id);
}
