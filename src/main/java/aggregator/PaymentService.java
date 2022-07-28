package aggregator;

public interface PaymentService {
    void executePayment(PaymentAggregatorRequest paymentAggregatorRequest);
    void failedPayment(PaymentAggregatorRequest paymentAggregatorRequest);
//    void savePayment(PaymentRecord paymentRecord);
//    public Datastore save(PaymentRecord paymentRecord);
}
