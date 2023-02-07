package fileLogic;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Base Reader interface. Should be implemented for using in Loader class.
 *
 * @see Loader
 * @since 1.0
 * @author zerumi
 */
public interface BaseReader {

    LinkedHashMap<String[], String> readFromFile(String path) throws IOException;
}
