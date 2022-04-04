package co.com.sofka.biblioteca.useCases.implementation;

import org.apache.logging.log4j.util.Supplier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.com.sofka.biblioteca.models.Mapper;
import co.com.sofka.biblioteca.models.ResourceDTO;
import co.com.sofka.biblioteca.repositories.ResourceRepository;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetAllResourcesUseCaseImplementation implements Supplier<Flux<ResourceDTO>> {
    
    private ResourceRepository repositorio;
    private Mapper mapper;

    public GetAllResourcesUseCaseImplementation(Mapper mapper, ResourceRepository repositorio) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Flux<ResourceDTO> get() {
        return repositorio.findAll().map(mapper.MapperToModel());
    }
    
}