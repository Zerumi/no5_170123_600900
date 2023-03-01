package commandManager.commands;

import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;
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
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 1);

        if (Utilities.isNotNumeric(args[1])) {
            System.out.println("Provided argument \"" + args[1] + "\" is not a number! Try again.");
            return;
        } else if (args[1].contains(",")) {
            System.out.println("Distance field cannot accept decimal values. Try again");
            return;
        }

        int greaterThan;

        try {
            greaterThan = Integer.parseInt(args[1]);
        } catch (NumberFormatException e)
        {
            System.out.println("Provided argument: \"" + args[1] + "\" is too large for distance field. Try again");
            return;
        }


        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        List<Integer> distances = collectionHandler.getCollection().stream().map(Route::getDistance).toList();

        int finalGreaterThan = greaterThan;
        System.out.println("Total count: " + distances.stream().map(x -> x.compareTo(finalGreaterThan)).filter(x -> x > 0).count());
    }
}
