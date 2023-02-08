package models.comparators;

import models.Route;

import java.util.Comparator;
import java.util.Objects;

public class RouteHashComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        if (o1.hashCode() > o2.hashCode()) return 1;
        else if (Objects.equals(o1, o2)) return 0;
        return -1;
    }
}
