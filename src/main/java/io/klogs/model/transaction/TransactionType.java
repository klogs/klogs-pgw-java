package io.klogs.model.transaction;

public enum TransactionType {
    Sale("sale"),
    Refund("refund"),
    Void("void"),
    Provision("provision"),
    ProvisionCommit("provisionCommit");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
