package commandManager.commands;

public interface BaseCommand {
    String getName();

    String getDescr();
    void execute(String[] args) throws IllegalArgumentException;
}
