package models.validators;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * Implementation of validator for ID field. (Route)
 *
 * @since 1.0
 * @author zerumi
 */
public class IdValidator implements Validator<Long> {

    TreeSet<Long> ids;


    /**
     * Setup validator default constructor
     */
    public IdValidator()
    {
        ids = new TreeSet<>();

        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();

        handler.getCollection().forEach((value) -> ids.add(value.getId()));
    }

    /**
     * Checks if value unique in collection, greater than 0 and notnull.
     *
     * @see models.Route
     * @param value ID to validate
     * @return true/false -- matches the restrictions
     */
    @Override
    public boolean validate(Long value) {

        if (value == null) return false;
        if (value <= 0) return false;
        return ids.add(value);
    }
}
