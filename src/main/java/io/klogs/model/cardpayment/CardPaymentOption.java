package io.klogs.model.cardpayment;

import java.util.List;

public class CardPaymentOption {
    public String title;
    public String issuerCode;
    public String cardProgram;
    public List<CardPaymentOptionCommRate> installments;
}
