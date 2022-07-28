package aggregator;

import java.math.BigDecimal;
import java.util.Currency;

public interface PaymentService {
    void executePayment(BigDecimal amount, Currency currencyType);
    void failedPayment(BigDecimal amount, Currency currencyType);
//    void savePayment(PaymentRecord paymentRecord);
//    public Datastore save(PaymentRecord paymentRecord);
}
