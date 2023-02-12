package models.handlers.userMode;

import models.Location;
import models.handlers.ModuleHandler;
import models.validators.LocationNameValidator;
import models.validators.LocationYZValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Implementation of ModuleHandler for Location Model.
 *
 * @since 1.0
 * @author Zerumi
 */
public class LocationCLIHandler implements ModuleHandler<Location> {
    /**
     * Method for create fully validated objects by CLI.
     *
     * @return Built object
     */
    @Override
    public Location buildObject() {
        System.out.println("Generating object...");
        Location result = new Location();
        System.out.println("Welcome to master of Location object creation!");
        System.out.println("Follow the instructions to setup your object.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            try {
                System.out.println("Enter the value of x (Type: float)");
                float value = 0;
                if (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        value = Float.parseFloat(line);
                }
                result.setX(value);
            } catch (InputMismatchException | NumberFormatException e)
            {
                System.out.println("Wrong input! Try again.");
                continue;
            }
            break;
        }

        LocationYZValidator yzValidator = new LocationYZValidator();

        while (true)
        {
            try {
                System.out.println("Enter the value of y (Type: Long)");
                Long value = null;
                if (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        value = Long.valueOf(line);
                }
                if (!yzValidator.validate(value))
                {
                    System.out.println("Value violates restrictions for field! Try again.");
                    continue;
                }
                result.setY(value);
            } catch (InputMismatchException | NumberFormatException e)
            {
                System.out.println("Wrong input! Try again.");
                continue;
            }
            break;
        }

        while (true)
        {
            try {
                System.out.println("Enter the value of z (Type: Long)");
                Long value = null;
                if (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        value = Long.valueOf(line);
                }
                if (!yzValidator.validate(value))
                {
                    System.out.println("Value violates restrictions for field! Try again.");
                    continue;
                }
                result.setZ(value);
            } catch (InputMismatchException | NumberFormatException e)
            {
                System.out.println("Wrong input! Try again.");
                continue;
            }
            break;
        }

        while (true)
        {
            LocationNameValidator nameValidator = new LocationNameValidator();
            try {
                System.out.println("Enter the value of name (Type: String)");
                System.out.println("This field may be skipped to fill");
                String value = null;
                if (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        value = line;
                }
                if (!nameValidator.validate(value)) {
                    System.out.println("Value violates restrictions for field! Try again.");
                    continue;
                }
                result.setName(value);
            } catch (InputMismatchException e)
            {
                System.out.println("Wrong input! Try again.");
            }
            break;
        }

        System.out.println("Object setup completed! Sending result...");

        return result;
    }
}
