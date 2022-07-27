package thirdpartyapi.grabpay;

public class GrabPaymentFailedException extends Exception {
    public GrabPaymentFailedException(String message) {
        super(message);
    }

    private String getReason() {
        return getMessage();
    }
}
