package thirdpartyapi.maya;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Random;
import java.util.UUID;

public class MayaApi {

    /**
     * returns reference id, to figure out if successful, use checkPayment() method
     */
    public String payment(Currency currency, BigDecimal amount) {
        String referenceId = UUID.randomUUID().toString();

        return referenceId;
    }

    public boolean checkPayment(String referenceId) {
        boolean successful = new Random().nextBoolean();
        return successful;
    }

}
