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

    /**
     * Base method for reading file and providing Address-Value interpretation of this File.
     *
     * @param path Full path to file.
     * @return Collection of values with full address from File.
     * @throws IOException When something will go wrong during file handling
     */
    LinkedHashMap<String[], String> readFromFile(String path) throws IOException;
}
