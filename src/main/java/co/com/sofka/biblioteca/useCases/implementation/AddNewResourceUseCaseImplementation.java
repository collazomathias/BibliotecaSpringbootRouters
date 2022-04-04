package co.com.sofka.biblioteca.useCases.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.com.sofka.biblioteca.models.Mapper;
import co.com.sofka.biblioteca.models.ResourceDTO;
import co.com.sofka.biblioteca.models.ResourceModel;
import co.com.sofka.biblioteca.repositories.ResourceRepository;
import co.com.sofka.biblioteca.useCases.SaveStringUseCase;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddNewResourceUseCaseImplementation implements SaveStringUseCase {
    
    private ResourceRepository repositorio;
    private Mapper mapper;
    
    @Autowired
    public AddNewResourceUseCaseImplementation(Mapper mapper, ResourceRepository repositorio) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> apply(ResourceDTO dto) {
        return repositorio.save(mapper.MapperToDTO(null).apply(dto)).map(ResourceModel::getId).thenReturn(dto.toString());
    }
}