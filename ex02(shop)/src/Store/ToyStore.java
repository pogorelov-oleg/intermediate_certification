package Store;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

import Data.FileManager;
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
     * Метод запускает розыгрыш призов. Из очереди customersQueue берется
     * покупатель, разыгрывается подарок и покупатель уходит из очереди. Победители
     * логируются в файл.
     */
    public void runPromo() {
        Date date = new Date();
        FileManager fm = new FileManager();
        fm.addToFile("Winners.txt", date.toString() + "\n");
        Promo promo = new Promo(this); // Создаю экземпляр промо акции "Розыгрыш игрушек"
        while (this.customersQueue.size() > 0) {
            PromotionalToy prize = promo.getPrizeToy();
            if (prize != null) {
                String message = this.customersQueue.poll().toString() + " - " + prize.getId() + " " + prize.getName();
                System.out.println(message);
                fm.addToFile("Winners.txt", message);
            } else {
                System.out.println(this.customersQueue.poll().toString() + " - \u001B[31mНичего не выиграл\u001B[0m");
            }
        }
        fm.addToFile("Winners.txt", "___________________________________________________________________");
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
