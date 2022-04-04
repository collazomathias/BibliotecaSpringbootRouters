package co.com.sofka.biblioteca.useCases.implementation;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import co.com.sofka.biblioteca.models.ResourceModel;
import co.com.sofka.biblioteca.repositories.ResourceRepository;
import co.com.sofka.biblioteca.useCases.GetDisponibilityResourceUseCase;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Service
@Validated
public class GetDisponibilityResourceUseCaseImplementation implements GetDisponibilityResourceUseCase {
    
    private final ResourceRepository repository;

    public GetDisponibilityResourceUseCaseImplementation(ResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<String> isBorrowed(String id) {
        Mono<ResourceModel> resource = repository.findById(id);
        try {
            return resource.map(res -> {
                var resourceDisponibility = res.getDisponible() ? "The resource is no available from date " + res.getFechaPrestamo() : "The resource is available";
                return Mono.just(resourceDisponibility);
            })
            .toFuture()
            .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Mono.empty();
    }

}



