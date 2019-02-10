package inno.l17.homework.SimpleFactory;

public class OrderCreator {
    public static void main(String[] args) {
        System.out.println("At your command, Sir!!!");
        try {
            new PizzaStore(new SimplePizzaFactory()).orderPizza("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
