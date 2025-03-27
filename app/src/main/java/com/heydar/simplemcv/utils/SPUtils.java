package com.heydar.simplemcv.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Utility class for managing SharedPreferences in an optimized way.
 */
public class SPUtils {

    /**
     * Saves a key-value pair in SharedPreferences.
     *
     * @param sp     SharedPreferences instance
     * @param key    Key to store the value
     * @param value  Value to store
     * @param async  If true, apply changes asynchronously; otherwise, commit synchronously
     */
    public static void put(SharedPreferences sp, String key, Object value, boolean async) {
        SharedPreferences.Editor editor = sp.edit();

        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else {
            throw new IllegalArgumentException("Unsupported data type");
        }

        if (async) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    /**
     * Retrieves a value from SharedPreferences.
     *
     * @param sp          SharedPreferences instance
     * @param key         Key to retrieve the value
     * @param defaultObj  Default value if key doesn't exist
     * @return Retrieved value or the default if key is not found
     */
    public static Object get(SharedPreferences sp, String key, Object defaultObj) {
        if (defaultObj instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObj);
        } else if (defaultObj instanceof Float) {
            return sp.getFloat(key, (Float) defaultObj);
        } else if (defaultObj instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObj);
        } else if (defaultObj instanceof Long) {
            return sp.getLong(key, (Long) defaultObj);
        } else if (defaultObj instanceof String) {
            return sp.getString(key, (String) defaultObj);
        } else {
            throw new IllegalArgumentException("Unsupported default data type");
        }
    }

    /**
     * Removes a key from SharedPreferences.
     *
     * @param sp     SharedPreferences instance
     * @param key    Key to remove
     * @param async  If true, apply changes asynchronously; otherwise, commit synchronously
     */
    public static void remove(SharedPreferences sp, String key, boolean async) {
        SharedPreferences.Editor editor = sp.edit().remove(key);
        if (async) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    /**
     * Retrieves all key-value pairs from SharedPreferences.
     *
     * @param sp SharedPreferences instance
     * @return Map containing all key-value pairs
     */
    public static Map<String, ?> getAll(SharedPreferences sp) {
        return sp.getAll();
    }

    /**
     * Clears all data from SharedPreferences.
     *
     * @param sp     SharedPreferences instance
     * @param async  If true, apply changes asynchronously; otherwise, commit synchronously
     */
    public static void clear(SharedPreferences sp, boolean async) {
        SharedPreferences.Editor editor = sp.edit().clear();
        if (async) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    /**
     * Checks if a key exists in SharedPreferences.
     *
     * @param sp  SharedPreferences instance
     * @param key Key to check
     * @return True if key exists, false otherwise
     */
    public static boolean contains(SharedPreferences sp, String key) {
        return sp.contains(key);
    }
}
