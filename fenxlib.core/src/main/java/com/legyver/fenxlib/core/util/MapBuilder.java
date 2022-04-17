package com.legyver.fenxlib.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder to make a map.
 */
public class MapBuilder {
    private final Map<String, Object> map = new HashMap<>();

    /**
     * Build the map
     * @return the built map
     */
    public Map<String, Object> build() {
        return map;
    }

    /**
     * Set an integer on the map
     * @param key the kep to associate the integer to
     * @param integer the integer
     * @return this builder
     */
    public MapBuilder with(String key, int integer) {
        map.put(key, integer);
        return this;
    }

    /**
     * Set a long on the map
     * @param key the key to associate the number to
     * @param number the number
     * @return this builder
     */
    public MapBuilder with(String key, long number) {
        map.put(key, number);
        return this;
    }
    /**
     * Set a double on the map
     * @param key the key to associate the number to
     * @param number the number
     * @return this builder
     */
    public MapBuilder with(String key, double number) {
        map.put(key, number);
        return this;
    }
    /**
     * Set a float on the map
     * @param key the key to associate the number to
     * @param number the number
     * @return this builder
     */
    public MapBuilder with(String key, float number) {
        map.put(key, number);
        return this;
    }
    /**
     * Set a boolean on the map
     * @param key the key to associate the flag with
     * @param flag the flag
     * @return this builder
     */
    public MapBuilder with(String key, boolean flag) {
        map.put(key, flag);
        return this;
    }
    /**
     * Set a character on the map
     * @param key the key to associate the character with
     * @param character the character
     * @return this builder
     */
    public MapBuilder with(String key, char character) {
        map.put(key, character);
        return this;
    }
    /**
     * Set an Object on the map
     * @param key the key to associate the Object with
     * @param value the Object
     * @return this builder
     */
    public MapBuilder with(String key, Object value) {
        map.put(key, value);
        return this;
    }

    /**
     * Create a MapBuildr with a key and integer value
     * @param key they key
     * @param value the integer value
     * @return a {@link MapBuilder}
     */
    public static MapBuilder of(String key, int value) {
        return new MapBuilder().with(key, value);
    }
    /**
     * Create a MapBuildr with a key and long value
     * @param key they key
     * @param value the long value
     * @return a {@link MapBuilder}
     */
    public static MapBuilder of(String key, long value) {
        return new MapBuilder().with(key, value);
    }
    /**
     * Create a MapBuildr with a key and double value
     * @param key they key
     * @param value the double value
     * @return a {@link MapBuilder}
     */
    public static MapBuilder of(String key, double value) {
        return new MapBuilder().with(key, value);
    }
    /**
     * Create a MapBuildr with a key and float value
     * @param key they key
     * @param value the float value
     * @return a {@link MapBuilder}
     */
    public static MapBuilder of(String key, float value) {
        return new MapBuilder().with(key, value);
    }
    /**
     * Create a MapBuildr with a key and boolean value
     * @param key they key
     * @param value the boolean value
     * @return a {@link MapBuilder}
     */
    public static MapBuilder of(String key, boolean value) {
        return new MapBuilder().with(key, value);
    }
    /**
     * Create a MapBuildr with a key and char value
     * @param key they key
     * @param value the char value
     * @return a {@link MapBuilder}
     */
    public static MapBuilder of(String key, char value) {
        return new MapBuilder().with(key, value);
    }
    /**
     * Create a MapBuildr with a key and Object value
     * @param key they key
     * @param value the Object value
     * @return a {@link MapBuilder}
     */
    public static MapBuilder of(String key, Object value) {
        return new MapBuilder().with(key, value);
    }
}
