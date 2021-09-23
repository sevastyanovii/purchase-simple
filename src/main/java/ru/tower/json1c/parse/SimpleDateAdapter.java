package ru.tower.json1c.parse;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateAdapter extends TypeAdapter<Date> {

    private final static ThreadLocal<SimpleDateFormat> simpleFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (null != value) {
            out.value(simpleFormat.get().format(value));
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        try {
            Date result = null;
            final String value = in.nextString();
            if (null != value && value.trim().length() > 0) {
                result = simpleFormat.get().parse(value);
            }
            return result;
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
