package com.jave.initialRootUseCase;

import com.jave.bookStoreQuotes.BookStoreQuotes;
import com.jave.bookStoreQuotes.commands.CreateRootCommand;
import com.jave.gateways.DomainEventRepository;
import com.jave.generic.CommandUseCase;
import com.jave.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class InitialRootUseCase extends CommandUseCase<CreateRootCommand> {

    private final DomainEventRepository repository;

    @Autowired
    public InitialRootUseCase(DomainEventRepository domainEventRepository) {
        this.repository = domainEventRepository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CreateRootCommand> createRootCommandMono) {
        var root = new BookStoreQuotes();
        var events = root.getUncommittedChanges();
        return Flux.fromIterable(events)
                .map(event -> event)
                .flatMap(repository::saveEvent)
                .doOnComplete(root::markChangeAsCommitted);
    }
}
