package ru.tower.json1c;

import java.time.ZoneId;
import java.util.Date;
import java.util.function.Supplier;

public class ParseUtils {

    public static final Integer year(Date from) {
        return from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
    }

    public static void assertThat(boolean cond, Supplier<? extends RuntimeException> exc) {
        if (!cond) {
            throw exc.get();
        }
    }
}
