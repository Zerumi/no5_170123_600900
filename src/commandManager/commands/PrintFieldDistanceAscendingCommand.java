package commandManager.commands;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Prints all distance fields in ascending sorting.
 *
 * @since 1.0
 * @author Zerumi
 */
public class PrintFieldDistanceAscendingCommand implements BaseCommand {

    @Override
    public String getName() {
        return "print_field_ascending_distance";
    }

    @Override
    public String getDescr() {
        return "Prints all distance fields in ascending sorting.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        List<Integer> distances = collectionHandler.getCollection().stream().map(Route::getDistance).sorted(Comparator.comparingInt(o -> o)).toList();

        distances.forEach(System.out::println);
    }
}
