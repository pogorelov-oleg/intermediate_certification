import Models.Customer;
import Models.Toys.PromotionalToy;
import Models.Toys.Toy;
import Store.ToyStore;

public class App {
    public static void main(String[] args) throws Exception {
        ToyStore toyStore = new ToyStore();
        toyStore.putToy(new Toy("Smoby Горка-мега 2 в 1", 10));
        toyStore.putToy(new PromotionalToy("Игра настольная \"Словодел Великан\"", 15, 5, 15));
        toyStore.putToy(new PromotionalToy("LEGO Technic конструктор Monster Jam Monster Mutt Dalmatian", 10, 1, 5));
        toyStore.putToy(new Toy("Wincars YK-2032 Внедорожник на катках 4х4", 6));
        toyStore.putToy(new PromotionalToy("Фишер Прайс Игровой набор \"Бизнесмен\"", 9, 3, 10));
        toyStore.putToy(new PromotionalToy("Фигурка героя-мини", 100, 10, 40));

        toyStore.putCustomer(new Customer("Сергей", "Савельев"));
        toyStore.putCustomer(new Customer("Василий", "Котов"));
        toyStore.putCustomer(new Customer("Сергей", "Афонасьев"));
        toyStore.putCustomer(new Customer("Константин", "Травников"));
        toyStore.putCustomer(new Customer("Ольга", "Бузова"));
        toyStore.putCustomer(new Customer("Ольга", "Громова"));
        toyStore.putCustomer(new Customer("Глеб", "Жиглов"));
        toyStore.putCustomer(new Customer("Ксения", "Никитина"));
        toyStore.putCustomer(new Customer("Михаил", "Бублик"));

        toyStore.runPromo();

        System.out.println();
        System.out.println(toyStore);
    }
}
