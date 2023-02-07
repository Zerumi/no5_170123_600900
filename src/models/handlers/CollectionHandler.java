package models.handlers;

import models.Route;

import java.util.AbstractCollection;
import java.util.Date;

public interface CollectionHandler<T extends AbstractCollection<E>, E> {
    T getCollection();
    void setCollection(T value);
    void addElementToCollection(E value);
    void clearCollection();
    void sort();
    Route getFirstOrNew();
    Date getInitDate();

    Route getLastElement();
}
