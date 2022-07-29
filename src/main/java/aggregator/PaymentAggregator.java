package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.paymentmethods.GCashProcessor;
import aggregator.paymentmethods.GrabPayProcessor;
import aggregator.paymentmethods.MayaProcessor;
import aggregator.paymentmethods.Payment;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentAggregator {
//    static PaymentRules paymentRules;
//    static PaymentService paymentService;

    public static void main(String[] args) {

        List<PaymentAggregatorRequest> paymentsToProcess = List.of(
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(2000000)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(100)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(33)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(2)),
                new PaymentAggregatorRequest(PaymentProvider.GRAB, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)));

        List<Payment> payments = paymentsToProcess.stream().map(payment -> PaymentServiceFactory.getServiceType(payment)).collect(Collectors.toList());
        payments.forEach(payment -> payment.executePayment());

            Datastore.showAllRecords();
    }
}
