package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.maya.MayaApi;


import java.math.BigDecimal;
import java.util.Currency;

public class MayaProcessor implements PaymentService {
    PaymentRules paymentRules;
    MayaApi mayaApi = new MayaApi();

    @Override
    public void executePayment(BigDecimal amount, Currency currencyType) {
    Currency currency = Currency.getInstance(String.valueOf(currencyType));
    String referenceId = mayaApi.payment(currency, amount);
        boolean result = mayaApi.checkPayment(referenceId);
        Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId,
                amount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
    }

    @Override
    public void failedPayment(BigDecimal amount, Currency currencyType) {
        Currency currency = Currency.getInstance(String.valueOf(currencyType));
        String referenceId = null;
        boolean result = false;
        Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId,
                amount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
    }
    //TODO: should not have to use the api if it fails
    // store it as a fail and then referenceId = null
}
