package Store;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Models.Customer;
import Models.Toys.PromotionalToy;
import Models.Toys.Toy;

/**
 * Создает экземпляр магазина
 */
public class ToyStore {
    private ArrayList<Toy> toys;
    private PriorityQueue<Customer> customersQueue;

    public ToyStore() {
        this.toys = new ArrayList<>();
        this.customersQueue = new PriorityQueue<>();
    }

    /**
     * Метод принимает экземпляр класса Toy и добавляет его в коллекцию this.toys
     * 
     * @param toy экземпляр класса Toy
     */
    public void putToy(Toy toy) {
        this.toys.add(toy);
    }

    /**
     * Метод принимает экземпляр класса Customer и добавляет его в коллекцию
     * this.customers
     * 
     * @param customer экземпляр класса Customer
     */
    public void putCustomer(Customer customer) {
        this.customersQueue.add(customer);
    }

    public ArrayList<Toy> getToys() {
        return this.toys;
    }

    public PriorityQueue<Customer> getCustomersQueue() {
        return customersQueue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[32mНаличие игрушек в магазине:\u001B[0m\n");
        for (Toy toy : toys) {
            if (toy instanceof PromotionalToy) {
                PromotionalToy promotionalToy = (PromotionalToy) toy; // создал копию экземпляра toy, чтобы сделать каст
                                                                      // с Тоу до PromotionalToy и иметь возможность,
                                                                      // обращаться к его методам.
                if (promotionalToy.getPromoCount() == 0) {
                    sb.append(toy.toString() + "\u001B[31m РАЗЫГРАНЫ\u001B[0m\n");
                } else {
                    sb.append(toy.toString() + "\u001B[36m Участвует в розыгрыше(остаток "
                            + promotionalToy.getPromoCount()
                            + ")\u001B[0m\n");
                }
            } else {
                sb.append(toy.toString() + "\n");
            }
        }
        return sb.toString();
    }

}
