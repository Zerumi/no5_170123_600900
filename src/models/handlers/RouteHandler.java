package models.handlers;

import models.Route;
import models.validators.IdValidator;
import models.validators.NameValidator;
import models.validators.Validator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RouteHandler implements ModuleHandler<Route>{
    @Override
    public Route buildObjectByCLI() {
        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();
        System.out.println("Generating object...");
        Route result = new Route();
        System.out.println("Welcome to master of Route object creation!");
        System.out.println("Follow the instructions to setup your object.");

        Scanner scanner = new Scanner(System.in);

        // id
        Validator<Long> idValidator = new IdValidator();
        var lastObj = handler.getLastElement();
        Long lastId = 1L;
        if (lastObj != null)
        {
            lastId = lastObj.getId() + 1;
        }
        while (!idValidator.validate(lastId))
        {
            lastId = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        }
        result.setId(lastId);
        System.out.println("ID Field (auto-generated): " + lastId);

        // name
        Validator<String> nameValidator = new NameValidator();
        String name = null;
        do {
            System.out.println("Enter Name of Route (Type: String)");
            if (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                if (!line.isEmpty())
                    name = line;
            }
            if (!nameValidator.validate(name))
                System.out.println("Value violates restrictions for field! Try again.");
        } while (!nameValidator.validate(name));
        result.setName(name);



        // from (may null)

        // to (may null)

        // distance

        return result;
    }
}
