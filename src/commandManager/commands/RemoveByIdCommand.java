package commandManager.commands;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;

/**
 * Removes element from collection by id.
 *
 * @since 1.0
 * @author Zerumi
 */
public class RemoveByIdCommand implements BaseCommand {

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescr() {
        return "Removes element from collection by id.";
    }
    @Override
    public String getArgs() {
        return "id";
    }
    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        collectionHandler.getCollection().removeIf(route -> route.getId() == Long.parseLong(args[1]));

        System.out.println("Executed.");
    }
}
