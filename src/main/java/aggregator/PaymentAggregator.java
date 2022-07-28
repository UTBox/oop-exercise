package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public class PaymentAggregator {

    //    static MayaApi mayaApi = new MayaApi();
    public static void main(String[] args) {
//        List paymentsToProcess = List.of(
//                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
//                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
//                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(1000000)),
//                new PaymentAggregatorRequest(PaymentProvider.GRAB, Currency.getInstance("USD"), BigDecimal.valueOf(33)),
//                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
//                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
//                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
//                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(2)),
//                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("AUD"), BigDecimal.valueOf(200)),
//                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("AUD"), BigDecimal.valueOf(200)),
//                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)));

        //TODO: as a test, I will initialize multiple payments for different providers and a class should be able to take those payments and process them

        //TODO: business rules
        // - we only accept PHP
        // - if amount of payment is beyond 1 million fail it

        // sample usage of maya api
//        BigDecimal paymentAmount = BigDecimal.valueOf(100.00);
//        Currency currency = Currency.getInstance("PHP");
//        String referenceId = mayaApi.payment(currency, paymentAmount);
//        boolean successful = mayaApi.checkPayment(referenceId);
//        Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId, paymentAmount.toString(), successful?PaymentStatus.SUCCESS:PaymentStatus.FAILED));
        PaymentAggregatorRequest test = new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200));
        PaymentAggregatorRequest test2 = new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(2000));

        //TODO: find a way to instantiate MayaProcessor in a way that we can pass multiple payments
        // and it will just process it all
        MayaProcessor mayaProcessor = new MayaProcessor(test);
        mayaProcessor.executePayment(test);
        //TODO: Refactor this, because it always prints out status=SUCCESS
        mayaProcessor.executePayment(test);
        mayaProcessor.executePayment(test2);
//        PaymentRules paymentRules = new PaymentRules(test);
//        paymentRules.paymentChecker();
        Datastore.showAllRecords();
    }

}
