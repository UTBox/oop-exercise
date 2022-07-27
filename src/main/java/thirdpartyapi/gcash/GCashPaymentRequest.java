package thirdpartyapi.gcash;

import java.math.BigDecimal;
import java.util.Currency;

public class GCashPaymentRequest {

    private Currency currency;
    private BigDecimal amount;

    public GCashPaymentRequest(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
