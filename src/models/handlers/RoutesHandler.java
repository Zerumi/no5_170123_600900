package models.handlers;

import models.Route;
import models.comparators.RouteComparator;
import models.validators.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Current implementation of CollectionsHandler for HashSet of Route.
 *
 * @since 1.0
 * @author Zerumi
 */
public class RoutesHandler implements CollectionHandler<HashSet<Route>, Route> {

    private static RoutesHandler singletoneMoment;

    private HashSet<Route> routes;
    private final Date initDate;

    private RoutesHandler() {
        routes = new HashSet<>();
        initDate = Date.from(Instant.now());
    }

    /**
     * Singletone moment.
     *
     * @return Single instance of handler.
     */
    public static RoutesHandler getInstance() {
        if (singletoneMoment == null)
            singletoneMoment = new RoutesHandler();
        return singletoneMoment;
    }

    /**
     * Returns actual collection reference.
     *
     * @return Current collection
     */
    @Override
    public HashSet<Route> getCollection()
    {
        return routes;
    }

    /**
     * Overrides current collection by provided value.
     *
     * @param routes Collection
     */
    @Override
    public void setCollection(HashSet<Route> routes) {
        this.routes = routes;
        validateElements();
        sort();
    }

    /**
     * Adds element to collection.
     *
     * @param e Element to add
     */
    @Override
    public void addElementToCollection(Route e)
    {
        routes.add(e);
        sort();
    }

    @Override
    public void clearCollection() {
        routes.clear();
    }

    /**
     * Sorts elements by ID Field in Route.
     */
    @Override
    public void sort() {
        this.routes = routes.stream().sorted(new RouteComparator()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Returns first element of collection.
     * @return First element of collection. If collection is empty, returns new object.
     */
    @Override
    public Route getFirstOrNew()
    {
        if (routes.iterator().hasNext())
            return routes.iterator().next();
        else
            return new Route();
    }

    @Override
    public Date getInitDate() {
        return initDate;
    }

    /**
     * Returns last element of collection.
     * @return Last element of collection of null if collection is empty
     */
    @Override
    public Route getLastElement()
    {
        Route result = null;
        for (Route route : routes) {
            result = route;
        }
        return result;
    }

    /**
     *
     */
    @Override
    public void validateElements() {
        for (Iterator<Route> it = getCollection().iterator(); it.hasNext(); ) {
            Route toValid = it.next();
            Validator<Route> validator = new RouteValidator();

            if (!validator.validate(toValid))
            {
                it.remove();
                System.out.println("Element removed from collection: " + toValid);
                System.out.println("This element violates the restriction of some fields. Check your file and fix it manually.");
            }
        }
    }
}
