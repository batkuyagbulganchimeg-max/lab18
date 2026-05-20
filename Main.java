package pizzashop;

import java.util.Scanner;
import pizzashop.decorator.*;
import pizzashop.factory.PizzaFactory;
import pizzashop.model.Pizza;
import pizzashop.order.Order;
import pizzashop.payment.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PizzaFactory factory = new PizzaFactory();

        // 1. Пицца сонгох
        System.out.println("=== PIZZA SHOP ===");
        System.out.println("Пицца сонгоно уу:");
        System.out.println("  1. Margherita    - 15,000₮  (улаан лооль, моцарелла, базилик)");
        System.out.println("  2. Pepperoni     - 18,000₮  (пеперони, моцарелла, улаан лооль)");
        System.out.println("  3. Veggie        - 16,000₮  (мөөг, оливс, чинжүү)");
        System.out.println("  4. BBQ Chicken   - 22,000₮  (BBQ тахиа, сонгино, моцарелла)");
        System.out.print("Сонголт (1-4): ");

        String pizzaType;
        int pizzaChoice = Integer.parseInt(scanner.nextLine().trim());
        switch (pizzaChoice) {
            case 1: pizzaType = "margherita"; break;
            case 2: pizzaType = "pepperoni"; break;
            case 3: pizzaType = "veggie"; break;
            case 4: pizzaType = "bbq"; break;
            default:
                System.out.println("Буруу сонголт. Margherita сонгогдлоо.");
                pizzaType = "margherita";
        }

        Pizza pizza = factory.createPizza(pizzaType);

        // 2. Topping нэмэх
        System.out.println("\nНэмэлт topping сонгоно уу (хэд ч сонгож болно):");
        System.out.println("  1. Extra Cheese  - +2,000₮");
        System.out.println("  2. Mushroom      - +1,500₮");
        System.out.println("  3. Jalapeno      - +1,200₮");
        System.out.println("  4. Olives        - +1,000₮");
        System.out.println("  0. Topping хэрэггүй");

        boolean addingToppings = true;
        while (addingToppings) {
            System.out.print("Topping сонголт (0-дараагийн алхам руу): ");
            int toppingChoice = Integer.parseInt(scanner.nextLine().trim());
            switch (toppingChoice) {
                case 1: pizza = new ExtraCheese(pizza); System.out.println("  ✓ Extra Cheese нэмэгдлээ"); break;
                case 2: pizza = new Mushroom(pizza);    System.out.println("  ✓ Mushroom нэмэгдлээ"); break;
                case 3: pizza = new Jalapeno(pizza);    System.out.println("  ✓ Jalapeno нэмэгдлээ"); break;
                case 4: pizza = new Olives(pizza);      System.out.println("  ✓ Olives нэмэгдлээ"); break;
                case 0: addingToppings = false; break;
                default: System.out.println("  Буруу сонголт."); break;
            }
        }

        // 3. Захиалгын мэдээлэл харуулах
        System.out.println("\n--- Таны захиалга ---");
        System.out.println("Пицца : " + pizza.getName());
        System.out.println("Найрлага: " + pizza.getIngredients());
        System.out.printf("Нийт үнэ: %.0f₮%n", pizza.getBasePrice());

        // 4. Төлбөрийн арга сонгох
        System.out.println("\nТөлбөрийн арга сонгоно уу:");
        System.out.println("  1. Cash (Бэлэн мөнгө)");
        System.out.println("  2. Card (Карт)");
        System.out.println("  3. QPay");
        System.out.print("Сонголт (1-3): ");

        PaymentStrategy payment;
        int payChoice = Integer.parseInt(scanner.nextLine().trim());
        switch (payChoice) {
            case 2:
                System.out.print("Картын дугаар оруулна уу (16 оронтой): ");
                String cardNumber = scanner.nextLine().trim();
                payment = new CardPayment(cardNumber);
                break;
            case 3:
                payment = new QpayPayment();
                break;
            default:
                payment = new CashPayment();
                break;
        }

        // 5. Checkout
        System.out.println();
        Order order = new Order(pizza);
        order.setPaymentMethod(payment);
        order.checkout();

        System.out.println("Баярлалаа! Таны захиалга хүлээн авлаа ");
        scanner.close();
    }
}