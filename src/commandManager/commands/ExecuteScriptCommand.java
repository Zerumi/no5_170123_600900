package commandManager.commands;

import commandManager.CommandExecutor;
import commandManager.CommandMode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void execute(String[] args) throws IllegalArgumentException {
        try {
            CommandExecutor executor = new CommandExecutor();
            myLogger.log(Level.INFO, "Выполняем скрипт " + args[1]);
            executor.startExecuting(new FileInputStream(Path.of(args[1]).toFile()), CommandMode.NonUserMode);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
