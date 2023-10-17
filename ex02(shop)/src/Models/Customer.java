package Models;

/**
 * Создает экземпляр человека
 */
public class Customer implements Comparable<Customer> {
    private String name;
    private String surName;

    public Customer(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surName;
    }

    /**
     * Сравнивает по имени. В случае, если имена одинаковые, сравнивает по фамилии
     */
    @Override
    public int compareTo(Customer nextName) {
        int result = this.name.compareTo(nextName.name);
        if (result == 0) {
            result = this.surName.compareTo(nextName.surName);
        }
        return result;
    }

}
