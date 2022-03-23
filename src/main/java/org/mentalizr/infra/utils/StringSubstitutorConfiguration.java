package org.mentalizr.infra.utils;

import de.arthurpicht.utils.core.collection.Maps;

import java.util.HashMap;
import java.util.Map;

public class StringSubstitutorConfiguration {

    private final String pre;
    private final String post;
    private final Map<String, String> substitutions;

    public static class Builder {

        private String pre;
        private String post;
        private final Map<String, String> substitutions;

        public Builder() {
            this.pre = "{{";
            this.post = "}}";
            this.substitutions = new HashMap<>();
        }

        public Builder withPre(String pre) {
            this.pre = pre;
            return this;
        }

        public Builder withPost(String post) {
            this.post = post;
            return this;
        }

        public Builder withSubstitution(String name, String value) {
            this.substitutions.put(name, value);
            return this;
        }

        public StringSubstitutorConfiguration build() {
            return new StringSubstitutorConfiguration(this.pre, this.post, this.substitutions);
        }

    }

    public StringSubstitutorConfiguration(String pre, String post, Map<String, String> substitutions) {
        this.pre = pre;
        this.post = post;
        this.substitutions = Maps.immutableMap(substitutions);
    }

    public String getPre() {
        return this.pre;
    }

    public String getPost() {
        return this.post;
    }

    public Map<String, String> getSubstitutionMap() {
        return this.substitutions;
    }

}
