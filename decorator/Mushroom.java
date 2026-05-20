package pizzashop.decorator;
import pizzashop.model.Pizza;
public class Mushroom extends PizzaDecorator {
    public Mushroom(Pizza pizza) {
        super(pizza);
    }
    @Override
    public String getName() {
        return pizza.getName() + " + Mushroom";
    }
    @Override
    public double getBasePrice() {
        return pizza.getBasePrice() + 1500;
    }
    @Override
    public String getIngredients() {
        return pizza.getIngredients() + ", mushroom";
    }
}