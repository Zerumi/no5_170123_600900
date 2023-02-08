package commandManager.commands;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;

/**
 * Clears collection
 *
 * @since 1.0
 * @author Zerumi
 */
public class ClearCommand implements BaseCommand {
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescr() {
        return "Clears collection";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        collectionHandler.clearCollection();

        System.out.println("Cleared!");
    }
}
