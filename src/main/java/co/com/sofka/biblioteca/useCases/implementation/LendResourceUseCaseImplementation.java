package co.com.sofka.biblioteca.useCases.implementation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.com.sofka.biblioteca.repositories.ResourceRepository;
import co.com.sofka.biblioteca.useCases.GetDisponibilityResourceUseCase;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Validated
public class LendResourceUseCaseImplementation implements GetDisponibilityResourceUseCase {

    private final ResourceRepository repository;

    public LendResourceUseCaseImplementation(ResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<String> isBorrowed(String id) {
        return repository.findById(id).flatMap(resource -> {
            if(resource.getDisponible() ) {
                return Mono.just("The resource is on loan, sorry.");
            }
            resource.setDisponible(true);
            LocalDate today = LocalDate.now();
            resource.setFechaPrestamo(today.toString());
            return repository.save(resource).thenReturn("The resource was successfully lent.");
        });
    }
}
