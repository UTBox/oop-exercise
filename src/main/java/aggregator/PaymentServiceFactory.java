package aggregator;

import aggregator.datastore.PaymentProvider;
import aggregator.paymentmethods.GCashProcessor;
import aggregator.paymentmethods.GrabPayProcessor;
import aggregator.paymentmethods.MayaProcessor;
import aggregator.paymentmethods.Payment;

public class PaymentServiceFactory {
    private static Payment payment;
    public static Payment getServiceType(PaymentAggregatorRequest paymentAggregatorRequest) {
        if (paymentAggregatorRequest.getProvider() == PaymentProvider.GCASH) {
            return new GCashProcessor(paymentAggregatorRequest.getAmount(), paymentAggregatorRequest.getCurrency());
        } else if (paymentAggregatorRequest.getProvider() == PaymentProvider.GRAB) {
            return new GrabPayProcessor(paymentAggregatorRequest.getAmount(), paymentAggregatorRequest.getCurrency());
        } else if (paymentAggregatorRequest.getProvider() == PaymentProvider.MAYA) {
            return new MayaProcessor(paymentAggregatorRequest.getAmount(), paymentAggregatorRequest.getCurrency());
        }
        return null;
    }
}
