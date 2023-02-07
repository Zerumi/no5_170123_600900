import fileLogic.Loader;
import models.Route;

import java.util.HashSet;

public class Main {
    public static final String ENV_KEY = "path";
    private static HashSet<Route> routes = new HashSet<>();

    public static void main(String[] args) {
        Loader<HashSet<Route>, Route> loader = new Loader<>(routes.getClass(), Route.class);

        routes = loader.loadFromXMLbyEnvKey(ENV_KEY);

        for (var a : routes)
        {
            System.out.println(a.hashCode());
        }
    }
}