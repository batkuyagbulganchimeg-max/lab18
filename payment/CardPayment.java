package pizzashop.payment;
public class CardPayment implements PaymentStrategy {
    private String cardNumber;
    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    @Override
    public boolean pay(double amount) {
        String masked =
                "****" +
                cardNumber.substring(cardNumber.length() - 4);
        System.out.println(
                amount + "₮ paid using card " + masked);
        return true;
    }
    @Override
    public String getName() {
        return "Card";
    }
}