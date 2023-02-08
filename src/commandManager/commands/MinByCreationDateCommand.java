package commandManager.commands;

import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.Date;
import java.util.HashSet;

public class MinByCreationDateCommand implements BaseCommand{
    @Override
    public String getName() {
        return "min_by_creation_date";
    }

    @Override
    public String getDescr() {
        return "Returns element from collection with min creation date.";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        Date min = collectionHandler.getCollection().stream().map(Route::getCreationDate).min(Date::compareTo).orElse(null);

        if (min == null)
        {
            System.out.println("There's nothing to show...");
            return;
        }
        else
        {
            var iterator = collectionHandler.getCollection().iterator();

            while (iterator.hasNext())
            {
                var obj = iterator.next();

                if (obj.getCreationDate().equals(min))
                    System.out.println(obj);
            }
        }
    }
}
