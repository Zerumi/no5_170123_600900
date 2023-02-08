package fileLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

/**
 * XML Writer class for <code>Saver</code> class. It writes XML file by given path and consume
 * collection of paths and values, transforming it into XML file. (Under construction)
 *
 * @see LinkedHashMap
 * @since 1.0
 * @author zerumi
 */
public class XMLWriter implements BaseWriter {

    @Override
    public void writeToFile(String path, LinkedHashMap<String[], String> values) {
        try (PrintWriter writer = new PrintWriter(path)) {
            writer.println("<?xml version=\"1.0\"?>");
            writer.println();
            writer.println("<routes>");
            values.forEach((address, value) -> writeElement(writer, address, value));
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

    private void writeElement(PrintWriter writer, String[] address, String value)
    {
        for (int i = 0; i < address.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                writer.print("\t");
            }
            writer.println("<" + address[i] + ">");
        }
        for (int j = 0; j < address.length + 1; j++)
        {
            writer.print("\t");
        }
        writer.println(value);
        for (int i = address.length - 1; i >= 0; i--)
        {
            for (int j = 0; j < i + 1; j++)
            {
                writer.print("\t");
            }
            writer.println("</" + address[i] + ">");
        }
    }
}
