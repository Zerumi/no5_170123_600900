import fileLogic.Loader;
import models.Route;

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
    private static HashSet<Route> routes = new HashSet<>();

    /**
     * Program entry point.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // load collection
        Loader<HashSet<Route>, Route> loader = new Loader<>(routes.getClass(), Route.class);
        routes = loader.loadFromXMLbyEnvKey(ENV_KEY);

        // commands
    }
}