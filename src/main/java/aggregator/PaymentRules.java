package aggregator;

import java.math.BigDecimal;

public class PaymentRules {

    private PaymentAggregatorRequest paymentAggregatorRequest;

    public PaymentRules(PaymentAggregatorRequest paymentAggregatorRequest){
        this.paymentAggregatorRequest = paymentAggregatorRequest;
    }
    public boolean paymentChecker(BigDecimal amount, String currencyType) {
        if (checkCurrency(currencyType) == true
                && checkPaymentAmount(amount) == false) {
            return true;
// TODO: pass the payment record but mark boolean as fail
        } return false;
    }
    public boolean checkPaymentAmount (BigDecimal amount){
        if (new BigDecimal(String.valueOf(amount)).compareTo(new BigDecimal(1000000)) == 1) {
            return true;
        } else {
            return false;
        }
    }
// TODO: implement to see if currency is PHP
    public boolean checkCurrency(String currencyType){
        if (currencyType == "PHP") {
            return true;
        } else {
            return false;
        }
    }
}

