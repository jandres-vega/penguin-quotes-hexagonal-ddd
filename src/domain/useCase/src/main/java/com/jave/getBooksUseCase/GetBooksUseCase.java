package com.jave.getBooksUseCase;


import com.jave.bookStoreQuotes.commands.CalculateOptimalPurchaseCommand;
import com.jave.gateways.DomainEventRepository;
import com.jave.generic.CommandUseCase;
import com.jave.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GetBooksUseCase extends CommandUseCase<CalculateOptimalPurchaseCommand> {

    private final DomainEventRepository repository;

    @Autowired
    public GetBooksUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }


    public Flux<DomainEvent> execute() {
        var copies = repository.findById("bd7394eb-3d5a-426e-9bec-a05c4d45a1cc");
        System.out.println(copies);
        return Flux.from(copies);
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CalculateOptimalPurchaseCommand> calculateOptimalPurchaseCommandMono) {
        return null;
    }
}
