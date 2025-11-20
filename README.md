# klogs-pgw-java
Klogs Payment Gateway Java client package

```java
package io.klogs;

public class Main {
    public static void main(String[] args) {
        
        var klogs = new Klogs("api-key", "secret-key", "http://localhost:37274/");

        var req = CardPaymentRequest.builder()
                .currency("TRY")
                .amount(BigDecimal.valueOf(52.5))
                .installment(1)
                .email("yusuf@klogs.io")
                .phone("5554443131")
                .referenceCode(UUID.randomUUID().toString())
                .useStoredCard(false)
                .use3d(false)
                .nationalNumber("11111111110")
                .explanation("Test from klogs java client")
                .card(
                        CreditCard.builder()
                                .cardHolderName("Yusuf Keser")
                                .cardNumber("5526080000000006")
                                .cvv("423")
                                .expireMonth(4)
                                .expireYear(2027)
                                .build()
                )
                .invoice(
                        Address.builder()
                                .name("Yusuf")
                                .surname("Keser")
                                .countryCode("TR")
                                .city("Hatay")
                                .district("Antakya")
                                .street1("XYZ mh.")
                                .street2("ABC 1. sk.")
                                .number("4")
                                .postalCode("31526")
                                .phone("5554443131")
                                .company("klogs inc.")
                                .fax("5234123212")
                                .build()
                )
                .build();

        var response = klogs.CardPayment.pay(req);

        System.out.println("Done!");
    }
}
```