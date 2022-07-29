package aggregator.paymentDir;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.gcash.GCashPaymentRequest;
import thirdpartyapi.gcash.GCashPaymentResponse;
import thirdpartyapi.gcash.GcashApi;

import java.math.BigDecimal;
import java.util.Currency;

public class Gcash implements Payment {
    private Currency currency;
    private BigDecimal amount;

    public Gcash(BigDecimal amount, Currency currency) {
        this.currency = currency;
        this.amount = amount;

        pay();
    }

    @Override
    public void pay() {
        GcashApi gcashApi = new GcashApi();
        if (businessRule()) {
            GCashPaymentRequest gcashRequest = new GCashPaymentRequest(currency, amount);
            GCashPaymentResponse response = gcashApi.submitPayment(gcashRequest);
            String referenceID = String.valueOf(response.getPaymentId());
            boolean successful = response.isPaymentSuccessful();
            Datastore.save(
                    new PaymentRecord(PaymentProvider.GCASH, referenceID,
                            String.valueOf(amount), successful ? PaymentStatus.SUCCESS : PaymentStatus.FAILED)
            );

        } else {
            Datastore.save(
                    new PaymentRecord(PaymentProvider.GCASH, null,
                            null, PaymentStatus.FAILED)
            );

        }


    }

    @Override
    public boolean businessRule() {
        if (currency.equals(Currency.getInstance("PHP")) && //TODO: condition for being able to exceed 1M
                (amount.compareTo(new BigDecimal(1000000)) == -1) &&
                (amount.compareTo(new BigDecimal(-1)) == 1)
        ) {
            return true;
        }
        return false;
    }
}
