package aggregator;

import aggregator.datastore.PaymentProvider;

import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * This is the request that we will use in the main class.
 *
 * */
public class PaymentAggregatorRequest {
    private PaymentProvider provider;
    private Currency currency;
    private BigDecimal amount;

    public PaymentAggregatorRequest(PaymentProvider provider, Currency currency, BigDecimal amount) {
        this.provider = provider;
        this.currency = currency;
        this.amount = amount;
    }

    public PaymentProvider getProvider() {
        return provider;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
