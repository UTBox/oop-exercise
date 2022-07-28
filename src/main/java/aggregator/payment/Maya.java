package aggregator.payment;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.maya.MayaApi;

import java.math.BigDecimal;
import java.util.Currency;

public class Maya implements Payment {

    private BigDecimal amount;
    private Currency currency;

    public Maya(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
    @Override
    public void pay() {
        MayaApi api = new MayaApi();
        if (currency.equals(Currency.getInstance("PHP"))) {
            String referenceId = api.payment(this.currency, this.amount);
            boolean status = api.checkPayment(referenceId);
            Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId, String.valueOf(amount), status?PaymentStatus.SUCCESS:PaymentStatus.FAILED));
        } else {
            Datastore.save(new PaymentRecord(PaymentProvider.MAYA, null, String.valueOf(0), PaymentStatus.FAILED));
        }

    }


}
