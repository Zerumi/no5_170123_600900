package fileLogic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class XMLReader {

    private static final Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");
    private final LinkedHashMap<String[], String> resultParsing;

    private final ArrayDeque<String> values;

    private final ArrayList<String> currentKeys;
    public XMLReader()
    {
        resultParsing = new LinkedHashMap<>();
        values = new ArrayDeque<>();
        currentKeys = new ArrayList<>();
    }

    /**
     * Reads XML file by path and returns parsed String[]/String map.
     *
     * @param path path to XML file
     * @return Map of read items
     * @throws IOException
     */
    public LinkedHashMap<String[], String> readXML(String path) throws IOException {
        Scanner scanner = new Scanner(Path.of(path));
        scanner.nextLine(); // skip 1st line

        while (scanner.hasNext())
        {
            String line = scanner.nextLine();
            line = line.replaceAll("\\t", "");
            if (line.isEmpty()) continue;

            String[] regexSplittedLine = line.split("<.*?>");
            pushValue(regexSplittedLine);

            Pattern nameStartPattern = Pattern.compile("<[^/]*?>");
            var nameStartPatternMatcher = nameStartPattern.matcher(line);
            if (nameStartPatternMatcher.find())
            {
                String nameStartTag = nameStartPatternMatcher.group();
                currentKeys.add(nameStartTag.substring(1, nameStartTag.length() - 1));
            }

            handleCurrentKeyAndValue();

            Pattern nameEndPattern = Pattern.compile("</\\S*?>");
            var nameEndPatternMatcher = nameEndPattern.matcher(line);
            if (nameEndPatternMatcher.find())
            {
                String nameEndTag = nameEndPatternMatcher.group();
                currentKeys.remove(nameEndTag.substring(2, nameEndTag.length() - 1));
            }
        }
        return resultParsing;
    }

    private void handleCurrentKeyAndValue() {
        if (!values.isEmpty())
        {
            String[] keys = new String[currentKeys.size()];
            int i = 0;
            for (String key : currentKeys)
            {
                keys[i++] = key;
            }

            myLogger.log(Level.FINE, Arrays.toString(keys) + " " + values.getLast());

            resultParsing.put(keys, values.getLast());
            values.removeLast();
        }
    }

    private void pushValue(String[] regexSplitLine) {
        for (String e : regexSplitLine)
        {
            if (e.isEmpty()) continue;
            values.addLast(e);
        }
    }
}
