package aggregator.paymentmethods;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.maya.MayaApi;


import java.math.BigDecimal;
import java.util.Currency;

public class MayaProcessor implements Payment {
    private BigDecimal amount;
    private Currency currency;

    public MayaProcessor(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
    @Override
    public void executePayment() {
        MayaApi mayaApi = new MayaApi();
        if (currency.equals(Currency.getInstance("PHP")) && amount.compareTo(new BigDecimal(1000000)) == -1) {
            String referenceId = mayaApi.payment(this.currency, this.amount);
            boolean result = mayaApi.checkPayment(referenceId);
            Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId,
                    amount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
        } else {
            Datastore.save(new PaymentRecord(PaymentProvider.MAYA, null,
                    String.valueOf(0), PaymentStatus.FAILED));
        }

    }
}
