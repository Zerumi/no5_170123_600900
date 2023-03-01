package models.comparators;

import models.Route;

import java.util.Comparator;
import java.util.Objects;

/**
 * Compare two Routes by ID (default used)
 *
 * @since 1.0
 * @author Zerumi
 */
public class RouteComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
}
