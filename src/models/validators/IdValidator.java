package models.validators;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * Checks for Route ID restrictions.
 *
 * @since 1.0
 * @see Route
 * @author zerumi
 */
public class IdValidator implements Validator<Long> {

    TreeSet<Long> ids;

    public IdValidator()
    {
        ids = new TreeSet<>();

        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();

        handler.getCollection().forEach((value) -> {
            ids.add(value.getId());
        });
    }

    @Override
    public boolean validate(Long value) {

        if (value == null) return false;
        if (value <= 0) return false;
        return ids.add(value);
    }
}
