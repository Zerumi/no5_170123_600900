package commandManager.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExitCommand implements BaseCommand {

    private static Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescr() {
        return "Terminates the application (without saving collection).";
    }

    @Override
    public void execute(String[] args) {
        myLogger.log(Level.FINE, "Выходим из программы...");
        System.exit(0);
    }
}
