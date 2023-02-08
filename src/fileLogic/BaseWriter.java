package fileLogic;

import java.util.LinkedHashMap;

public interface BaseWriter {
    void writeToFile(String path, LinkedHashMap<String[], String> values);
}
