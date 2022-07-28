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
    @Override
    public void executePayment(PaymentAggregatorRequest paymentAggregatorRequest) {
        MayaApi mayaApi = new MayaApi();
        BigDecimal paymentAmount = paymentAggregatorRequest.getAmount();
        Currency currency = paymentAggregatorRequest.getCurrency();
        String referenceId = mayaApi.payment(currency, paymentAmount);
        boolean result = mayaApi.checkPayment(referenceId);
        Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId,
                paymentAmount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
        //TODO: implement business rules
//        if (paymentRules.paymentChecker(paymentAmount, currency.toString()) == true) {
//            Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId,
//                    paymentAmount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));
//        } else {
//            Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId,
//                    paymentAmount.toString(), PaymentStatus.FAILED));
//        }
    }
}
