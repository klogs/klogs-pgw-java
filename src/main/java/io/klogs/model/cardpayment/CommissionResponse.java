package io.klogs.model.cardpayment;

import io.klogs.model.Response;

import java.util.List;

public class CommissionResponse extends Response {
    public List<CardPaymentOption> options;

    public CommissionResponse setOptions(List<CardPaymentOption> options) {
        this.options = options;

        return this;
    }
}
