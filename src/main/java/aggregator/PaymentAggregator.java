package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import aggregator.datastore.PaymentRecord;
import aggregator.datastore.PaymentStatus;
import aggregator.paymentDir.Payment;
import aggregator.paymentDir.PaymentFactory;
import thirdpartyapi.maya.MayaApi;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentAggregator {


    public static void main(String[] args) {
        List<PaymentAggregatorRequest> paymentsToProcess = List.of(
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(1000000)),
                new PaymentAggregatorRequest(PaymentProvider.GRAB, Currency.getInstance("USD"), BigDecimal.valueOf(33)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(2)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("AUD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("AUD"), BigDecimal.valueOf(200)),
                new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("PHP"), BigDecimal.valueOf(0)));

        //TODO: as a test, I will initialize multiple payments for different providers and a class should be able to take those payments and process them

        //TODO: business rules
        // - we only accept PHP
        // - if amount of payment is beyond 1 million fail it

        List<Payment> payments = paymentsToProcess.stream().map(
                request -> new PaymentFactory().createPayment(request)
        ).collect(Collectors.toList());

         payments.forEach(payment -> payment.pay());


        // sample usage of maya api
//        BigDecimal paymentAmount = BigDecimal.valueOf(100.00);
//        Currency currency = Currency.getInstance("PHP");
//        String referenceId = mayaApi.payment(currency, paymentAmount);
//        boolean successful = mayaApi.checkPayment(referenceId);
//        Datastore.save(new PaymentRecord(PaymentProvider.MAYA, referenceId, paymentAmount.toString(), successful?PaymentStatus.SUCCESS:PaymentStatus.FAILED));

        Datastore.showAllRecords();
    }

}
