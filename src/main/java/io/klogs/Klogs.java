package io.klogs;

import io.klogs.client.CardPaymentClient;
import io.klogs.client.HostedPaymentClient;
import io.klogs.client.PaymentTransactionClient;

public class Klogs {
    public Klogs(String apiKey, String secretKey, String baseURL) {
        var options = new ClientOptions()
                .setApiKey(apiKey)
                .setSecretKey(secretKey)
                .setBaseURL(baseURL);

        this.CardPayment = new CardPaymentClient(options);
        this.PaymentTransaction = new PaymentTransactionClient(options);
        this.HostedPayment = new HostedPaymentClient(options);
    }

    public final CardPaymentClient CardPayment;
    public final PaymentTransactionClient PaymentTransaction;
    public final HostedPaymentClient HostedPayment;
}
