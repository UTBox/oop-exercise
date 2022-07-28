package aggregator.datastore;

import aggregator.payment.Maya;
import aggregator.payment.Payment;
import aggregator.PaymentAggregatorRequest;

public enum PaymentProvider {
    GCASH {
        @Override
        public Payment convertRequestToPayment(PaymentAggregatorRequest request) {
            return null;
        }
    }, MAYA {
        @Override
        public Payment convertRequestToPayment(PaymentAggregatorRequest request) {
            return new Maya(request.getAmount(), request.getCurrency());
        }
    }, GRAB {
        @Override
        public Payment convertRequestToPayment(PaymentAggregatorRequest request) {
            return null;
        }
    };

    public abstract Payment convertRequestToPayment(PaymentAggregatorRequest request);
}
