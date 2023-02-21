package fileLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.logging.Logger;

/**
 * XML Writer class for <code>Saver</code> class. It writes XML file by given path and consume
 * collection of paths and values, transforming it into XML file.
 *
 * @see LinkedHashMap
 * @since 1.0
 * @author zerumi
 */
public class XMLWriter implements BaseWriter {

    private static final Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");

    @Override
    public void writeToFile(String path, LinkedHashMap<String[], String> values) {
        try (PrintWriter writer = new PrintWriter(path)) {

            while (values.values().remove(null));

            writer.println("<?xml version=\"1.0\"?>");
            writer.println();
            writer.println("<routes>");
            values.forEach((address, value) -> writeElement(writer, address, getNextAddress(values, address), value));
            writer.println("</routes>");

        } catch (FileNotFoundException e) {
            File xml = new File(path);
            try {
                boolean ignored = xml.createNewFile();
            } catch (IOException ex) {
                ex.initCause(e);
                System.out.println("Creating file finished with error! " + ex);
                System.out.println("Try to create this file manually: " + path);
                System.out.println("Or change environment variable.");
                System.out.println("Please, don't modify/remove this xml file. It goes to unknown consequences.");
            }
        }
    }

    int lastKnownI = 0;
    private void writeElement(PrintWriter writer, String[] address, String[] nextAddress, String value)
    {
        if (value == null) return;

        for (int i = lastKnownI; i < address.length; i++)
        {
            for (int j = 0; j < i + 1; j++)
            {
                writer.print("\t");
            }
            myLogger.fine("Opening: " + address[i] + "Addresses // next: " + Arrays.toString(nextAddress) + ", current: " + Arrays.toString(address));
            writer.println("<" + address[i] + ">");
        }
        for (int j = 0; j < address.length + 1; j++)
        {
            writer.print("\t");
        }
        writer.println(value);
        for (lastKnownI = address.length; lastKnownI > 0; lastKnownI--) {
            myLogger.fine("Addresses // next: " + Arrays.toString(nextAddress) + ", current: " + Arrays.toString(address) + " / lastI: " + lastKnownI);
            if (nextAddress.length < lastKnownI || !Objects.equals(nextAddress[lastKnownI - 1], address[lastKnownI - 1])) {
                for (int j = 0; j < lastKnownI; j++) {
                    writer.print("\t");
                }
                writer.println("</" + address[lastKnownI - 1] + ">");
                myLogger.fine("Closing: " + address[lastKnownI - 1]);
            }
            else break;
        }
    }

    private String[] getNextAddress(LinkedHashMap<String[], String> map, String[] key) {
        List<String[]> keys = new ArrayList<>(map.keySet());
        int index = keys.indexOf(key);

        if (index < 0 || index >= keys.size() - 1)
            return new String[0];

        String[] k = keys.get(index + 1);
        return Objects.requireNonNullElseGet(k, () -> new String[0]);
    }
}
