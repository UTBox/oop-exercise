package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.gcash.GCashPaymentRequest;
import thirdpartyapi.gcash.GCashPaymentResponse;
import thirdpartyapi.gcash.GcashApi;

import java.math.BigDecimal;
import java.util.Currency;

public class GCashProcessor implements PaymentService{
    GCashPaymentResponse gCashPaymentResponse;
    GcashApi gcashApi = new GcashApi();
//    PaymentRules paymentRules;
    @Override
    public void executePayment(BigDecimal amount, Currency currencyType) {
        Currency currency = Currency.getInstance(String.valueOf(currencyType));
        GCashPaymentRequest paymentRequest = new GCashPaymentRequest(currency, amount);
        gCashPaymentResponse = gcashApi.submitPayment(paymentRequest);
        String referenceID = String.valueOf(gCashPaymentResponse.getPaymentId());
        boolean result = gCashPaymentResponse.isPaymentSuccessful();
        Datastore.save(new PaymentRecord(PaymentProvider.GCASH, referenceID,
                amount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
    }

    @Override
    public void failedPayment(BigDecimal amount, Currency currencyType) {
        Currency currency = Currency.getInstance(String.valueOf(currencyType));
        GCashPaymentRequest paymentRequest = new GCashPaymentRequest(currency, amount);
        gCashPaymentResponse = gcashApi.submitPayment(paymentRequest);
        String referenceID = String.valueOf(gCashPaymentResponse.getPaymentId());
        boolean result = false;
        Datastore.save(new PaymentRecord(PaymentProvider.GCASH, referenceID,
                amount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
        //TODO: should not have to use the api if it fails
        // store it as a fail and then referenceId = null
    }

}
