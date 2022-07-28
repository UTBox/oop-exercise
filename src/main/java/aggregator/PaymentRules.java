package aggregator;

import java.math.BigDecimal;

public class PaymentRules {

    private PaymentAggregatorRequest paymentAggregatorRequest;

    public PaymentRules(PaymentAggregatorRequest paymentAggregatorRequest){
        this.paymentAggregatorRequest = paymentAggregatorRequest;
    }
    public void paymentChecker(Long amount, String currencyType) {
        if (checkCurrency(currencyType) == true
                && checkPaymentAmount(amount) == false) {
            new MayaProcessor(paymentAggregatorRequest);
        } else {
// TODO: pass the payment record but mark boolean as fail
        }

    }
    public boolean checkPaymentAmount (Long amount){
        if (new BigDecimal(amount).compareTo(new BigDecimal(1000000)) == 1) {
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

