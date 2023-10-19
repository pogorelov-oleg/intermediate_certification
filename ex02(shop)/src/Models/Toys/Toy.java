package Models.Toys;

/**
 * Родительский класс, который создает экземпляр игрушки
 */
public class Toy implements Comparable<Toy> {
    private int id;
    private static int generalId; // Общий статический ID
    private String name;
    private int count;

    public Toy(String name, int count) {
        generalId++;
        this.id = generalId;
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.name + " (остаток в магазине: " + this.count + " шт)";
    }

    /**
     * Сравнивает по ID
     */
    @Override
    public int compareTo(Toy nextToy) {
        return this.id - nextToy.id;
    }

}
