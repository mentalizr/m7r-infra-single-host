package org.mentalizr.infra.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;

public class StringSubstitutor {

    public static String substitute(String template, StringSubstitutorConfiguration configuration) {
        assertArgumentNotNull("template", template);
        assertArgumentNotNull("configuration", configuration);

        return substituteString(
                template,
                configuration.getSubstitutionMap(),
                configuration.getPre(),
                configuration.getPost());
    }

    public static List<String> substitute(List<String> template, StringSubstitutorConfiguration configuration) {
        assertArgumentNotNull("template", template);
        assertArgumentNotNull("configuration", configuration);

        List<String> strings = new ArrayList<>(template);
        for (int i=0; i < strings.size(); i++) {
            String string = substituteString(
                    strings.get(i),
                    configuration.getSubstitutionMap(),
                    configuration.getPre(),
                    configuration.getPost());
            strings.set(i, string);
        }
        return strings;
    }

    private static String substituteString(String template, Map<String, String> substitutionMap, String pre, String post) {
        String string = template;
        for (String name : substitutionMap.keySet()) {
            String substitutionExpression = pre + name + post;
            String value = substitutionMap.get(name);
            string = string.replace(substitutionExpression, value);
        }
        return string;
    }

}
