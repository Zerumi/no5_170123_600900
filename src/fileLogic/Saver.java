package fileLogic;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;

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
            System.out.println(Arrays.toString(fields));
            for (Field field : fields) {
                try {
                    ArrayList<String> address = new ArrayList<>();
                    address.add(fullClassOfElement.getCanonicalName().toLowerCase() + iter);
                    addToCollection(obj, field, address);
                } catch (IllegalAccessException ignored) {
                }
            }
            iter++;
        }

        return result;
    }

    private void addToCollection(Object obj, Field field, ArrayList<String> currentAddress) throws IllegalAccessException {
        if (Wrapper.isWrapperType(field.getType()))
        {
            // primitive
            Object objValue = field.get(obj); // don't go next (??)

            String value = objValue.toString();

            String[] address = new String[currentAddress.size()];

            for (int i = 0; i < address.length; i++)
            {
                address[i] = currentAddress.get(i);
            }

            result.put(address, value);
        }
        else if (field.get(obj) == null) return;
        else
        {
            Field[] fields = field.getType().getDeclaredFields();
            for (Field fieldType : fields)
            {
                currentAddress.add(field.getName());
                addToCollection(obj, fieldType, currentAddress);
            }
        }
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
            return ret;
        }
    }
}
