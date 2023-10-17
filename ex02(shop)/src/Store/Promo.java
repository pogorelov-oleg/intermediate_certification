package Store;

import java.util.ArrayList;
import java.util.Random;

import Models.Toys.PromotionalToy;
import Models.Toys.Toy;

/**
 * Создает экземпляр промо акции "Розыгрыш игрушек"
 */
public class Promo {
    private ArrayList<PromotionalToy> promotionalToys;

    public Promo(ToyStore toystore) {
        this.promotionalToys = new ArrayList<>();
        for (Toy toy : toystore.getToys()) {
            if (toy instanceof PromotionalToy) {
                this.promotionalToys.add((PromotionalToy) toy);
            }
        }
    }

    /**
     * Метод получает рандомный экземпляр PromotionalToy из массива призов. Массив
     * призов создается из коллекции
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