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
    public void executePayment(BigDecimal amount, Currency currencyType) {
        Currency currency = Currency.getInstance(String.valueOf(currencyType));
        try {
            String referenceId = grabpayApi.pay(currency, amount);
            Datastore.save(new PaymentRecord(PaymentProvider.GRAB, referenceId,
                    amount.toString(), PaymentStatus.SUCCESS));
            //TODO: implement business rules
        } catch (GrabPaymentFailedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void failedPayment(BigDecimal amount, Currency currencyType) {
           Currency currency = Currency.getInstance(String.valueOf(currencyType));
        try {
            grabpayApi.pay(currency, amount);
            String referenceId = grabpayApi.pay(currency, amount);
            Datastore.save(new PaymentRecord(PaymentProvider.GRAB, referenceId,
                    amount.toString(), PaymentStatus.FAILED));
        } catch (GrabPaymentFailedException e) {
            throw new RuntimeException(e);
        }
        //TODO: should not have to use the api if it fails
        // store it as a fail and then referenceId = null
    }
}
