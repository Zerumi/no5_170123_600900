package commandManager.commands;

import exceptions.WrongAmountOfArgumentsException;
import fileLogic.Saver;
import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;

/**
 * Saves collection to file.
 *
 * @since 1.0
 * @author Zerumi
 */
public class SaveCommand implements BaseCommand {
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescr() {
        return "Saves collection to file.";
    }

    @Override
    public void execute(String[] args) throws WrongAmountOfArgumentsException {
        System.out.println("Saving...");
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        Saver<HashSet<Route>, Route> saver = new Saver<>(Route.class);

        saver.saveCollection(collectionHandler.getCollection(), "lab5");

        System.out.println("Executed.");
    }
}
