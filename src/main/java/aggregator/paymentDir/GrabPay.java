package aggregator.paymentDir;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.grabpay.GrabPaymentFailedException;
import thirdpartyapi.grabpay.GrabpayApi;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.Currency;

public class GrabPay implements Payment {

    private BigDecimal amount;
    private Currency currency;

    public GrabPay(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
        pay();
    }


    @Override
    public void pay() {
        GrabpayApi grabpayApi = new GrabpayApi();
        Collator collator = Collator.getInstance();

        if (businessRule()) {
            String referenceID = "";
            try {
                referenceID = String.valueOf(grabpayApi.pay(currency, amount));
            } catch (GrabPaymentFailedException e) {
                throw new RuntimeException(e);
            } finally {
                //TODO: write code for data save HERE
                Datastore.save(
                        new PaymentRecord(PaymentProvider.GRAB, referenceID,
                                String.valueOf(amount), PaymentStatus.SUCCESS)
                );
            }

        } else {
            Datastore.save(
                    new PaymentRecord(PaymentProvider.GRAB, null,
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
