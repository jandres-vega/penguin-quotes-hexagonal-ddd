package com.jave.calculateQuotation;

import com.jave.bookStoreQuotes.BookStoreQuotes;
import com.jave.bookStoreQuotes.commands.CalculateOptimalPurchaseCommand;
import com.jave.bookStoreQuotes.commands.CalculateQuotationCommand;
import com.jave.bookStoreQuotes.events.QuotationCreated;
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


    public CalculateOptimalQuotationUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }


    @Override
    public Flux<?> apply(Mono<CalculateOptimalPurchaseCommand> commandMono) {
        QuotationCreatedMapper mapper = new QuotationCreatedMapper();

        return commandMono.flatMapMany(command -> repository.findById(command.getBookStoreQuoteId())
                .collectList()
                .flatMapIterable(events -> {
                    BookStoreQuoteId bookStoreQuoteId = BookStoreQuoteId.of(command.getBookStoreQuoteId());
                    BookStoreQuotes bookStoreQuotes = BookStoreQuotes.from(bookStoreQuoteId, events);
                    bookStoreQuotes.calculateQuotation(command.getCopies(), command.getCustomerCreatedDate());
                    return bookStoreQuotes.getUncommittedChanges();
                })
                .flatMap(event -> repository.saveEvent(event).thenReturn(event))
                .<QuotationCreated>handle((event, sink) -> {
                    if (event != null) {
                        sink.next((QuotationCreated) event);
                    } else {
                        sink.error(new IllegalArgumentException("Evento no soportado: " + event.getClass().getName()));
                    }
                })
                .map(mapper::map)
        );
    }
}
