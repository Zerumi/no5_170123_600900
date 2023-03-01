package commandManager.commands;

import exceptions.BuildObjectException;
import models.Route;
import models.comparators.RouteDistanceComparator;
import models.handlers.CollectionHandler;
import models.handlers.ModuleHandler;
import models.handlers.RoutesHandler;
import models.handlers.userMode.RouteCLIHandler;

import java.util.HashSet;

/**
 * Add element if it's value greater than max value.
 *
 * @since 1.0
 * @author Zerumi
 */
public class AddIfMaxCommand implements BaseCommand {
    ModuleHandler<Route> handler;

    /**
     * Default constructor with handler from 1.0
     */
    public AddIfMaxCommand()
    {
        handler = new RouteCLIHandler();
    }
    /**
     * Provides choosing handler
     *
     * @since 1.1
     * @param handler ModuleHandler for operating
     */
    public AddIfMaxCommand(ModuleHandler<Route> handler)
    {
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescr() {
        return "Add element if it's value greater than max value.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }
    @Override
    public void execute(String[] args) throws BuildObjectException {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        Route obj = handler.buildObject();

        if (obj.compareTo(collectionHandler.getMax(new RouteDistanceComparator())) > 0)
        {
            collectionHandler.addElementToCollection(obj);
            System.out.println("Element added!");
        }
        else
        {
            System.out.println("Element not added: it's not greater than max value.");
        }
    }
}
