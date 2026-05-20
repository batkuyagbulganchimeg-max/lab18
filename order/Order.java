package pizzashop.order;

import pizzashop.logger.OrderLogger;
import pizzashop.model.Pizza;
import pizzashop.payment.PaymentStrategy;

public class Order {

    private Pizza pizza;
    private PaymentStrategy payment;

    public Order(Pizza pizza) {
        this.pizza = pizza;
    }

    public void setPaymentMethod(PaymentStrategy payment) {
        this.payment = payment;
    }

    public void checkout() {

        if (payment == null) {
            throw new IllegalStateException(
                    "Төлбөрийн арга тохируулагдаагүй байна");
        }

        double total = pizza.getBasePrice();

        OrderLogger logger = OrderLogger.getInstance();

        logger.log("Order: " + pizza.getName());

        payment.pay(total);

        logger.log("Success");
    }
}