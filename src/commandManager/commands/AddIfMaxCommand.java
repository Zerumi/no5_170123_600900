package commandManager.commands;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.ModuleHandler;
import models.handlers.RouteHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;

public class AddIfMaxCommand implements BaseCommand{
    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescr() {
        return "Add element if it's value greater than max value.";
    }

    @Override
    public void execute(String[] args) {
        ModuleHandler<Route> handler = new RouteHandler();
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        Route obj = handler.buildObjectByCLI();

        if (obj.compareTo(collectionHandler.getLastElement()) > 0)
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
