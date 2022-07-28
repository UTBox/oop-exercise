package aggregator;

import java.util.List;

public class PaymentProcessor {
    static PaymentAggregatorRequest paymentAggregatorRequest;

    public void pay(List<PaymentService> paymentsToProcess) {
        paymentsToProcess.forEach(paymentService -> paymentService.executePayment(paymentAggregatorRequest));
    }


}
