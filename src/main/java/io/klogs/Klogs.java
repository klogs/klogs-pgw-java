package io.klogs;

import io.klogs.client.CardPayment;

public class Klogs {
    public Klogs(String apiKey, String secretKey, String baseURL) {
        var options = new ClientOptions()
                .setApiKey(apiKey)
                .setSecretKey(secretKey)
                .setBaseURL(baseURL);

        this.CardPayment = new CardPayment(options);
    }

    public final CardPayment CardPayment;
}
