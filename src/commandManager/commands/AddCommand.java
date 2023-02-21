package commandManager.commands;

import exceptions.BuildObjectException;
import models.Route;
import models.handlers.*;
import models.handlers.userMode.RouteCLIHandler;

import java.util.HashSet;

/**
 * Adds new element to collection.
 *
 * @since 1.0
 * @author Zerumi
 */
public class AddCommand implements BaseCommand {
    ModuleHandler<Route> handler;

    /**
     * Default constructor with handler from 1.0
     */
    public AddCommand()
    {
        handler = new RouteCLIHandler();
    }
    /**
     * Provides choosing handler
     *
     * @param handler ModuleHandler for operating
     */
    public AddCommand(ModuleHandler<Route> handler)
    {
        this.handler = handler;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescr() {
        return "Adds new element to collection.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }

    @Override
    public void execute(String[] args) throws BuildObjectException {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        collectionHandler.addElementToCollection(handler.buildObject());

        System.out.println("Element added!");
    }
}
