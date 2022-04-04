package co.com.sofka.biblioteca.useCases.implementation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.com.sofka.biblioteca.models.Mapper;
import co.com.sofka.biblioteca.models.ResourceDTO;
import co.com.sofka.biblioteca.repositories.ResourceRepository;
import co.com.sofka.biblioteca.useCases.RecommendResourceByTypeAndAreaUseCase;
import reactor.core.publisher.Flux;

@Service
@Validated
public class RecommendResourceByTypeAndAreaUseCaseImplementation implements RecommendResourceByTypeAndAreaUseCase {

    private ResourceRepository repository;
    private Mapper mapper;
    
    public RecommendResourceByTypeAndAreaUseCaseImplementation(ResourceRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    
    @Override
    public Flux<ResourceDTO> apply(String tipo, String area) {
        return this.repository.findByTipo(tipo).map(resource -> {
            return resource;
        })
        .filter(resource -> resource.getTema().equals(area))
        .map(resource -> mapper.MapperToModel().apply(resource));
    }

}
