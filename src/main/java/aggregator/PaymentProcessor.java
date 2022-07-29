package aggregator;

import aggregator.paymentmethods.Payment;

import java.util.List;

public class PaymentProcessor {
    PaymentAggregatorRequest paymentAggregatorRequest;
    public void pay(List<Payment> paymentsToProcess) {
        paymentsToProcess.forEach(paymentService -> paymentService.executePayment());
    }
}
