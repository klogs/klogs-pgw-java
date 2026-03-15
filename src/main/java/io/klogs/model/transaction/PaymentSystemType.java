package io.klogs.model.transaction;

public enum PaymentSystemType {
    VirtualPos("virtualPos"),
    PaymentFacilitator("paymentFacilitator"),
    ShoppingLoan("shoppingLoan"),
    DigitalPayment("digitalPayment"),
    Other("other");

    public String value;

    PaymentSystemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
