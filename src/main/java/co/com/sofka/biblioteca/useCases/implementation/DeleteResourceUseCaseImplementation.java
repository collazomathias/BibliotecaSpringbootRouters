package co.com.sofka.biblioteca.useCases.implementation;

import org.springframework.stereotype.Service;

import java.util.Objects;
import org.springframework.validation.annotation.Validated;

import co.com.sofka.biblioteca.repositories.ResourceRepository;
import co.com.sofka.biblioteca.useCases.DeleteResourceUseCase;
import reactor.core.publisher.Mono;

@Service
@Validated
public class DeleteResourceUseCaseImplementation implements DeleteResourceUseCase {
   
    private ResourceRepository repository;

    public DeleteResourceUseCaseImplementation(ResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> get(String id) {
        if(Objects.isNull(id)){
            return Mono.empty();
        }
        return repository.deleteById(id);
    }

}
