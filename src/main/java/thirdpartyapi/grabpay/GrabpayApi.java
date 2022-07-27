package thirdpartyapi.grabpay;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Random;
import java.util.UUID;

public class GrabpayApi {

    /**
     * returns reference id of payment
     * throws exception if payment fails
     * */
    public String pay(Currency currency, BigDecimal amount) throws GrabPaymentFailedException {
        // processing code

        String referenceId = UUID.randomUUID().toString();
        boolean success = new Random().nextBoolean();
        if (!success) {
            throw new GrabPaymentFailedException("NOT_ENOUGH_BALANCE");
        }
        return referenceId;
    }

}
