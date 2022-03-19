package org.mentalizr.infra.utils;

import de.arthurpicht.utils.core.assertion.MethodPreconditions;

public class StringUtils {

    public static String assureEndsWith(String string, String ending) {
        MethodPreconditions.assertArgumentNotNull("string", string);
        if (string.endsWith(ending)) return string;
        return string + ending;
    }

    public static String concat(Iterable<?> objects) {
        MethodPreconditions.assertArgumentNotNull("objects", objects);
        StringBuilder stringBuilder = new StringBuilder();
        objects.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

}
