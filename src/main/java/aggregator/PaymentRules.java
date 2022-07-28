package aggregator;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public class PaymentRules {

//    private PaymentAggregatorRequest paymentAggregatorRequest;
//
//    public PaymentRules(PaymentAggregatorRequest paymentAggregatorRequest){
//        this.paymentAggregatorRequest = paymentAggregatorRequest;
//    }
    public void paymentChecker(List<PaymentService> paymentServiceList) {

        //TODO: take only PaymentService rather than payment aggretator request, and pass the list
        for (PaymentService paymentService: paymentServiceList) {
            //TODO: pass the conditions here
//            if (checkCurrency())
        }
        if (checkCurrency(paymentAggregatorRequest.getCurrency())
                && !(checkPaymentAmount(paymentAggregatorRequest.getAmount()))) {
            paymentService.executePayment(paymentAggregatorRequest.getAmount(), paymentAggregatorRequest.getCurrency());
// TODO: pass the payment record but mark boolean as fail
        } else {
            paymentService.failedPayment(paymentAggregatorRequest.getAmount(), paymentAggregatorRequest.getCurrency());
        }
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


