package aggregator.Provider;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.gcash.GCashPaymentRequest;
import thirdpartyapi.gcash.GCashPaymentResponse;

import java.math.BigDecimal;
import java.util.Currency;

import static aggregator.PaymentAggregator.gcashApi;


public class Gcash implements Payment {
    private BigDecimal amount;
    private Currency currency;

    public Gcash(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency =currency;
    }

    @Override
    public void pay() {
        if(currency.equals(Currency.getInstance("PHP")) && amount.compareTo(new BigDecimal(1000000))!=1 )   {
            GCashPaymentRequest request = new GCashPaymentRequest(currency,amount);
            GCashPaymentResponse gCashPaymentResponse = gcashApi.submitPayment(request);
            String referenceID =String.valueOf(gCashPaymentResponse.getPaymentId());
            boolean success =gCashPaymentResponse.isPaymentSuccessful();
            Datastore.save(new PaymentRecord(PaymentProvider.GCASH,referenceID,String.valueOf(amount),success? PaymentStatus.SUCCESS : PaymentStatus.FAILED));

        }else {
            Datastore.save(new PaymentRecord(PaymentProvider.GCASH,null,String.valueOf(0),PaymentStatus.FAILED));
        }
    }
}
