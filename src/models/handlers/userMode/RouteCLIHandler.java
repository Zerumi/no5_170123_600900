package models.handlers.userMode;

import exceptions.BuildObjectException;
import exceptions.StreamInterruptedException;
import main.Utilities;
import models.Route;
import models.handlers.*;
import models.validators.DistanceValidator;
import models.validators.NameValidator;
import models.validators.Validator;

import java.time.Instant;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Implementation of ModuleHandler for Route Model.
 *
 * @since 1.0
 * @author Zerumi
 */
public class RouteCLIHandler implements ModuleHandler<Route> {
    /**
     * Method for create fully validated objects by CLI (userMode).
     *
     * @return Built object
     */
    @Override
    public Route buildObject() throws BuildObjectException {
        try {
            System.out.println("Generating object...");
            Route result = new Route();
            System.out.println("Welcome to master of Route object creation!");
            System.out.println("Follow the instructions to setup your object.");
            System.out.println();

            Scanner scanner = new Scanner(System.in);

            // id
            result.setId(RouteHandlers.generateID());

            // name
            Validator<String> nameValidator = new NameValidator();
            String name = null;
            do {
                System.out.println("Enter the value of name (Type: String)");
                if (Utilities.hasNextLineOrThrow(scanner)) {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        name = line;
                }
                if (!nameValidator.validate(name)) {
                    System.out.println("Value violates restrictions for field! Try again.");
                    System.out.println("Restrictions: Should be not null and not empty.");
                }
            } while (!nameValidator.validate(name));
            result.setName(name);

            // coords
            System.out.println("Starting coordinates field setup... (Type: Coordinates)");
            CoordinatesCLIHandler coordinatesCLIHandler = new CoordinatesCLIHandler();
            result.setCoordinates(coordinatesCLIHandler.buildObject());

            // from (may null)
            System.out.println("Starting \"from\" field setup... (Type: Location)");
            System.out.print("This field may be skipped to fill. Skip? [y/n] ");
            String answer = scanner.next();
            if (!answer.equalsIgnoreCase("y")) {
                LocationCLIHandler locationCLIHandler = new LocationCLIHandler();
                result.setFrom(locationCLIHandler.buildObject());
            }
            if (Utilities.hasNextLineOrThrow(scanner)) {
                scanner.nextLine();
            }

            // to (may null)
            System.out.println("Starting \"to\" field setup... (Type: Location)");
            System.out.print("This field may be skipped to fill. Skip? [y/n] ");
            answer = scanner.next();
            if (!answer.equalsIgnoreCase("y")) {
                LocationCLIHandler locationCLIHandler = new LocationCLIHandler();
                result.setTo(locationCLIHandler.buildObject());
            }
            if (Utilities.hasNextLineOrThrow(scanner)) {
                scanner.nextLine();
            }

            // distance
            while (true) {
                try {
                    DistanceValidator validator = new DistanceValidator();
                    System.out.println("Enter the value of distance (Type: int (default value: 0))");
                    int value = 0;
                    if (Utilities.hasNextLineOrThrow(scanner)) {
                        String line = scanner.nextLine();
                        if (!line.isEmpty())
                            value = Integer.parseInt(line);
                    }
                    if (!validator.validate(value)) {
                        System.out.println("Value violates restrictions for field! Try again.");
                        System.out.println("Restrictions: Number should be grater than 1.");
                        continue;
                    }
                    result.setDistance(value);
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Wrong input! Try again.");
                    System.out.println("You should enter a number in range [-2147483648; 2147483647], it shouldn't be decimal.");
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

        } catch (StreamInterruptedException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }
}
