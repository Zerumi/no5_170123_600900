package models.comparators;

import models.Route;

import java.util.Comparator;
import java.util.Objects;

public class RouteComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        if (o1.getId() - o2.getId() < 0)
            return -1;
        else if (Objects.equals(o1.getId(), o2.getId()))
            return 0;
        else
            return 1;
    }
}
