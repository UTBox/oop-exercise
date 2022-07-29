package aggregator.Provider;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;

import java.math.BigDecimal;
import java.util.Currency;

import static aggregator.PaymentAggregator.mayaApi;

public class Maya implements Payment {

    private BigDecimal amount;
    private Currency currency;

    public Maya(BigDecimal amount, Currency currency) {
        this.amount=amount;
        this.currency=currency;
    }

    @Override
    public void pay() {
        if( currency.equals(Currency.getInstance("PHP")) && amount.compareTo(new BigDecimal(1000000))!=1 )   {
            String referenceID = mayaApi.payment(this.currency, this.amount);
            boolean success =mayaApi.checkPayment(referenceID);
            Datastore.save(new PaymentRecord(PaymentProvider.MAYA,referenceID,String.valueOf(amount),success? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
        }else {
            Datastore.save(new PaymentRecord(PaymentProvider.MAYA,null,String.valueOf(0),PaymentStatus.FAILED));
        }


    }
}
