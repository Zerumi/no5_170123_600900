package models.handlers.userMode;

import exceptions.BuildObjectException;
import exceptions.StreamInterruptedException;
import main.Utilities;
import models.Location;
import models.handlers.ModuleHandler;
import models.validators.LocationNameValidator;
import models.validators.LocationXValidator;
import models.validators.LocationYZValidator;
import models.validators.Validator;

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
    public Location buildObject() throws BuildObjectException {
        try {
            System.out.println("Generating object...");
            Location result = new Location();
            System.out.println("Welcome to master of Location object creation!");
            System.out.println("Follow the instructions to setup your object.");
            System.out.println();

            Scanner scanner = new Scanner(System.in);

            Validator<Float> locationXValidator = new LocationXValidator();

            while (true) {
                try {
                    System.out.println("Enter the value of x (Type: float (default value: 0.0f))");
                    float value = 0;
                    if (Utilities.hasNextLineOrThrow(scanner)) {
                        String line = scanner.nextLine();
                        if (!line.isEmpty())
                            value = Float.parseFloat(line);
                    }
                    if (!locationXValidator.validate(value)) {
                        System.out.println("Value violates restrictions for field! Try again.");
                        System.out.println("Restrictions: IEEE 754 Float value.");
                        continue;
                    }
                    result.setX(value);
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Wrong input! Try again.");
                    System.out.println("You should enter a real number, matches with IEEE 754 Float value standard (not so big/small).");
                    continue;
                }
                break;
            }

            LocationYZValidator yzValidator = new LocationYZValidator();


            String yInvitation = "Enter the value of y (Type: Long)";
            result.setY(handleYZInput(scanner, yzValidator, yInvitation));

            String zInvitation = "Enter the value of z (Type: Long)";
            result.setZ(handleYZInput(scanner, yzValidator, zInvitation));

            while (true) {
                LocationNameValidator nameValidator = new LocationNameValidator();
                System.out.println("Enter the value of name (Type: String)");
                System.out.println("This field may be skipped to fill");
                String value = null;
                if (Utilities.hasNextLineOrThrow(scanner)) {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        value = line;
                }
                if (!nameValidator.validate(value)) {
                    System.out.println("Value violates restrictions for field! Try again.");
                    System.out.println("Restrictions: Should be not empty.");
                    continue;
                }
                result.setName(value);
                break;
            }

            System.out.println("Object setup completed! Sending result...");

            return result;
        } catch (StreamInterruptedException e) {
            throw new BuildObjectException("Во время конструирования объекта произошла ошибка: " + e.getMessage());
        }
    }

    private Long handleYZInput(Scanner scanner, Validator<Long> yzValidator, String invitation) throws StreamInterruptedException {
        Long value = null;
        while (true) {
            try {
                System.out.println(invitation);
                if (Utilities.hasNextLineOrThrow(scanner)) {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        value = Long.valueOf(line);
                }
                if (!yzValidator.validate(value)) {
                    System.out.println("Value violates restrictions for field! Try again.");
                    System.out.println("Restrictions: Number should be in range [-9223372036854775808; 9223372036854775807] and not null.");
                    continue;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Wrong input! Try again.");
                System.out.println("You should enter a number in range [-9223372036854775808; 9223372036854775807], it shouldn't be decimal.");
                continue;
            }
            break;
        }
        return value;
    }
}
