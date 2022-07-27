package aggregator.datastore;

import java.util.Currency;

public class PaymentRecord {
    private PaymentProvider paymentProvider;
    private String paymentId;
    private String amount;
    private PaymentStatus status;


    public PaymentRecord(PaymentProvider paymentProvider, String paymentId, String amount, PaymentStatus status) {
        this.paymentProvider = paymentProvider;
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentRecord{" +
                "paymentProvider=" + paymentProvider +
                ", paymentId='" + paymentId + '\'' +
                ", amount='" + amount + '\'' +
                ", status=" + status +
                '}';
    }
}
