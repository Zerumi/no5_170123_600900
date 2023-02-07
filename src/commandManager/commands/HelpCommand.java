package commandManager.commands;

import commandManager.CommandManager;

import java.util.Arrays;

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
            manager.getCommands().forEach((name,command) -> {
                System.out.println(name + " -- " + command.getDescr());
            });
        }
        else
        {
            for (int i = 1; i < args.length; i++)
            {
                var command = manager.getCommands().get(args[i]);
                System.out.println(args[i] + " -- " + command.getDescr());
                System.out.println();
            }
        }
    }
}
