package co.com.sofka.biblioteca.useCases;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteResourceUseCase {
    Mono<Void> get(String id);
}
