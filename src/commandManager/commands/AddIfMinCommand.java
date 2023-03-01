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
 * Adds element if it's value lower than min value.
 *
 * @since 1.0
 * @author Zerumi
 */
public class AddIfMinCommand implements BaseCommand {

    /**
     * Default constructor with handler from 1.0
     */
    public AddIfMinCommand()
    {
        handler = new RouteCLIHandler();
    }
    ModuleHandler<Route> handler;
    /**
     * Provides choosing handler
     *
     * @since 1.1
     * @param handler ModuleHandler for operating
     */
    public AddIfMinCommand(ModuleHandler<Route> handler)
    {
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "add_if_min";
    }

    @Override
    public String getDescr() {
        return "Adds element if it's value lower than min value.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }
    @Override
    public void execute(String[] args) throws BuildObjectException {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        Route obj = handler.buildObject();

        if (obj.compareTo(collectionHandler.getMin(new RouteDistanceComparator())) < 0)
        {
            collectionHandler.addElementToCollection(obj);
            System.out.println("Element added!");
        }
        else
        {
            System.out.println("Element not added: it's not lower than min value.");
        }

    }
}
