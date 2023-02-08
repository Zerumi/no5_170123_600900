package models.handlers;

import models.Coordinates;
import models.validators.CoordXValidator;
import models.validators.CoordYValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoordinatesHandler implements ModuleHandler<Coordinates> {

    @Override
    public Coordinates buildObjectByCLI() {
        System.out.println("Generating object...");
        Coordinates result = new Coordinates();
        System.out.println("Welcome to master of Coordinates object creation!");
        System.out.println("Follow the instructions to setup your object.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            try {
                CoordXValidator xValidator = new CoordXValidator();
                System.out.println("Enter the value of x (Type: double)");
                double value = 0;
                if (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        value = Double.parseDouble(line);
                }
                if (xValidator.validate(value))
                    result.setX(value);
                else
                {
                    System.out.println("Value violates restrictions for field! Try again.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input! Try again.");
                continue;
            }
            break;
        }

        while (true)
        {
            try {
                CoordYValidator yValidator = new CoordYValidator();
                System.out.println("Enter the value of y (Type: Float)");
                Float value = null;
                if (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    if (!line.isEmpty())
                        value = Float.valueOf(line);
                }
                if (yValidator.validate(value))
                    result.setY(value);
                else
                {
                    System.out.println("Value violates restrictions for field! Try again.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input! Try again.");
                continue;
            }
            break;
        }

        System.out.println("Object setup completed! Sending result...");

        return result;
    }
}
