package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.models.ResourceDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveResourceUseCase {
    public Mono<ResourceDTO> apply(ResourceDTO datoDTO);
}
