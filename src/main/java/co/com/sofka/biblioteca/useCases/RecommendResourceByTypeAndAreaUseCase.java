package co.com.sofka.biblioteca.useCases;

import co.com.sofka.biblioteca.models.ResourceDTO;
import reactor.core.publisher.Flux;


@FunctionalInterface
public interface RecommendResourceByTypeAndAreaUseCase {
    public Flux<ResourceDTO> apply(String tipo, String area);
}
