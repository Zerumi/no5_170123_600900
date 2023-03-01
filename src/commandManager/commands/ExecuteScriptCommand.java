package commandManager.commands;

import commandManager.CommandExecutor;
import commandManager.CommandMode;
import exceptions.WrongAmountOfArgumentsException;
import main.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Reads and executes script from file.
 *
 * @since 1.0
 * @author Zerumi
 */
public class ExecuteScriptCommand implements BaseCommand {

    private static final Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescr() {
        return "Reads and executes script from file.";
    }

    @Override
    public String getArgs() {
        return "file_path";
    }
    @Override
    public void execute(String[] args) throws IllegalArgumentException, WrongAmountOfArgumentsException {
        Utilities.checkArgumentsOrThrow(args.length, 1);

        try {
            CommandExecutor executor = new CommandExecutor();
            if (checkRecursion(Path.of(args[1]), new ArrayDeque<>())) {
                myLogger.log(Level.WARNING, "При анализе скрипта обнаружена рекурсия. Устраните ее перед исполнением.");
                return;
            }
            myLogger.log(Level.INFO, "Executing script " + args[1]);
            executor.startExecuting(new FileInputStream(Path.of(args[1]).toFile()), CommandMode.NonUserMode);
        } catch (InvalidPathException e) {
            System.out.println("Provided argument path isn't legal. Try again.");
            throw new IllegalArgumentException(e);
        } catch (FileNotFoundException | NoSuchFileException e) {
            System.out.println("File not found! Try again.");
        } catch (SecurityException e) {
            System.out.println("Access denied. Try again with another file.");
        } catch (IOException e) {
            System.out.println("Something went wrong during file handling. Try again. (" + e.getMessage() + ")");
        }
    }

    private boolean checkRecursion(Path path, ArrayDeque<Path> stack) throws IOException {
        if (stack.contains(path)) return true;
        stack.addLast(path);
        String str = Files.readString(path);
        Pattern pattern = Pattern.compile("execute_script .*");
        var patternMatcher = pattern.matcher(str);
        while (patternMatcher.find())
        {
            Path newPath = Path.of(patternMatcher.group().split(" ")[1]);
            if(checkRecursion(newPath, stack)) return true;
        }
        stack.removeLast();
        return false;
    }
}
