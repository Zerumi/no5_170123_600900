package commandManager.commands;

import commandManager.CommandManager;

/**
 * Shows reference about available commands.
 *
 * @since 1.0
 * @author Zerumi
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
                // TODO: Handle wrong arguments!
                var command = manager.getCommands().get(args[i]);
                System.out.println(args[i] + " -- " + command.getDescr());
                System.out.println();
            }
        }
    }
}
