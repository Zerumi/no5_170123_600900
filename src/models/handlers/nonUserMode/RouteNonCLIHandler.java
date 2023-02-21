package models.handlers.nonUserMode;

import exceptions.BuildObjectException;
import models.Coordinates;
import models.Location;
import models.Route;
import models.handlers.ModuleHandler;
import models.handlers.RouteHandlers;
import models.validators.RouteValidator;
import models.validators.Validator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of Route ModelHandler for non-User Mode.
 *
 * @since 1.1
 * @author zerumi
 */
public class RouteNonCLIHandler implements ModuleHandler<Route> {

    private static final Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");

    Scanner scanner;

    /**
     * Constructor for setup handler's scanner.
     *
     * @param scanner Command scanner for reading argument
     */
    public RouteNonCLIHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Route buildObject() throws BuildObjectException {
        System.out.println("Generating object...");
        Route result = new Route();
        int valuesToRead = 12;
        int coordsIndex = 1;
        int fromIndex = 3;
        int toIndex = 7;
        ArrayList<String> values = new ArrayList<>(valuesToRead);

        for (int i = 0; i < valuesToRead && scanner.hasNextLine(); i++)
        {
            String line = scanner.nextLine();
            if (!line.isEmpty())
                values.add(line);
            else
            {
                values.add(null);

                if (i == coordsIndex)
                {
                    valuesToRead -= 1;
                    fromIndex -= 1;
                    toIndex -= 1;
                }

                if (i == fromIndex)
                {
                    valuesToRead -= 3;
                    toIndex -= 3;
                }

                if (i == toIndex)
                {
                    valuesToRead -= 3;
                }
            }
        }

        try {
            result.setId(RouteHandlers.generateID());
            result.setName(values.get(0));
            System.out.println("Name: " + result.getName());
            if (values.get(coordsIndex) != null) {
                System.out.println("Generating coordinates...");
                Coordinates coordinates = new Coordinates();
                coordinates.setX(Double.parseDouble(values.get(coordsIndex)));
                System.out.println("Coords X: " + coordinates.getX());
                coordinates.setY(Float.valueOf(values.get(coordsIndex + 1)));
                System.out.println("Coords Y: " + coordinates.getY());
                result.setCoordinates(coordinates);
            }
            System.out.println("Coords: " + result.getCoordinates());
            result.setFrom(generateLocation(fromIndex, values));
            System.out.println("From: " + result.getFrom());
            result.setTo(generateLocation(toIndex, values));
            System.out.println("To: " + result.getTo());
            result.setDistance(Integer.parseInt(values.get(values.size() - 1)));
            System.out.println("Distance: " + result.getDistance());
            result.setCreationDate(Date.from(Instant.now()));
            System.out.println("Generated at: " + result.getCreationDate());
            System.out.println("Object generated! Validating result...");

            Validator<Route> validator = new RouteValidator();
            if (!validator.validate(result))
            {
                System.out.println("Object's invalid, skipping...");
                throw new BuildObjectException("Созданный элемент нарушает ограничения и не может быть добавлен в коллекцию!");
            }
            System.out.println("Validate successful! Sending result...");

            return result;

        } catch (NumberFormatException | NullPointerException e)
        {
            myLogger.log(Level.WARNING, "Объект будет пропущен. Устраните ошибку в скрипте и повторите попытку.");
            throw new BuildObjectException("Предоставленные данные для построения объекта неверны. Воспользуйтесь ручным вводом или исправьте команду в скрипте.");
        }
    }

    private Location generateLocation(int toIndex, ArrayList<String> values) {
        Location obj = null;
        if (values.get(toIndex) != null)
        {
            System.out.println("Generating location...");
            obj = new Location();
            obj.setX(Float.parseFloat(values.get(toIndex)));
            System.out.println("X: " + obj.getX());
            obj.setY(Long.valueOf(values.get(toIndex + 1)));
            System.out.println("Y: " + obj.getX());
            obj.setZ(Long.valueOf(values.get(toIndex + 2)));
            System.out.println("Z: " + obj.getX());
            obj.setName(values.get(toIndex + 3));
            System.out.println("Name:" + obj.getName());
        }
        return obj;
    }
}
