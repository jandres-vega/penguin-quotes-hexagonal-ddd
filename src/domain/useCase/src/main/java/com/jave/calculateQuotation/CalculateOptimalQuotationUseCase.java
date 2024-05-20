package com.jave.calculateQuotation;

import com.jave.bookStoreQuotes.BookStoreQuotes;
import com.jave.bookStoreQuotes.commands.CalculateOptimalPurchaseCommand;
import com.jave.bookStoreQuotes.values.identities.BookStoreQuoteId;
import com.jave.gateways.DomainEventRepository;
import com.jave.generic.CommandUseCase;
import com.jave.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class CalculateOptimalQuotationUseCase extends CommandUseCase<CalculateOptimalPurchaseCommand> {

    private final DomainEventRepository repository;

    public CalculateOptimalQuotationUseCase(@Qualifier("mongoRepositoryAdapter")  DomainEventRepository repository) {
        this.repository = repository;
    }
    @Override
    public Flux<DomainEvent> apply(Mono<CalculateOptimalPurchaseCommand> commandMono) {
        return commandMono.flatMapMany(command -> repository.findById(command.getBookStoreQuoteId())
                .collectList()
                .flatMapIterable(events -> {
                    BookStoreQuoteId bookStoreQuoteId = BookStoreQuoteId.of(command.getBookStoreQuoteId());
                    BookStoreQuotes bookStoreQuotes = BookStoreQuotes.from(bookStoreQuoteId, events);
                    bookStoreQuotes.calculateOptimalPurchase(command.getCopies(), command.getBudget());
                    return bookStoreQuotes.getUncommittedChanges();
                })
                .map(event -> event)
                .flatMap(repository::saveEvent)
        );

    }
}
