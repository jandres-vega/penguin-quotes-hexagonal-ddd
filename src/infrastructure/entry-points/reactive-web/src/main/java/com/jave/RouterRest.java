package com.jave;

import com.jave.bookStoreQuotes.commands.CalculateOptimalPurchaseCommand;
import com.jave.bookStoreQuotes.commands.CreateRootCommand;
import com.jave.bookStoreQuotes.commands.SaveBookCommand;
import com.jave.calculateQuotation.CalculateOptimalQuotationUseCase;
import com.jave.generic.DomainEvent;
import com.jave.getBooksUseCase.GetBooksUseCase;
import com.jave.initialRootUseCase.InitialRootUseCase;
import com.jave.saveCopyUseCase.SaveCopyUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RouterRest {

    private final SaveCopyUseCase saveCopyUseCase;
    private final GetBooksUseCase getBooksUseCase;
    private final InitialRootUseCase initialRootUseCase;
    private final CalculateOptimalQuotationUseCase calculateOptimalQuotationUseCase;

    public RouterRest(SaveCopyUseCase saveCopyUseCase, GetBooksUseCase getBooksUseCase, InitialRootUseCase initialRootUseCase, CalculateOptimalQuotationUseCase calculateOptimalQuotationUseCase) {
        this.saveCopyUseCase = saveCopyUseCase;
        this.getBooksUseCase = getBooksUseCase;
        this.initialRootUseCase = initialRootUseCase;
        this.calculateOptimalQuotationUseCase = calculateOptimalQuotationUseCase;
    }

    @PostMapping("/init-root")
    public Mono<ResponseEntity<List<DomainEvent>>> initRoot(){
        return initialRootUseCase.apply(Mono.just(new CreateRootCommand()))
                .collectList()
                .map(ResponseEntity::ok);
    }

    @PostMapping("/save-copy")
    public Mono<ResponseEntity<List<DomainEvent>>> saveCopy(@RequestBody SaveBookCommand command){
        return saveCopyUseCase.apply(Mono.just(command))
                .collectList()
                .map(ResponseEntity::ok);
    }

    @GetMapping("/")
    public Flux<DomainEvent> getBooks(){
        return getBooksUseCase.execute();
    }

    @PostMapping("/send-quotation")
    public Mono<ResponseEntity<List<DomainEvent>>> calculateOptimalPurchase(@RequestBody CalculateOptimalPurchaseCommand command){
        return calculateOptimalQuotationUseCase.apply(Mono.just(command))
                .collectList()
                .map(ResponseEntity::ok);
    }
}
