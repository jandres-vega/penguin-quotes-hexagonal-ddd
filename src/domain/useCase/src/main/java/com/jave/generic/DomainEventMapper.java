package com.jave.generic;

public interface DomainEventMapper<T extends DomainEvent, R> {
    R map(T domainEvent);
}
