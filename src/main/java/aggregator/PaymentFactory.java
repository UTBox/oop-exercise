package aggregator;

import aggregator.Provider.Payment;

public class PaymentFactory {
    public Payment requestPayment(PaymentAggregatorRequest request){
        return request.getProvider().convertRequestToPayment(request);
    }
}