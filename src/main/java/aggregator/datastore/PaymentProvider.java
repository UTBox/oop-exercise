package aggregator.datastore;

import aggregator.*;
import aggregator.Provider.Gcash;
import aggregator.Provider.Grab;
import aggregator.Provider.Maya;
import aggregator.Provider.Payment;

public enum PaymentProvider {
    GCASH{
        @Override
        public Payment convertRequestToPayment(PaymentAggregatorRequest request) {
            return new Gcash(request.getAmount(),request.getCurrency());
        }
    }, MAYA{
        @Override
        public Payment convertRequestToPayment(PaymentAggregatorRequest request) {
            return new Maya(request.getAmount(),request.getCurrency());
        }
    }, GRAB{
        @Override
        public Payment convertRequestToPayment(PaymentAggregatorRequest request) {
            return new Grab(request.getAmount(),request.getCurrency());
        }
    };

    public abstract  Payment convertRequestToPayment(PaymentAggregatorRequest request) ;
}
