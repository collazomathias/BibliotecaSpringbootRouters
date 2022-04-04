package co.com.sofka.biblioteca.services;
import java.util.concurrent.ExecutionException;

import co.com.sofka.biblioteca.models.ResourceModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface ResourceService {
    public Mono<ResourceModel> getResourceById(String id);
    public Mono<String> isBorrowed(String id);
    public Mono<String> preConsult(Mono<ResourceModel> resource) throws InterruptedException, ExecutionException;
    public Mono<Object> lendResource(String id);
    public Flux<ResourceModel> getAllResources();
    public Mono<Void> deleteResource(String id);
    public Mono<ResourceModel> addNewResource(ResourceModel resource);
    public Mono<ResourceModel> updateResource(String id, ResourceModel resource);
    public Flux<ResourceModel> recommendResourceByTypeAndArea(String type,String area);
    public Mono<Object> giveBackResource(String id);
}