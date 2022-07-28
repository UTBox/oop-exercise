package aggregator;

import java.math.BigDecimal;
import java.util.Currency;

public class PaymentRules {

//    private PaymentAggregatorRequest paymentAggregatorRequest;
//
//    public PaymentRules(PaymentAggregatorRequest paymentAggregatorRequest){
//        this.paymentAggregatorRequest = paymentAggregatorRequest;
//    }
    public boolean paymentChecker(PaymentAggregatorRequest paymentAggregatorRequest, PaymentService paymentService) {
        if (checkCurrency(paymentAggregatorRequest.getCurrency())
                && !(checkPaymentAmount(paymentAggregatorRequest.getAmount()))) {
            paymentService.executePayment(paymentAggregatorRequest);
// TODO: pass the payment record but mark boolean as fail
        } return false;
    }
    public boolean checkPaymentAmount (BigDecimal amount){
        if (amount.compareTo(new BigDecimal(1000000)) == 1) {
            return true;
        } else {
            return false;
        }
    }
// TODO: implement to see if currency is PHP
    public boolean checkCurrency(Currency currencyType){
        if (currencyType.toString() == "PHP") {
            return true;
        } else {
            return false;
        }
    }
}

