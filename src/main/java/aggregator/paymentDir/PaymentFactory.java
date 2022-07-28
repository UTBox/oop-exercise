package aggregator.paymentDir;

import aggregator.PaymentAggregatorRequest;
import aggregator.datastore.PaymentProvider;

public class PaymentFactory {
    public Payment createPayment(PaymentAggregatorRequest request) {
        switch (request.getProvider()) {
            case MAYA:
                return new Maya(request.getAmount(), request.getCurrency());

//            case GRAB:
//                return new GrabPay();
//                break;
            case GCASH:
                return new Gcash(request.getAmount(), request.getCurrency());
        }
//        return null;
        return null;
    }
}
