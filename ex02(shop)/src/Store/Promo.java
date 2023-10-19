package Store;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Random;

import Data.FileManager;
import Models.Customer;
import Models.Toys.PromotionalToy;
import Models.Toys.Toy;

/**
 * Создает экземпляр промо акции "Розыгрыш игрушек"
 */
public class Promo {
    private ArrayList<PromotionalToy> promotionalToys;
    private PriorityQueue<Customer> customersQueue;

    public Promo(ToyStore toystore) {
        this.promotionalToys = new ArrayList<>();
        this.customersQueue = toystore.getCustomersQueue();

        for (Toy toy : toystore.getToys()) {
            if (toy instanceof PromotionalToy) {
                this.promotionalToys.add((PromotionalToy) toy);
            }
        }
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
        while (this.customersQueue.size() > 0) {
            PromotionalToy prize = getPrizeToy();
            if (prize != null) {
                String message = this.customersQueue.poll().toString() + " выиграл Приз №" + prize.getId() + " - " + prize.getName();
                System.out.println(message);
                fm.addToFile("Winners.txt", message);
            } else {
                System.out.println(this.customersQueue.poll().toString() + "\u001B[31m Ничего не выиграл\u001B[0m");
            }
        }
        fm.addToFile("Winners.txt", "___________________________________________________________________");
    }

    /**
     * Метод получает рандомный экземпляр PromotionalToy из массива призов. Массив
     * призов создается используя экземпляры коллекции 
     * this.promotionalToys с помощью метода createPrizeTable()
     * 
     * @return Экземпляр PromotionalToy (приз)
     */
    public PromotionalToy getPrizeToy() {
        Random random = new Random();
        int prizeIndex = random.nextInt(100);
        PromotionalToy prize = createPrizeTable()[prizeIndex];
        if (prize != null) {
            prize.setCount(prize.getCount() - 1);
            prize.setPromoCount(prize.getPromoCount() - 1);
            if (!checkPromoCount(prize)) {
                promotionalToys.remove(prize);
            }
        }
        return prize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Toy toy : promotionalToys) {
            sb.append(toy.toString() + "\n");
        }

        return sb.toString();
    }

    /**
     * Метод создает массив призов PromotionalToy[] prizes из коллекции
     * this.promotionalToys. Размер массива равен 100 и заполняется в соответствии
     * со значениями frequency всех экземпляров класса PromotionalToy. Их сумма не
     * должна привышать 100, в противном случае программа выдаст Exeption
     * 
     * @return массив призов (экземпляров PromotionalToy)
     */
    private PromotionalToy[] createPrizeTable() {
        int size = 100;
        if (!checkFrequencySum()) {
            throw new ArrayIndexOutOfBoundsException(
                    "Сумма frequency всех экземпляров игрушек в розыгрыше привышает 100");
        }
        PromotionalToy[] prizes = new PromotionalToy[size];
        int index = 0;
        int frequency;
        for (PromotionalToy toy : promotionalToys) {
            frequency = toy.getFrequency();
            while (frequency > 0) {
                if (prizes[index] == null) {
                    prizes[index] = toy;
                    frequency--;
                    index++;
                }
            }
        }
        return prizes;
    }

    /**
     * Метод проверяет, не привышает ли 100 сумма frequency всех экземпляров игрушек
     * в розыгрыше
     * 
     * @return булево
     */
    private boolean checkFrequencySum() {
        int sum = 0;
        for (PromotionalToy toy : promotionalToys) {
            sum += toy.getFrequency();
        }
        return sum <= 100;
    }

    /**
     * Метод проверяет остались ли еще призовые игрушки в экземпляре PromotionalToy
     * 
     * @return булево
     */
    private boolean checkPromoCount(PromotionalToy toy) {
        return toy.getPromoCount() != 0;
    }

}