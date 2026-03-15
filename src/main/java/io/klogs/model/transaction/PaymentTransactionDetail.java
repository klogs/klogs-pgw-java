package io.klogs.model.transaction;

import java.util.ArrayList;

public class PaymentTransactionDetail extends PaymentTransaction {
    public ArrayList<PaymentTransactionBase> relatedTransactions = new ArrayList<>();

    public PaymentProvider paymentProvider;

}
