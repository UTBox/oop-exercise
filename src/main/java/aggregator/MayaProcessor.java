package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import thirdpartyapi.maya.MayaApi;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public class MayaProcessor implements PaymentService {
    private final PaymentAggregatorRequest paymentAggregatorRequest;

    public MayaProcessor(PaymentAggregatorRequest paymentAggregatorRequest) {
        this.paymentAggregatorRequest = paymentAggregatorRequest;
    }
//    PaymentRules rules = new PaymentRules(paymentAggregatorRequest);

    @Override
    public void executePayment(PaymentAggregatorRequest paymentAggregatorRequest) {
//        BigDecimal paymentAmount = BigDecimal.valueOf(amount);
//        Currency currency = Currency.getInstance(currencyType);
        MayaApi mayaApi = new MayaApi();
        BigDecimal paymentAmount = paymentAggregatorRequest.getAmount();
        Currency currency = paymentAggregatorRequest.getCurrency();
        String referenceId = mayaApi.payment(currency, paymentAmount);
        boolean result = mayaApi.checkPayment(referenceId);
        Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId,
                paymentAmount.toString(), result ? PaymentStatus.SUCCESS : PaymentStatus.FAILED));

    }

//    @Override
//    public Datastore save(PaymentRecord paymentRecord) {
//        executePayment();
//    }


}
