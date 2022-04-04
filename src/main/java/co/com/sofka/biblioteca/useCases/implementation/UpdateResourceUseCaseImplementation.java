package co.com.sofka.biblioteca.useCases.implementation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.com.sofka.biblioteca.models.Mapper;
import co.com.sofka.biblioteca.models.ResourceDTO;
import co.com.sofka.biblioteca.repositories.ResourceRepository;
import co.com.sofka.biblioteca.useCases.SaveStringUseCase;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateResourceUseCaseImplementation implements SaveStringUseCase {
    private final ResourceRepository repository;
    private final Mapper mapper;

    public UpdateResourceUseCaseImplementation(ResourceRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> apply(ResourceDTO dto) {
        String id = dto.getId();
        return this.repository.findById(id)
            .flatMap(res -> {
                dto.setId(id);
                return this.repository.save(mapper.MapperToDTO(null).apply(dto))
                    .map(resource -> mapper.MapperToModel().apply(resource));
            })
            .switchIfEmpty(Mono.empty())
            .thenReturn(dto.toString());
    }
    
}
