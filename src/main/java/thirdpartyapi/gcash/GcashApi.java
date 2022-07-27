package thirdpartyapi.gcash;

import java.util.Random;

public class GcashApi {

    /**
     * Accepts a request object containing request details like currency and amount
     * Returns a response class that contains details like if the payment is successful or not and a reference id
     * */
    public GCashPaymentResponse submitPayment(GCashPaymentRequest request) {
        // processing code
        long referenceId = new Random().nextLong();
        System.out.printf("processing GCash payment of %s %n with reference id: %n \n",
                request.getCurrency().toString(), request.getAmount(), referenceId);
        boolean success = new Random().nextBoolean();
        return new GCashPaymentResponse(success, referenceId);
    }

}
