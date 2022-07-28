package aggregator;

import aggregator.datastore.Datastore;
import aggregator.datastore.PaymentRecord;

public interface PaymentService {
    void executePayment(PaymentAggregatorRequest paymentAggregatorRequest);

//    void savePayment(PaymentRecord paymentRecord);
//    public Datastore save(PaymentRecord paymentRecord);
}
