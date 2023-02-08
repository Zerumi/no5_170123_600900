package commandManager.commands;

import models.Route;
import models.comparators.RouteHashComparator;
import models.handlers.CollectionHandler;
import models.handlers.ModuleHandler;
import models.handlers.RouteHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;

/**
 * Removes elements from collection greater than given in argument.
 *
 * @since 1.0
 * @author Zerumi
 */
public class RemoveGreaterCommand implements BaseCommand {
    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescr() {
        return "Removes elements from collection greater than given in argument.";
    }

    @Override
    public String getArgs() {
        return "{element}";
    }
    @Override
    public void execute(String[] args) {
        RouteHashComparator comparator = new RouteHashComparator();

        ModuleHandler<Route> handler = new RouteHandler();
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        Route greaterThan = handler.buildObjectByCLI();
        System.out.println("Hashcode: " + greaterThan.hashCode());
        var iterator = collectionHandler.getCollection().iterator();

        int count = 0;

        while (iterator.hasNext())
        {
            var current = iterator.next();
            System.out.print("Comparing: current -- " + current.hashCode() + " vs " + greaterThan.hashCode());
            if (comparator.compare(current, greaterThan) > 0)
            {
                System.out.println();
                System.out.println("Removing element: " + current);
                iterator.remove();
                count++;
            }
            else
            {
                System.out.println(" -- Lower.");
            }
        }

        System.out.println("Removed " + count + " elements");
    }
}
