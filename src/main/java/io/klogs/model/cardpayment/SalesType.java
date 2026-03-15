package io.klogs.model.cardpayment;

public enum SalesType {
    DirectSale("directSale"),
    Provision("provision");

    private final String value;

    SalesType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
