package commandManager.commands;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.ModuleHandler;
import models.handlers.RouteHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;

public class AddCommand implements BaseCommand {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescr() {
        return "Adds new element to collection.";
    }

    @Override
    public void execute(String[] args) {
        ModuleHandler<Route> handler = new RouteHandler();
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        collectionHandler.addElementToCollection(handler.buildObjectByCLI());
    }
}
