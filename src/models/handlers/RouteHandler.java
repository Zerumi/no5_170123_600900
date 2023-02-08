package models.handlers;

import models.Route;
import models.validators.DistanceValidator;
import models.validators.IdValidator;
import models.validators.NameValidator;
import models.validators.Validator;

import java.util.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.InputMismatchException;
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
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        // id
        Validator<Long> idValidator = new IdValidator();
        var lastObj = handler.getLastElement();
        long lastId = 1L;
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
            System.out.println("Enter the value of name (Type: String)");
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

        // coords
        System.out.println("Starting coordinates field setup... (Type: Coordinates)");
        CoordinatesHandler coordinatesHandler = new CoordinatesHandler();
        result.setCoordinates(coordinatesHandler.buildObjectByCLI());

        // from (may null)
        System.out.println("Starting \"from\" field setup... (Type: Location)");
        System.out.print("This field may be skipped to fill. Skip? [y/n] ");
        String answer = scanner.next();
        if (!answer.equalsIgnoreCase("y"))
        {
            LocationHandler locationHandler = new LocationHandler();
            result.setFrom(locationHandler.buildObjectByCLI());
        }

        // to (may null)
        System.out.println("Starting \"to\" field setup... (Type: Location)");
        System.out.print("This field may be skipped to fill. Skip? [y/n] ");
        answer = scanner.next();
        if (!answer.equalsIgnoreCase("y"))
        {
            LocationHandler locationHandler = new LocationHandler();
            result.setTo(locationHandler.buildObjectByCLI());
        }

        // distance
        while (true)
        {
            try {
                DistanceValidator validator = new DistanceValidator();
                System.out.println("Enter the value of distance (Type: int)");
                int value = scanner.nextInt();
                if (!validator.validate(value))
                {
                    System.out.println("Value violates restrictions for field! Try again.");
                    continue;
                }
                result.setDistance(value);
            } catch (InputMismatchException e) {
                System.out.println("Wrong input! Try again.");
                continue;
            }
            break;
        }

        // creationDate
        Date creationDate = Date.from(Instant.now());
        System.out.println("Object created at " + creationDate);
        result.setCreationDate(creationDate);

        System.out.println("Object setup completed! Sending result...");

        return result;
    }
}
