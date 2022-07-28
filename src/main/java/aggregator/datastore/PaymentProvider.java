package aggregator.datastore;

import aggregator.*;

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
