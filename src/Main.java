import models.Route;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.ParserAdapter;

import java.util.HashSet;

public class Main {
    public static final String ENV_KEY = "path";
    private static HashSet<Route> routes = new HashSet<>();

    public static void main(String[] args) {
        Loader<HashSet<Route>, Route> loader = new Loader<>(routes.getClass(), Route.class);

        routes = loader.loadFromXML(ENV_KEY);
    }
}