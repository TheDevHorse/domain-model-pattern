package com.thedevhorse.domainmodelpattern.domain.base;

public abstract class AbstractAggregateRoot<T>  extends AbstractEntity<T> {
    protected AbstractAggregateRoot(T id) {
        super(id);
    }
}
