package ru.tower.json1c.db;

public class QueryParam {

    private final String name;
    private final Object value;

    public QueryParam(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public static QueryParam param(String name, Object value) {
        return new QueryParam(name, value);
    }
}
