package thirdpartyapi.gcash;

public class GCashPaymentResponse {

    private boolean paymentSuccessful;
    private long paymentId;

    public GCashPaymentResponse(boolean paymentSuccessful, long paymentId) {
        this.paymentSuccessful = paymentSuccessful;
        this.paymentId = paymentId;
    }

    public boolean isPaymentSuccessful() {
        return paymentSuccessful;
    }

    public long getPaymentId() {
        return paymentId;
    }
}
