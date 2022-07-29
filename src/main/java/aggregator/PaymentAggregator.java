package aggregator;

import aggregator.Provider.Payment;
import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentProvider;
import thirdpartyapi.gcash.GcashApi;
import thirdpartyapi.grabpay.GrabPaymentFailedException;
import thirdpartyapi.grabpay.GrabpayApi;
import thirdpartyapi.maya.MayaApi;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentAggregator {
    public static MayaApi mayaApi = new MayaApi();
    public static GrabpayApi grabpayApi = new GrabpayApi();
    public static GcashApi gcashApi = new GcashApi();

    public static void main(String[] args)  {
        List<PaymentAggregatorRequest> paymentsToProcess = List.of(
                    new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                    new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                    new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(1000000)),
                    new PaymentAggregatorRequest(PaymentProvider.GRAB, Currency.getInstance("USD"), BigDecimal.valueOf(33)),
                    new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                    new PaymentAggregatorRequest(PaymentProvider.MAYA, Currency.getInstance("PHP"), BigDecimal.valueOf(200)),
                    new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(200)),
                    new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(2)),
                    new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("AUD"), BigDecimal.valueOf(200)),
                    new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("AUD"), BigDecimal.valueOf(200)),
                    new PaymentAggregatorRequest(PaymentProvider.GCASH, Currency.getInstance("USD"), BigDecimal.valueOf(200)));

        //TODO: as a test, I will initialize multiple payments for different providers and a class should be able to take those payments and process them
        PaymentFactory paymentFactory = new PaymentFactory();
        List<Payment> payments = paymentsToProcess.stream().map(request -> paymentFactory.requestPayment(request)).collect(Collectors.toList());
        payments.forEach(payment -> payment.pay());

        //TODO: business rules
        // - we only accept PHP
        // - if amount of payment is beyond 1 million fail it

        Datastore.showAllRecords();
    }

}
