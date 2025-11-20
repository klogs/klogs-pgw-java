package io.klogs;

import io.klogs.model.cardpayment.CardPaymentRequest;
import io.klogs.model.cardpayment.CommissionRequest;
import io.klogs.model.common.Address;
import io.klogs.model.common.CreditCard;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        final String apiKey = "lrM54xgeBRw6kABrmyz5GixNW54Eg9zWt3Orgi35E";
        final String secretKey = "G99T1V+bzzfU+X0Zv+xvCB4LwLstYtymL8ybsZjvdLGzl98EuNh3AeYUCA1pAOYa6rxv3Y5HsFvhs2v3ufx+nQ==";

        var klogs = new Klogs(apiKey, secretKey, "http://localhost:37274/");

        CardPaymentRequest req = CardPaymentRequest.builder()
                .currency("TRY")
                .amount(BigDecimal.valueOf(52.5))
                .installment(1)
                .email("emre@klogs.io")
                .phone("5074458449")
                .referenceCode(UUID.randomUUID().toString())
                .useStoredCard(false)
                .use3d(false)
                .nationalNumber("19007652730")
                .explanation("Test from klogs java client")
                .card(
                        CreditCard.builder()
                                .cardHolderName("Emre Çağlar")
                                .cardNumber("5526080000000006")
                                .cvv("423")
                                .expireMonth(4)
                                .expireYear(2027)
                                .build()
                )
                .invoice(
                        Address.builder()
                                .name("Emre")
                                .surname("Çağlar")
                                .countryCode("TR")
                                .city("Ordu")
                                .district("Ünye")
                                .street1("Burunucu mh.")
                                .street2("Orkide 1. sk.")
                                .number("4")
                                .postalCode("52300")
                                .phone("5074458449")
                                .company("klogs inc.")
                                .fax("4523233887")
                                .build()
                )
                .build();

        var response = klogs.CardPayment.pay(req);

        var response = klogs.CardPayment.commissionsByBinAsync(
                CommissionRequest.builder()
                        .binNumber("435508")
                        .currency("TRY")
                        .build()
        );

        System.out.println("Done!");
//        System.out.println(response.behavior);
//        System.out.println(response.link);
    }
}