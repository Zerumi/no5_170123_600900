package models.handlers;

import models.Route;
import models.validators.IdValidator;
import models.validators.Validator;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for handling Route objects. Contains static methods.
 *
 * @since 1.1
 * @author zerumi
 */
public class RouteHandlers {

    /**
     * Generates unique ID for Route Object.
     *
     * @return value for ID field.
     */
    public static Long generateID()
    {
        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();
        // id
        Validator<Long> idValidator = new IdValidator();
        var lastObj = handler.getLastElement();
        long lastId = 1L;
        if (lastObj != null)
        {
            lastId = lastObj.getId() + 1;
        }
        while (!idValidator.validate(lastId))
        {
            lastId = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        }
        System.out.println("ID Field (auto-generated): " + lastId);
        return lastId;
    }
}
