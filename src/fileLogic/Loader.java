package fileLogic;

import editors.DateEditor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Abstract loader class. It uses <code>java.lang.reflect</code> API for changing fields taken from XML file
 * and converted by <code>java.beans.PropertyEditor</code>. It fills Elements of type E to collection of type T
 * @param <T> Collection to fill
 * @param <E> Type of collection elements
 * @see java.lang.reflect
 * @see java.beans.PropertyEditor
 * @since 1.0
 * @author zerumi
 */

public class Loader<T extends Collection<E>, E> {

    private static final Logger myLogger = Logger.getLogger("com.github.zerumi.lab5");

    private T resultCollection;

    private final Class<E> fullClassOfElement;

    /**
     * Initializer of fileLogic.Loader class.
     *
     * @param tClass Class of <code>T</code>. Should be provided due to Java's generic types restriction.
     * @param eClass Class of <code>E</code>. Should be provided due to Java's generic types restriction.
     */
    public Loader(Class<T> tClass, Class<E> eClass)
    {
        setupConverter();
        fullClassOfElement = eClass;
        try {
            resultCollection = tClass.newInstance();
            buildingObject = buildElement();
            addObjectToCollection(buildingObject);
        }
        catch (Exception e)
        {
            myLogger.log(Level.SEVERE, "В программе возникла проблема: " + e);
        }
    }


    private void setupConverter() {
        PropertyEditorManager.registerEditor(Date.class, DateEditor.class);
    }

    /**
     * Manually register editor for your datatype. Call this method before parsing XML.
     *
     * @param typeToEdit Type to register in editor
     * @param editor Editor of type. Should implement PropertyEditor interface
     *
     * @see PropertyEditor
     */

    public void setupConverter(Class<?> typeToEdit, Class<? extends PropertyEditor> editor)
    {
        PropertyEditorManager.registerEditor(typeToEdit, editor);
    }

    /**
     * Fill collection with elements from XML file.
     * XML filepath will be taken from Environment variable
     *
     * @param envKey Key of System env. var to XML filepath
     *
     * @return Filled collection of type T
     */
    public T loadFromXMLbyEnvKey(String envKey)
    {
        try {
            //String xmlPath = System.getenv(envKey);
            // fix envVariable
            //String xmlPath = "D:\\Zerumi\\ЛАБЫ ПРОГА 2022\\no5_170123_600900\\routes.xml";
            String xmlPath = "/Users/zerumi/IdeaProjects/no5_170123_600900/routes.xml";

            XMLReader reader = new XMLReader();
            LinkedHashMap<String[], String> parsedValues = reader.readXML(xmlPath);

            fillCollection(parsedValues);
        } catch (IOException e) {
            myLogger.log(Level.SEVERE, "Во время работы с вводом-выводом произошла ошибка! " + e);
        }

        return resultCollection;
    }

    private void fillCollection(LinkedHashMap<String[], String> parsedValues) {
        parsedValues.forEach(this::addFieldToElement);
    }

    private int latestKnownIndex = 0;
    private E buildingObject;

    private void addFieldToElement(String[] pathToField, String s) {
        // match the correct object
        var matcher = Pattern.compile("[0-9]+?").matcher(pathToField[1]);
        int currentIndex;
        if (matcher.find())
        {
            currentIndex = Integer.parseInt(matcher.group());
        }
        else {
            myLogger.log (Level.WARNING, "Поле имеет некорректный адрес и будет пропущено. " + Arrays.toString(pathToField));
            return;
        }
        if (latestKnownIndex != currentIndex)
        {
            latestKnownIndex = currentIndex;
            if (resultCollection.size() > currentIndex)
            {
                // object exists
                Iterator<E> iterator = resultCollection.iterator();
                for (int i = 0; i <= currentIndex; i++)
                {
                    buildingObject = iterator.next();
                }
            }
            else
            {
                // build new
                buildingObject = buildElement();
                addObjectToCollection(buildingObject);
            }
        }

        // set field value

        for (int i = 2; i < pathToField.length; i++)
        {
            try {
                setField(fullClassOfElement, pathToField, 2, s, buildingObject);

            } catch (NoSuchFieldException | InstantiationException | IllegalAccessException e) {
                myLogger.log(Level.SEVERE, "Поле не было обнаружено: " + e);
            }
        }
    }

    private void addObjectToCollection(E buildingObject) {
        resultCollection.add(buildingObject);
    }

    private <U> void setField (Class<?> currentType, String[] fullPath, int currentIndex, String value, U object) throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        Field fieldToSet = currentType.getDeclaredField(fullPath[currentIndex++]);
        fieldToSet.setAccessible(true);
        Class<U> nextType = (Class<U>) fieldToSet.getType();
        if (currentIndex != fullPath.length)
        {
            U nextObject = (U) fieldToSet.get(object);
            if (nextObject == null)
            {
                nextObject = nextType.newInstance();
                fieldToSet.set(object, nextObject);
            }
            setField(nextType, fullPath, currentIndex, value, nextObject);
        }
        else
        {
            try
            {
                fieldToSet.set(object, convert(nextType, value));
            }
            catch (NullPointerException e)
            {
                myLogger.log(Level.WARNING, "XML файл поврежден. Строка с данными " + value + " (Адрес: " + Arrays.toString(fullPath) + ") была пропущена.");
                myLogger.log(Level.INFO, "Если вы считаете, что произошла ошибка, добавьте в регистр обработчик. Взгляните на Loader.setupConverter в Javadoc.");
            }
        }
    }

    private Object convert(Class<?> targetType, String text) {
        PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
        editor.setAsText(text);
        return editor.getValue();
    }

    private E buildElement() {

        E result = null;
        try
        {
            result = fullClassOfElement.newInstance();
        } catch (InstantiationException e) {
            myLogger.log(Level.SEVERE, "При инициализации элемента возникла проблема! " + e);
        } catch (IllegalAccessException e) {
            myLogger.log(Level.SEVERE, "Ошибка доступа! " + e);
        }
        return result;
    }
}
