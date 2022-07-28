package aggregator.paymentDir;

import aggregator.PaymentAggregatorRequest;
import aggregator.datastore.PaymentProvider;

public class PaymentFactory {
    public Payment createPayment(PaymentAggregatorRequest request){
        switch (request.getProvider()){
            case MAYA:
                return new Maya(request.getAmount(), request.getCurrency());
//                break;
//            case GRAB:
//                return new GrabPay();
//                break;
//            case GCASH:
//                break;
        }
//        return null;
        return null;
    }
}
