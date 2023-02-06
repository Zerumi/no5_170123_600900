import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Abstract loader class.
 * @param <T> Collection to fill
 * @param <E> Type of collection elements
 */

public class Loader<T extends Collection<E>, E> {

    private T resultCollection;

    private final Class<E> fullClassOfElement;
    private final Class<T> fullClassOfCollection;

    /**
     * Initializer of Loader class.
     *
     * @param tClass Class of T. Should be provided due to Java's generic types restriction.
     * @param eClass Class of E. Should be provided due to Java's generic types restriction.
     */
    public Loader(Class<T> tClass, Class<E> eClass)
    {
        fullClassOfCollection = tClass;
        fullClassOfElement = eClass;
        try {
            resultCollection = fullClassOfCollection.newInstance();
        }
        catch (Exception e)
        {
            System.out.println("В этой программе возникла проблема: " + e);
        }
    }

    /**
     * Fill collection with elements from XML file.
     * XML filepath will be taken from Environment variable
     *
     * @param envKey Key of System env. var to XML filepath
     *
     * @return Filled collection of type T
     */
    public T loadFromXML(String envKey)
    {
        try {
            //String xmlPath = System.getenv(envKey);
            // fix envVariable
            //String xmlPath = "D:\\Zerumi\\ЛАБЫ ПРОГА 2022\\no5_170123_600900\\routes.xml";
            String xmlPath = "/Users/zerumi/IdeaProjects/no5_170123_600900/routes.xml";

            XMLReader reader = new XMLReader();
            LinkedHashMap<String[], String> parsedValues = reader.readXML(xmlPath);

            fillCollection();
        } catch (IOException e) {
            System.out.println("Во время работы с вводом-выводом произошла ошибка! " + e);
        }

        return resultCollection;
    }

    private void fillCollection() {



    }
}
