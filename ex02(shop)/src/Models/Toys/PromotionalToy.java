package Models.Toys;

/**
 * Дочерний класс класса Toy. Создает экземпляр игрушки, участвующей в розыгрыше
 */
public class PromotionalToy extends Toy {
    private int frequency;
    private int promoCount;

    public PromotionalToy(String name, int count, int promoCount, int frequency) {
        super(name, count);
        this.promoCount = promoCount;
        this.frequency = frequency;
        if (frequency < 0 & frequency >= 100) {
            throw new IllegalArgumentException("Значение frequency должно быть больше 0 и меньше или равно 100 ");
        }
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getPromoCount() {
        return promoCount;
    }

    public void setPromoCount(int promoCount) {
        this.promoCount = promoCount;
    }

}
