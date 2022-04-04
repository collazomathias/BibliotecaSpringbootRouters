package co.com.sofka.biblioteca.useCases.implementation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.com.sofka.biblioteca.repositories.ResourceRepository;
import co.com.sofka.biblioteca.useCases.GetDisponibilityResourceUseCase;
import reactor.core.publisher.Mono;

@Service
@Validated
public class GiveBackResourceUseCaseImplementation  implements GetDisponibilityResourceUseCase {
    
    private ResourceRepository repository;

    public GiveBackResourceUseCaseImplementation(ResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<String> isBorrowed(String id) {
        return repository.findById(id).flatMap(resource -> {
            if(!resource.getDisponible() ) { //si no esta prestado, no lo puedo devolver
                return Mono.just("It cannot be returned if it is not borrowed.");
            }
            resource.setDisponible(false);
            return repository.save(resource).thenReturn("Resource returned successfully.!");
        });
    }

}
