package io.klogs.client;

import io.klogs.ClientOptions;
import io.klogs.handler.BasicJsonResponseHandler;
import io.klogs.model.Response;
import io.klogs.model.cardpayment.*;
import io.klogs.net.HttpClient;
import io.klogs.utility.Json;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URISyntaxException;
import java.util.List;

public class CardPayment extends HttpClient {

    public CardPayment(ClientOptions options) {
        super(options);
    }

    public CardPaymentResponse pay(CardPaymentRequest request) {
        return post("api/cardPayment", request, null, new BasicJsonResponseHandler<>(CardPaymentResponse.class).handler);
    }

    public Response provisionCommit(ProvisionCommitRequest request) {
        return post("api/cardPayment/provisionCommit", request, null, new BasicJsonResponseHandler<>(CardPaymentResponse.class).handler);
    }

    public CommissionResponse commissionsByBin(CommissionRequest request) throws URISyntaxException {
        var urlBuilder = new URIBuilder()
                .setPath("api/cardPayment/installments");

        if (request.getAmount() != null) {
            urlBuilder.setParameter("amount", request.getAmount().toString());
        }

        var requestUrl = urlBuilder.setParameter("binNumber", request.getBinNumber())
                .setParameter("currency", request.getCurrency())
                .build()
                .toString();

        return get(requestUrl, null, response -> {
            var entity = response.getEntity();

            try (var stream = entity.getContent()) {
                var content = new String(stream.readAllBytes());
                var obj = Json.toObject(content, List.class);

                return new CommissionResponse()
                        .setOptions(obj);
            } catch (Exception ex) {
                return null;
            }
        });
    }
}
