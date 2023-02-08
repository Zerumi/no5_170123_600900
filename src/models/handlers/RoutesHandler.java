package models.handlers;

import models.Route;
import models.comparators.RouteComparator;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class RoutesHandler implements CollectionHandler<HashSet<Route>, Route> {

    private static RoutesHandler singletoneMoment;

    private HashSet<Route> routes;
    private Date initDate;

    private RoutesHandler() {
        routes = new HashSet<>();
        initDate = Date.from(Instant.now());
    }

    public static RoutesHandler getInstance() {
        if (singletoneMoment == null)
            singletoneMoment = new RoutesHandler();
        return singletoneMoment;
    }

    @Override
    public HashSet<Route> getCollection()
    {
        return routes;
    }

    @Override
    public void setCollection(HashSet<Route> routes) {
        this.routes = routes;
        sort();
    }

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
        var iterator = routes.iterator();
        while (iterator.hasNext())
        {
            result = iterator.next();
        }
        return result;
    }
}
