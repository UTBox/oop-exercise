package aggregator;

import java.util.List;

public class PaymentProcessor {
    PaymentAggregatorRequest paymentAggregatorRequest;
    public void pay(List<PaymentService> paymentsToProcess) {
        paymentsToProcess.forEach(paymentService -> paymentService.executePayment(paymentAggregatorRequest.getAmount(), paymentAggregatorRequest.getCurrency()));
    }
}
