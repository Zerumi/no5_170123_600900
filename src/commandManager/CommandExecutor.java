package commandManager;

import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.UnknownCommandException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for executing commands. Provides different inputs for command executing.
 */
public class CommandExecutor {
    private static final Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");

    /**
     * Start executing commands from InputStream.
     *
     * @param input commands stream (File, System.in, e.t.c.)
     * @param mode  variant of command behavior (see CommandMode enum)
     */
    public void startExecuting(InputStream input, CommandMode mode) {
        Scanner cmdScanner = new Scanner(input);
        CommandManager commandManager = new CommandManager(mode, cmdScanner);
        while (cmdScanner.hasNext()) {
            String line = cmdScanner.nextLine();
            if (line.isEmpty()) continue;
            try {
                commandManager.executeCommand(line.split(" "));
            } catch (CommandInterruptedException ex) {
                if (mode.equals(CommandMode.CLI_UserMode))
                    myLogger.log(Level.INFO, "Выполнение команды было прервано. Вы можете продолжать работу.");
                else
                    myLogger.log(Level.INFO, "Команда была пропущена... Обработчик продолжает работу");
            }
        }
    }
}