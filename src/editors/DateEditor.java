package editors;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateEditor implements PropertyEditor {

    Date result;

    public DateEditor()
    {
        result = new Date();
    }
    @Override
    public void setValue(Object value) {
        result = (Date)value;
    }

    @Override
    public Object getValue() {
        return result;
    }

    @Override
    public boolean isPaintable() {
        return false;
    }

    @Override
    public void paintValue(Graphics gfx, Rectangle box) {

    }

    @Override
    public String getJavaInitializationString() {
        return null;
    }

    @Override
    public String getAsText() {
        return null;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            result = Date.from(Instant.parse(text));
        }
        catch (DateTimeParseException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String[] getTags() {
        return new String[0];
    }

    @Override
    public Component getCustomEditor() {
        return null;
    }

    @Override
    public boolean supportsCustomEditor() {
        return false;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {}

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }
}