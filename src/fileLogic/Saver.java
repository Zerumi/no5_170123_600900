package fileLogic;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Abstract saver class. (Under construction)
 */
public class Saver<T extends Collection<E>, E> {


    private final Class<E> fullClassOfElement;


    /**
     * Initializer of fileLogic.Saver class.
     *
     * @param eClass Class of <code>E</code>. Should be provided due to Java's generic types restriction.
     */
    public Saver(Class<E> eClass)
    {
        fullClassOfElement = eClass;
    }

    /**
     * Saves provided collection into file by environment key.
     *
     * @param collection Current collection to save
     * @param envKey Environment key to filepath
     */
    public void saveCollection(T collection, String envKey)
    {
        String xmlPath = System.getenv(envKey);

        BaseWriter writer = new XMLWriter();

        writer.writeToFile(xmlPath, getValues(collection));
    }

    LinkedHashMap<String[], String> result;
    private LinkedHashMap<String[], String> getValues(T collection) {
        result = new LinkedHashMap<>();

        int iter = 0;
        for (E obj : collection) {
            Field[] fields = obj.getClass().getDeclaredFields();
            //.out.println(Arrays.toString(fields));
            for (Field field : fields) {
                try {
                    ArrayList<String> address = new ArrayList<>();
                    String[] canonicalNameSplitted = fullClassOfElement.getCanonicalName().toLowerCase().split("\\.");
                    address.add(canonicalNameSplitted[canonicalNameSplitted.length - 1] + iter);
                    addToCollection(obj, field, address);
                } catch (IllegalAccessException ignored) {
                }
            }
            iter++;
        }

        return result;
    }

    private void addToCollection(Object obj, Field field, ArrayList<String> currentAddress) throws IllegalAccessException {
        field.setAccessible(true);
        Object objValue = field.get(obj);

        if (Wrapper.isWrapperType(field.getType()))
        {
            // primitive
            String value = convert(field.getType(), objValue);

            String fieldNameToAddress = field.getName();
            currentAddress.add(fieldNameToAddress);
            String[] address = new String[currentAddress.size()];

            for (int i = 0; i < address.length; i++)
            {
                address[i] = currentAddress.get(i);
            }

            result.put(address, value);
        }
        else if (objValue != null)
        {
            Field[] fields = field.getType().getDeclaredFields();
            currentAddress.add(field.getName());
            for (Field fieldType : fields)
            {
                addToCollection(objValue, fieldType, currentAddress);
                currentAddress.remove(currentAddress.size() - 1);
            }
        }
    }

    private String convert(Class<?> targetType, Object obj) {
        PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
        editor.setValue(obj);
        return editor.getAsText();
    }

    private static class Wrapper
    {
        private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

        public static boolean isWrapperType(Class<?> clazz)
        {
            return WRAPPER_TYPES.contains(clazz);
        }

        private static Set<Class<?>> getWrapperTypes()
        {
            Set<Class<?>> ret = new HashSet<>();
            ret.add(Boolean.class);
            ret.add(Character.class);
            ret.add(Byte.class);
            ret.add(Short.class);
            ret.add(Integer.class);
            ret.add(Long.class);
            ret.add(Float.class);
            ret.add(Double.class);
            ret.add(Void.class);
            ret.add(Date.class);
            ret.add(String.class);
            ret.add(boolean.class);
            ret.add(char.class);
            ret.add(byte.class);
            ret.add(short.class);
            ret.add(int.class);
            ret.add(long.class);
            ret.add(float.class);
            ret.add(double.class);
            return ret;
        }
    }
}
