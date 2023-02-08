package commandManager.commands;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.*;
import java.util.HashSet;
import java.util.stream.Collectors;

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
        List<Integer> distances = collectionHandler.getCollection().stream().map(Route::getDistance).collect(Collectors.toList());

        distances.sort(Comparator.comparingInt(o -> o));
        distances.forEach(System.out::println);
    }
}
