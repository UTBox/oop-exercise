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
    public void executePayment(PaymentAggregatorRequest paymentAggregatorRequest) {
        BigDecimal paymentAmount = paymentAggregatorRequest.getAmount();
        Currency currency = paymentAggregatorRequest.getCurrency();
        GCashPaymentRequest paymentRequest = new GCashPaymentRequest(currency, paymentAmount);
        gCashPaymentResponse = gcashApi.submitPayment(paymentRequest);
        String referenceID = String.valueOf(gCashPaymentResponse.getPaymentId());
        boolean result = gCashPaymentResponse.isPaymentSuccessful();
        Datastore.save(new PaymentRecord(PaymentProvider.GCASH, referenceID,
                    paymentAmount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));

    }

    @Override
    public void failedPayment(PaymentAggregatorRequest paymentAggregatorRequest) {
        BigDecimal paymentAmount = paymentAggregatorRequest.getAmount();
        Currency currency = paymentAggregatorRequest.getCurrency();
        GCashPaymentRequest paymentRequest = new GCashPaymentRequest(currency, paymentAmount);
        gCashPaymentResponse = gcashApi.submitPayment(paymentRequest);
        String referenceID = String.valueOf(gCashPaymentResponse.getPaymentId());
        boolean result = false;
        Datastore.save(new PaymentRecord(PaymentProvider.GCASH, referenceID,
                paymentAmount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
    }
}
