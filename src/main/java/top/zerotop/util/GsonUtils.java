package top.zerotop.util;

import com.google.gson.Gson;

/**
 * Created by:zerotop  date:2020/5/3
 */
public class GsonUtils {
    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static Gson getGson() {
        return gson;
    }
}
