# klogs-pgw-java

Klogs Payment Gateway Java client library.

## Features

- **Card Payment** — Direct and 3D-secure card payments, provision/commit flow, installment commission inquiry by BIN
- **Hosted Payment** — Redirect-based payment page creation
- **Payment Transaction** — Transaction detail retrieval, void (reversal), and refund operations

## Requirements

- Java 11+
- Gradle 7+

## Installation

Clone the repository and add it as a local module dependency, or build and publish to your local Maven repository:

```bash
./gradlew publishToMavenLocal
```

Then add to your `build.gradle`:

```gradle
dependencies {
    implementation 'io.klogs:klogs-pgw-java:1.0-SNAPSHOT'
}
```

## Initialization

```java
import io.klogs.Klogs;

var klogs = new Klogs("your-api-key", "your-secret-key", "https://pgw.klogs.io/");
```

The third argument (base URL) is optional. If omitted or `null`, it defaults to `https://pgw.klogs.io`.

The `Klogs` instance exposes three clients:

| Field                    | Type                       | Description                        |
|--------------------------|----------------------------|------------------------------------|
| `klogs.CardPayment`      | `CardPaymentClient`        | Card payment operations            |
| `klogs.PaymentTransaction` | `PaymentTransactionClient` | Transaction management             |
| `klogs.HostedPayment`    | `HostedPaymentClient`      | Hosted (redirect) payment page     |

---

## Card Payment

### Pay

Make a direct or 3D-secure card payment.

```java
import io.klogs.model.cardpayment.CardPaymentRequest;
import io.klogs.model.common.Address;
import io.klogs.model.common.CreditCard;
import java.math.BigDecimal;
import java.util.UUID;

var request = CardPaymentRequest.builder()
        .currency("TRY")
        .amount(BigDecimal.valueOf(52.50))
        .installment(1)
        .referenceCode(UUID.randomUUID().toString())
        .email("customer@example.com")
        .phone("5551234567")
        .nationalNumber("11111111110")
        .explanation("Order #1234")
        .use3d(false)
        .useStoredCard(false)
        .card(
                CreditCard.builder()
                        .cardHolderName("John Doe")
                        .cardNumber("5526080000000006")
                        .cvv("123")
                        .expireMonth(4)
                        .expireYear(2027)
                        .build()
        )
        .invoice(
                Address.builder()
                        .name("John")
                        .surname("Doe")
                        .countryCode("TR")
                        .city("Istanbul")
                        .district("Kadikoy")
                        .street1("Main St.")
                        .street2("Apt. 4")
                        .number("10")
                        .postalCode("34710")
                        .phone("5551234567")
                        .build()
        )
        .build();

var response = klogs.CardPayment.pay(request);

if (response.getSuccess()) {
    System.out.println("Payment successful!");
} else {
    System.out.println("Error: " + response.getError());
}
```

For 3D-secure payments set `.use3d(true)` and provide a `.returnURL("https://your-site.com/callback")`. The response will contain a `link` field to redirect the user to.

### Provision / Commit

Use `SalesType.Provision` for pre-authorization, then commit with `provisionCommit`.

```java
import io.klogs.model.cardpayment.ProvisionCommitRequest;
import io.klogs.model.cardpayment.SalesType;

// Step 1 — place a provision hold
var payRequest = CardPaymentRequest.builder()
        .chargeType(SalesType.Provision)
        // ... other fields ...
        .build();

var payResponse = klogs.CardPayment.pay(payRequest);

// Step 2 — commit the provision
var commitRequest = new ProvisionCommitRequest();
commitRequest.referenceCode = payResponse.getReferenceCode(); // use actual reference
commitRequest.amount = BigDecimal.valueOf(52.50);

var commitResponse = klogs.CardPayment.provisionCommit(commitRequest);
```

### Commission / Installment Options by BIN

Retrieve available installment options and commission rates for a given card BIN number.

```java
import io.klogs.model.cardpayment.CommissionRequest;
import java.math.BigDecimal;
import java.util.ArrayList;

var commRequest = CommissionRequest.builder()
        .binNumber("552608")
        .currency("TRY")
        .amount(BigDecimal.valueOf(100.00))
        .build();

var commResponse = klogs.CardPayment.commissionsByBin(commRequest);
```

---

## Hosted Payment

Create a hosted payment page and redirect the customer to the returned URL.

```java
import io.klogs.model.hostedpayment.HostedPaymentRequest;
import io.klogs.model.common.Address;
import java.math.BigDecimal;
import java.util.UUID;

var request = HostedPaymentRequest.builder()
        .currency("TRY")
        .amount(BigDecimal.valueOf(150.00))
        .referenceCode(UUID.randomUUID().toString())
        .email("customer@example.com")
        .phone("5551234567")
        .fullName("John Doe")
        .explanation("Order #5678")
        .returnURL("https://your-site.com/payment/callback")
        .invoice(
                Address.builder()
                        .name("John")
                        .surname("Doe")
                        .countryCode("TR")
                        .city("Istanbul")
                        .district("Kadikoy")
                        .street1("Main St.")
                        .number("10")
                        .postalCode("34710")
                        .phone("5551234567")
                        .build()
        )
        .build();

var response = klogs.HostedPayment.createPayment(request);

if (response.getSuccess()) {
    // Redirect customer to response.link
    System.out.println("Payment URL: " + response.link);
}
```

---

## Payment Transaction

### Get Transaction Detail

```java
var detail = klogs.PaymentTransaction.detail("your-reference-code");

if (detail.getSuccess()) {
    var trx = detail.transaction;
    System.out.println("Status: " + trx.getStatus());
}
```

### Void (Reverse)

Cancel an authorized or settled transaction.

```java
import io.klogs.model.transaction.VoidRequest;

var voidRequest = VoidRequest.builder()
        .referenceCode("your-reference-code")
        .build();

var response = klogs.PaymentTransaction.reverse(voidRequest);
```

### Refund

Refund a previously captured transaction (full or partial).

```java
import io.klogs.model.transaction.RefundRequest;
import java.math.BigDecimal;

var refundRequest = RefundRequest.builder()
        .referenceCode("your-reference-code")
        .amount(BigDecimal.valueOf(52.50))
        .build();

var response = klogs.PaymentTransaction.refund(refundRequest);
```

---

## Response

All client methods return a `Response` (or a subclass of it). Check `getSuccess()` before using the result.

```java
if (response.getSuccess()) {
    // handle success
} else {
    var error = response.getError();
    // handle error
}
```

---

## Dependencies

| Library | Version |
|---|---|
| Apache HttpClient5 | 5.5 |
| Gson | 2.13.2 |
| Lombok | 1.18.30 |
| Apache Commons Lang3 | 3.18.0 |

---

## License

See [LICENSE](LICENSE).