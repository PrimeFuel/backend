package com.primefuel.fulltank.platform.shared.domain.model.aggregates;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Collection;

@NullMarked
public abstract class AbstractDomainAggregateRoot<T extends AbstractDomainAggregateRoot<T>>
        extends AbstractAggregateRoot<T> {

    protected void registerDomainEvent(Object event) {
        super.registerEvent(event);
    }

    @Override
    public Collection<Object> domainEvents() {
        return super.domainEvents();
    }

    @Override
    public void clearDomainEvents() {
        super.clearDomainEvents();
    }
}
