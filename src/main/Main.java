package main;

import commandManager.CommandExecutor;
import commandManager.CommandMode;
import fileLogic.Loader;
import models.Route;
import models.handlers.CollectionHandler;
import models.handlers.RoutesHandler;

import java.util.HashSet;

/**
 * Program entry point class. Contains main() method.
 * This program is managing collection of type <code>HashSet&#8249;Route></code>
 *
 * @since 1.0
 * @author zerumi
 */
public class Main {

    /**
     * Environment key to XML file for store collection.
     */
    public static final String ENV_KEY = "lab5";

    /**
     * Program entry point.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();

        // load collection
        Loader<HashSet<Route>, Route> loader = new Loader<>(handler.getCollection().getClass(), Route.class);
        handler.setCollection(loader.loadFromXMLbyEnvKey(ENV_KEY));
        System.out.println("Loaded " + handler.getCollection().size() + " elements total.");
        System.out.println();

        // commands
        System.out.println("Welcome to CLI! Now you are operating with collection of type " + handler.getCollection().getClass().getName() + ", filled with elements of type " + handler.getFirstOrNew().getClass().getName());
        System.out.println("Now you can enter the commands. Use help for reference.");
        CommandExecutor executor = new CommandExecutor();
        executor.startExecuting(System.in, CommandMode.CLI_UserMode);
    }
}