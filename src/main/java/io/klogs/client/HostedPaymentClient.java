package io.klogs.client;

import io.klogs.ClientOptions;
import io.klogs.handler.BasicJsonResponseHandler;
import io.klogs.model.hostedpayment.CreateHostedPaymentResponse;
import io.klogs.model.hostedpayment.HostedPaymentRequest;
import io.klogs.net.HttpClient;

public class HostedPaymentClient extends HttpClient {

    public HostedPaymentClient(ClientOptions options) {
        super(options);
    }

    public CreateHostedPaymentResponse createPayment(HostedPaymentRequest request) {
        return post("api/payment", request, null,
                new BasicJsonResponseHandler<>(CreateHostedPaymentResponse.class).handler);
    }
}
