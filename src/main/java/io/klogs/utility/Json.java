package io.klogs.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.klogs.model.Response;

public class Json {
    private static final Gson gson = new GsonBuilder().create();

    public static String toJson(Object value) {
        return gson.toJson(value);
    }

    public static <T> T toObject(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }
}
