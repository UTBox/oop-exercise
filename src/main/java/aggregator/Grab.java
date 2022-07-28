package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.grabpay.GrabPaymentFailedException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Random;

import static aggregator.PaymentAggregator.grabpayApi;

public class Grab implements Payment {

    private BigDecimal amount;
    private Currency currency;
    public Grab(BigDecimal amount, Currency currency) {
        this.amount =amount;
        this.currency=currency;
    }

    @Override
    public void pay() {
        if( currency.equals(Currency.getInstance("PHP")) && amount.compareTo(new BigDecimal(1000000))!=1 )   {
            try {
                String referenceID = String.valueOf( grabpayApi.pay(currency,amount));
                Datastore.save(new PaymentRecord(PaymentProvider.MAYA,referenceID,String.valueOf(amount),PaymentStatus.SUCCESS));
            } catch (GrabPaymentFailedException e) {
                throw new RuntimeException(e);
            }

        }else {
            Datastore.save(new PaymentRecord(PaymentProvider.GRAB,null,String.valueOf(0),PaymentStatus.FAILED));
        }

    }
}
