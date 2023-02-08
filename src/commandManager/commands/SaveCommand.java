package commandManager.commands;

import fileLogic.Saver;
import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;

public class SaveCommand implements BaseCommand {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescr() {
        return null;
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        Saver<HashSet<Route>, Route> saver = new Saver<>(Route.class);

        saver.saveCollection(collectionHandler.getCollection(), "lab5");

        System.out.println("Saved!");
    }
}
