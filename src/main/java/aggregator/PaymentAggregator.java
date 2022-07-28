package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public class PaymentAggregator {
    static PaymentService paymentService;
    static MayaProcessor mayaProcessor = new MayaProcessor();
    static GCashProcessor gCashProcessor = new GCashProcessor();
    static GrabPayProcessor grabPayProcessor = new GrabPayProcessor();
    public static void main(String[] args) {
        List paymentsToProcess = List.of(
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(1000000)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(33)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(2)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("AUD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("AUD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)));

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
        PaymentAggregatorRequest test = new PaymentAggregatorRequest(PaymentProvider.GRAB, Currency.getInstance("PHP"), BigDecimal.valueOf(1));
        PaymentAggregatorRequest test2 = new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(10000000));
        PaymentAggregatorRequest test3 = new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(3000));
        //TODO: find a way to instantiate MayaProcessor in a way that we can pass multiple payments
        // and it will just process it all
        PaymentService paymentService = new MayaProcessor();
        PaymentService paymentService1 = new GrabPayProcessor();
        PaymentService paymentService2 = new GCashProcessor();
        paymentService.executePayment(test2);
        paymentService1.executePayment(test);
        paymentService2.executePayment(test3);
//        mayaProcessor.executePayment(test);
//        mayaProcessor.executePayment(test2);
        List modeOfPayment = List.of(
                mayaProcessor, grabPayProcessor, gCashProcessor
        );
//
//        for(int index = 0; index < paymentsToProcess.size(); index++){
//            new PaymentRules().paymentChecker(
//                    (PaymentAggregatorRequest) paymentsToProcess.get(index),
//                    (PaymentService) modeOfPayment.get(index)
//            );
//        }
        Datastore.showAllRecords();

    }
}
