package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.grabpay.GrabPaymentFailedException;
import thirdpartyapi.grabpay.GrabpayApi;

import java.math.BigDecimal;
import java.util.Currency;

public class GrabPayProcessor implements PaymentService {
    PaymentRules paymentRules;
    GrabpayApi grabpayApi = new GrabpayApi();
    @Override
    public void executePayment(PaymentAggregatorRequest paymentAggregatorRequest) {

        BigDecimal paymentAmount = paymentAggregatorRequest.getAmount();
        Currency currency = paymentAggregatorRequest.getCurrency();
        try {
            String referenceId = grabpayApi.pay(currency, paymentAmount);
            Datastore.save(new PaymentRecord(PaymentProvider.GRAB, referenceId,
                        paymentAmount.toString(), PaymentStatus.SUCCESS));
            //TODO: implement business rules
        } catch (GrabPaymentFailedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void failedPayment(PaymentAggregatorRequest paymentAggregatorRequest) {
        BigDecimal paymentAmount = paymentAggregatorRequest.getAmount();
        Currency currency = paymentAggregatorRequest.getCurrency();
        try {
            grabpayApi.pay(currency, paymentAmount);
            String referenceId = grabpayApi.pay(currency, paymentAmount);
            Datastore.save(new PaymentRecord(PaymentProvider.GRAB, referenceId,
                    paymentAmount.toString(), PaymentStatus.FAILED));
        } catch (GrabPaymentFailedException e) {
            throw new RuntimeException(e);
        }

    }
}
