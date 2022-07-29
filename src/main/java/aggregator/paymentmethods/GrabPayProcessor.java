package aggregator.paymentmethods;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.grabpay.GrabPaymentFailedException;
import thirdpartyapi.grabpay.GrabpayApi;

import java.math.BigDecimal;
import java.util.Currency;

public class GrabPayProcessor implements Payment {
    private BigDecimal amount;
    private Currency currency;

    public GrabPayProcessor(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public void executePayment() {
        GrabpayApi grabpayApi = new GrabpayApi();
        if ((currency.equals(Currency.getInstance("PHP")) && amount.compareTo(new BigDecimal(1000000)) == -1)) {
            try {
                String referenceId = grabpayApi.pay(currency, amount);
                Datastore.save(new PaymentRecord(PaymentProvider.GRAB, referenceId,
                        amount.toString(), PaymentStatus.SUCCESS));
            } catch (GrabPaymentFailedException e) {
                throw new RuntimeException(e);
            }
        } else {
            Datastore.save(new PaymentRecord(PaymentProvider.GRAB, null,
                    String.valueOf(0), PaymentStatus.FAILED));
        }
    }
}
