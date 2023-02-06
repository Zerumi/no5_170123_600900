import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

public class Loader<T extends Collection<E>, E> extends DefaultHandler {

    private T resultCollection;

    /**
     * Fill collection with elements from XML file.
     * XML filepath will be taken from Environment variable
     *
     * @param collection Collection to fill
     * @param envKey Key of System env. var to XML filepath
     */
    public void loadFromXML(T collection, String envKey)
    {
        try
        {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            Handler handler = new Handler();
            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);

            //String xmlPath = System.getenv(envKey);
            // fix envVariable
            String xmlPath = "D:\\Zerumi\\ЛАБЫ ПРОГА 2022\\no5_170123_600900\\routes.xml";
            FileReader lab5xml = new FileReader(xmlPath);
            xr.parse(new InputSource(lab5xml));
            System.out.println("Расчет окончен");
        }
        catch (SAXException ex)
        {
            System.out.println("В парсере возникла проблема: " + ex);
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден!" + ex);
        } catch (IOException ex) {
            System.out.println("Во время взаимодействия с входом-выходом возникла проблема: " + ex);
        }
    }

    private class Handler extends DefaultHandler
    {
        E handledObject;
        @Override
        public void startElement (String uri, String name,
                                  String qName, Attributes atts)
        {
            if ("".equals (uri))
                System.out.println("Start element: " + qName);
            else
                System.out.println("Start element: {" + uri + "}" + name);

            Field[] fields = handledObject.getClass().getDeclaredFields();

            for (Field f : fields)
            {
                Class type = f.getType();
                String fName = f.getName();
                System.out.println(type.getName() + " " + fName + ";");

                // type = Route
                // name = id

                // type vs Route in xml !!
            }
        }


        @Override
        public void endElement (String uri, String name, String qName)
        {
            if ("".equals (uri))
                System.out.println("End element: " + qName);
            else
                System.out.println("End element:   {" + uri + "}" + name);
            resultCollection.add(handledObject);
        }


        @Override
        public void characters (char ch[], int start, int length)
        {
            System.out.print("Characters:    \"");
            for (int i = start; i < start + length; i++) {
                switch (ch[i]) {
                    case '\\':
                        System.out.print("\\\\");
                        break;
                    case '"':
                        System.out.print("\\\"");
                        break;
                    case '\n':
                        System.out.print("\\n");
                        break;
                    case '\r':
                        System.out.print("\\r");
                        break;
                    case '\t':
                        System.out.print("\\t");
                        break;
                    default:
                        System.out.print(ch[i]);
                        break;
                }
            }
            System.out.print("\"\n");
        }
    }
}
