package org.mentalizr.infra.utils;

import de.arthurpicht.utils.core.collection.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringSubstitutorTest {

    @Test
    void string() {
        StringSubstitutorConfiguration configuration = new StringSubstitutorConfiguration.Builder()
                .withSubstitution("var1", "value1")
                .withSubstitution("var2", "value2")
                .build();

        String template = "Hier ein {{var1}} und ein {{var2}}";
        String product = StringSubstitutor.substitute(template, configuration);

        assertEquals("Hier ein value1 und ein value2", product);
    }

    @Test
    void listOfString() {
        StringSubstitutorConfiguration configuration = new StringSubstitutorConfiguration.Builder()
                .withSubstitution("var1", "value1")
                .withSubstitution("var2", "value2")
                .build();

        List<String> template = Lists.newArrayList("Hier ein {{var1}}.", "Hier ein {{var1}} und ein {{var2}}");

        List<String> product = StringSubstitutor.substitute(template, configuration);

        List<String> expectedProduct = Lists.newArrayList(
                "Hier ein value1.",
                "Hier ein value1 und ein value2"
        );

        assertEquals(expectedProduct, product);
    }

}