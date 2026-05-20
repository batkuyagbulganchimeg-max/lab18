package pizzashop.payment;
public class QpayPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println(
                amount + "₮ paid using QPay");
        return true;
    }
    @Override
    public String getName() {
        return "QPay";
    }
}