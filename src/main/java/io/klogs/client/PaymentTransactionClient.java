package io.klogs.client;

import io.klogs.ClientOptions;
import io.klogs.handler.BasicJsonResponseHandler;
import io.klogs.model.Response;
import io.klogs.model.transaction.RefundRequest;
import io.klogs.model.transaction.TransactionDetailResponse;
import io.klogs.model.transaction.VoidRequest;
import io.klogs.net.HttpClient;

public class PaymentTransactionClient extends HttpClient {
    public PaymentTransactionClient(ClientOptions options) {
        super(options);
    }

    public TransactionDetailResponse detail(String referenceCode) {
        return get("api/trx", null,
                new BasicJsonResponseHandler<>(TransactionDetailResponse.class).handler);
    }

    public Response reverse(VoidRequest request) {
        return post("api/trx/void", request, null,
                new BasicJsonResponseHandler<>(Response.class).handler);
    }

    public Response refund(RefundRequest request) {
        return post("api/trx/refund", request, null,
                new BasicJsonResponseHandler<>(Response.class).handler);
    }

}