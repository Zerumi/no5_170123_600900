package commandManager;

import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandExecutor {
    private static final Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");

    public void startExecuting(InputStream input)
    {
        CommandManager commandManager = new CommandManager();

        Scanner cmdScanner = new Scanner(input);
        while (cmdScanner.hasNext())
        {
            String line = cmdScanner.nextLine();
            if (line.isEmpty()) continue;
            try {
                commandManager.executeCommand(line.split(" "));
            } catch (Exception e) {
                myLogger.log(Level.SEVERE, "В командном менеджере произошла ошибка! " + e);
                if (input.equals(System.in))
                    myLogger.log(Level.INFO, "Выполнение команды было прервано. Вы можете продолжать работу.");
                else
                    myLogger.log(Level.INFO, "Команда была пропущена... Обработчик продолжает работу");
            }
        }
    }
}
