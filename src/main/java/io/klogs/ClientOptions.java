package io.klogs;

public class ClientOptions {
    private String apiKey;
    private String secretKey;
    private String baseURL;


    public String getApiKey() {
        return apiKey;
    }

    public ClientOptions setApiKey(String apiKey) {
        this.apiKey = apiKey;

        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public ClientOptions setSecretKey(String secretKey) {
        this.secretKey = secretKey;

        return this;
    }

    public String getBaseURL() {
        return baseURL == null ? "https://pgw.klogs.io" : baseURL;
    }

    public ClientOptions setBaseURL(String baseURL) {
        this.baseURL = baseURL;

        return this;
    }
}
