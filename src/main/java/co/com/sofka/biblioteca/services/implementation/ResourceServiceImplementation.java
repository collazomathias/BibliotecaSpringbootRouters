package co.com.sofka.biblioteca.services.implementation;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sofka.biblioteca.models.ResourceModel;
import co.com.sofka.biblioteca.repositories.ResourceRepository;
import co.com.sofka.biblioteca.services.ResourceService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ResourceServiceImplementation implements ResourceService {

    @Autowired
    ResourceRepository repository;

    @Override
    public Mono<ResourceModel> getResourceById(String id) { 
        return this.repository.findById(id);
    }

    @Override
    public Mono<String> isBorrowed(String id) {
        Mono<ResourceModel> resource = repository.findById(id);
        try {
            return preConsult(resource);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Mono.empty();
    }

    @Override
    public  Mono<String> preConsult(Mono<ResourceModel> resource) throws InterruptedException, ExecutionException {
        return resource.map(res -> {
            var resourceDisponibility = res.getDisponible() ? "The resource is no available from date " + res.getFechaPrestamo() : "The resource is available";
            return Mono.just(resourceDisponibility);
        })
        .toFuture()
        .get();
    }

    @Override
    public Mono<Object> lendResource(String id) {
        return repository.findById(id).flatMap(resource -> {
            if(resource.getDisponible() ) {
                return Mono.just("The resource is on loan, sorry.");
            }
            resource.setDisponible(true);
            LocalDate today = LocalDate.now();
            resource.setFechaPrestamo(today.toString());
            return repository.save(resource);    
        });
    }

    @Override
    public Mono<Void> deleteResource(String id) {
        return this.repository.deleteById(id);
    }

    @Override
    public Mono<ResourceModel> addNewResource(ResourceModel resource) {
        return this.repository.save(resource);
    }

    @Override
    public Flux<ResourceModel> recommendResourceByTypeAndArea(String type, String area) {
        return this.repository.findByTipo(type).map(resource -> {
            return resource;
        })
        .filter(resource -> resource.getTema().equals(area));
    }

    @Override
    public Mono<Object> giveBackResource(String id) {
        return repository.findById(id).flatMap(resource -> {
            if(!resource.getDisponible()) {
                return Mono.just("The resource has already been given back.");
            }
            resource.setDisponible(false);
            return repository.save(resource);  
         });
    }

    @Override
    public Flux<ResourceModel> getAllResources() {
        return this.repository.findAll();
    }

    @Override
    public Mono<ResourceModel> updateResource(String id, ResourceModel resource) {
        return this.repository.findById(id)
            .flatMap(res -> {
                resource.setId(id);
                return addNewResource(resource);
            })
            .switchIfEmpty(Mono.empty());
    }
}
