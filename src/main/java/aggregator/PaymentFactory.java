package aggregator;

import aggregator.payment.Payment;

public class PaymentFactory {

    public Payment createPayment(PaymentAggregatorRequest request) {
        return request.getProvider().convertRequestToPayment(request);
    }

}
