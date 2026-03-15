package io.klogs.model.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class PaymentTransaction extends PaymentTransactionBase {
    public UUID id;

    public BigDecimal balance;

    public String clientReferenceCode;

    public BigDecimal commissionRate;

    public String currencyCode;

    public String paymentProviderId;

    public BigDecimal rewardAmount;

    public Integer plusInstallment;

    public Integer paymentDeferral;

    public Integer installment;

    public String orderId;

    public String paymentMethod = "klogs";

    public NameCodeModel paymentChannel;

    public TransactionUser user;

    public NameCodeModel paymentSystem;

    public ArrayList<NameValueModel> paymentItems;

    public ArrayList<NameValueModel> fields;
}
