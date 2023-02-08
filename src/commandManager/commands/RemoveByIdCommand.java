package commandManager.commands;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;

public class RemoveByIdCommand implements BaseCommand {

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescr() {
        return "Removes element from collection by id";
    }
    @Override
    public String getArgs() {
        return "id";
    }
    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();

        var iterator = collectionHandler.getCollection().iterator();

        while (iterator.hasNext())
        {
            if(iterator.next().getId() == Long.parseLong(args[1]))
                iterator.remove();
        }

        System.out.println("Executed.");
    }
}
