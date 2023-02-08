package commandManager;

import commandManager.commands.*;
import exceptions.UnknownCommandException;

import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command Manager for interactive collection manage. For execute commands, use CommandExecutor class
 *
 * @see CommandExecutor
 * @since 1.0
 * @author Zerumi
 */
public class CommandManager {

    private static final Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");
    LinkedHashMap<String, BaseCommand> commands;

    /**
     * Setup command manager and all of its commands.
     */
    public CommandManager()
    {
        commands = new LinkedHashMap<>();

        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("add", new AddCommand());
        commands.put("update", new UpdateIdCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand());
        commands.put("add_if_max", new AddIfMaxCommand());
        commands.put("add_if_min", new AddIfMinCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("min_by_creation_date", new MinByCreationDateCommand());
        commands.put("count_greater_than_distance", new CountGreaterThanDistanceCommand());
        commands.put("print_field_ascending_distance", new PrintFieldDistanceAscendingCommand());
    }

    /**
     * Get all commands from manager.
     *
     * @return Map of loaded commands
     */
    public LinkedHashMap<String, BaseCommand> getCommands() {
        return commands;
    }

    /**
     * Universe method for command executing.
     *
     * @param args full separated line from stream
     * @throws UnknownCommandException If command isn't found.
     */
    public void executeCommand(String[] args) throws UnknownCommandException {
        try {
            commands.get(args[0]).execute(args);
        }
        catch (NullPointerException e)
        {
            UnknownCommandException transfer = new UnknownCommandException("Указанная команда не была обнаружена и/или был предоставлен некорректный аргумент.");
            transfer.initCause(e);
            throw transfer;
        }
        catch (IllegalArgumentException e)
        {
            myLogger.log(Level.SEVERE, "Выполнение команды пропущено из-за возникшей проблемы: " + e);
        }
    }
}
