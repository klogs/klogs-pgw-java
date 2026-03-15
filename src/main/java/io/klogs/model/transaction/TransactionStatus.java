package io.klogs.model.transaction;

public enum TransactionStatus {
    Success("success"),
    Failed("failed"),
    Pending("pending"),
    Unknown("unknown");

    public final String value;
    TransactionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
