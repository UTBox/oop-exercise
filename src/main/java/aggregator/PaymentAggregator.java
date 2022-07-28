package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentAggregator {
//    static PaymentRules paymentRules;
//    static PaymentService paymentService;

    public static void main(String[] args) {
        MayaProcessor mayaProcessor = new MayaProcessor();
        GCashProcessor gCashProcessor = new GCashProcessor();
        GrabPayProcessor grabPayProcessor = new GrabPayProcessor();

        List<PaymentAggregatorRequest> paymentsToProcess = List.of(
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(1000000)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(33)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(2)),
                new PaymentAggregatorRequest(PaymentProvider.GRAB, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)));

        List<PaymentService> paymentServices = paymentsToProcess.stream().map(payment -> PaymentServiceFactory.getServiceType(payment)).collect(Collectors.toList());
    PaymentRules paymentRules = new PaymentRules();
    paymentRules.paymentChecker();
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
//        PaymentAggregatorRequest test = new PaymentAggregatorRequest(PaymentProvider.GRAB, Currency.getInstance("PHP"), BigDecimal.valueOf(1));
//        PaymentAggregatorRequest test2 = new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(10000000));
        PaymentAggregatorRequest test3 = new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(3000));
//        //TODO: find a way to instantiate MayaProcessor in a way that we can pass multiple payments
//        // and it will just process it all
//        PaymentService paymentService = new MayaProcessor();
//        PaymentService paymentService1 = new GrabPayProcessor();
//        PaymentService paymentService2 = new GCashProcessor();
//        paymentService.executePayment(test2);
//        paymentService1.executePayment(test);
//        paymentService2.executePayment(test3);
//        PaymentRules paymentRules = new PaymentRules();
//        paymentRules.paymentChecker(test3,);
//        //TODO: code from top to bottom
////        List paymentsToProcess = List.of(
////                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
////                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
////                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(1000000)));
//
//        }
            Datastore.showAllRecords();
//        }
    }
}
