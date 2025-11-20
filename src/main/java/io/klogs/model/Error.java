package io.klogs.model;

public class Error {
    public String summary;

    public Error setSummary(String summary) {
        this.summary = summary;

        return this;
    }

    public String getSummary() {
        return this.summary;
    }
}
