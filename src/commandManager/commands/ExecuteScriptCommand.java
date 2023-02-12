package commandManager.commands;

import commandManager.CommandExecutor;
import commandManager.CommandMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
    public void execute(String[] args) throws IllegalArgumentException {
        try {
            CommandExecutor executor = new CommandExecutor();
            if (checkRecursion(args[1], new ArrayList<>()))
            {
                myLogger.log(Level.WARNING, "При анализе скрипта обнаружена рекурсия. Устраните ее перед исполнением.");
                return;
            }
            myLogger.log(Level.INFO, "Выполняем скрипт " + args[1]);
            executor.startExecuting(new FileInputStream(Path.of(args[1]).toFile()), CommandMode.NonUserMode);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private boolean checkRecursion(String file, ArrayList<Path> stack) throws IOException {
        Path path = Path.of(file);
        if (stack.contains(path)) return true;
        stack.add(path);
        String str = Files.readString(path);
        Pattern pattern = Pattern.compile("execute_script .*");
        var patternMatcher = pattern.matcher(str);
        while (patternMatcher.find())
        {
            String line = patternMatcher.group().split(" ")[1];
            if(checkRecursion(line, stack)) return true;
        }
        return false;
    }
}
