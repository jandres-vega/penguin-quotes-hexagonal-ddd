package com.jave.bookStoreQuotes.events;


import com.jave.generic.DomainEvent;

public class CreatedRoot  extends DomainEvent {

    public CreatedRoot() {
        super(TypeEvent.CREATED_ROOT.toString());
    }

}
