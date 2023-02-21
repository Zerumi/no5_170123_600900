package commandManager.commands;

import commandManager.CommandManager;

import java.util.Optional;

/**
 * Shows reference about available commands.
 *
 * @author Zerumi
 * @since 1.0
 */
public class HelpCommand implements BaseCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescr() {
        return "Shows reference about available commands.";
    }

    @Override
    public void execute(String[] args) {
        CommandManager manager = new CommandManager();

        if (args.length == 1)
        {
            manager.getCommands().forEach((name,command) -> System.out.println(name + " " + command.getArgs() + " --  " + command.getDescr()));
        }
        else
        {
            for (int i = 1; i < args.length; i++)
            {
                var command = manager.getCommands().get(args[i]);
                System.out.println(args[i] + " -- " + Optional.ofNullable(command).map(BaseCommand::getDescr).orElse("This command is not found in manager"));
            }
        }
    }
}
