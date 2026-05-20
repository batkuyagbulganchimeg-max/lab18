package pizzashop.payment;
public class CashPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println(
                amount + "₮ paid by cash");
        return true;
    }
    @Override
    public String getName() {
        return "Cash";
    }
}