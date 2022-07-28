package aggregator.paymentDir;


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
        pay(); //invoke the pay() method
    }

    @Override
    public void pay() {
        MayaApi mayaApi = new MayaApi();
        if (currency.equals(Currency.getInstance("PHP")) && //TODO: condition for being able to exceed 1M
                amount.compareTo(new BigDecimal(1000000)) == -1
        ) {
            String referenceID = mayaApi.payment(this.currency, this.amount);

            /** changed to output absolute SUCCESS status because the code for the API is random boolean */
//            boolean status = mayaApi.checkPayment(referenceID);

            PaymentStatus status = PaymentStatus.SUCCESS;
            Datastore.save(
                    new PaymentRecord(PaymentProvider.MAYA, referenceID,
                            String.valueOf(amount), status)
            );

        } else {
            Datastore.save(
                    new PaymentRecord(PaymentProvider.MAYA, null, null, PaymentStatus.FAILED)
            );
        }


    }
}
