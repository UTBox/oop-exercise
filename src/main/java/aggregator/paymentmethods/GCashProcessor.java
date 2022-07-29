package aggregator.paymentmethods;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.gcash.GCashPaymentRequest;
import thirdpartyapi.gcash.GCashPaymentResponse;
import thirdpartyapi.gcash.GcashApi;

import java.math.BigDecimal;
import java.util.Currency;

public class GCashProcessor implements Payment {
    private  BigDecimal amount;
    private  Currency currency;

    public GCashProcessor(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public void executePayment() {
        GcashApi gcashApi = new GcashApi();
        if ((currency.equals(Currency.getInstance("PHP")) && amount.compareTo(new BigDecimal(1000000)) == -1)){
            GCashPaymentRequest paymentRequest = new GCashPaymentRequest(currency, amount);
            GCashPaymentResponse gCashPaymentResponse = gcashApi.submitPayment(paymentRequest);
            String referenceId = String.valueOf(gCashPaymentResponse.getPaymentId());
            boolean result = gCashPaymentResponse.isPaymentSuccessful();
            Datastore.save(new PaymentRecord(PaymentProvider.GCASH, referenceId,
                    amount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
        } else {
            Datastore.save(new PaymentRecord(PaymentProvider.GCASH, null,
                    String.valueOf(0), PaymentStatus.FAILED));
        }
    }
}
