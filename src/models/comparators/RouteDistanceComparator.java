package models.comparators;

import models.Route;

import java.util.Comparator;
import java.util.Objects;

/**
 * Compare two Routes by Distance. Used in commands, such as remove_greater, add_if max/min
 *
 * @author Zerumi
 * @since 1.0
 */
public class RouteDistanceComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        return o1.getDistance() - o2.getDistance();
    }
}
