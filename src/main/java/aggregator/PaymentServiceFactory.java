package aggregator;

import aggregator.datastore.PaymentProvider;

public class PaymentServiceFactory {
    private static PaymentService paymentService;
    public static PaymentService getServiceType(PaymentAggregatorRequest paymentAggregatorRequest) {
        if (paymentAggregatorRequest.getProvider() == PaymentProvider.GCASH) {
            return new GCashProcessor();
        } else if (paymentAggregatorRequest.getProvider() == PaymentProvider.GRAB) {
            return new GrabPayProcessor();
        } else if (paymentAggregatorRequest.getProvider() == PaymentProvider.MAYA) {
            return new MayaProcessor();
        }
        return null;
    }
}
