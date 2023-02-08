package commandManager.commands;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;
import java.util.List;

/**
 * Shows count of the elements greater than distance value.
 *
 * @since 1.0
 * @author Zerumi
 */
public class CountGreaterThanDistanceCommand implements BaseCommand{
    @Override
    public String getName() {
        return "count_greater_than_distance";
    }

    @Override
    public String getDescr() {
        return "Shows count of the elements greater than distance value.";
    }

    @Override
    public String getArgs() {
        return "distance";
    }
    @Override
    public void execute(String[] args) {
        int greaterThan = Integer.parseInt(args[1]);
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        List<Integer> distances = collectionHandler.getCollection().stream().map(Route::getDistance).toList();

        System.out.println("Total count: " + distances.stream().map(x -> x > greaterThan).count());
    }
}
