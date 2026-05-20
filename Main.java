package pizzashop;
import pizzashop.decorator.*;
import pizzashop.factory.PizzaFactory;
import pizzashop.model.Pizza;
import pizzashop.order.Order;
import pizzashop.payment.*;
public class Main {
        public static void main(String[] args) {
                PizzaFactory factory = new PizzaFactory();
                Pizza pizza = new Mushroom(
                                new ExtraCheese(
                                                factory.createPizza("margherita")));
                Order order = new Order(pizza);
                order.setPaymentMethod(
                                new CardPayment(
                                                "1234567890123456"));
                order.checkout();
        }
}