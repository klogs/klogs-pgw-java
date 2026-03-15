package io.klogs.model.transaction;

import java.util.ArrayList;

public class PaymentProvider {
    public String id;

    public String title;

    public String version;

    public String issuerCode;

    public ArrayList<ProviderSupportedSystem> supportedSystems;

    public PaymentSystemType systemType;

    public String[] supportedCurrencies;

    public String[] supportedPaymentMethods;
}
