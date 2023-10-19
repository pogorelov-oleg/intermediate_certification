package Models;

/**
 * Создает экземпляр покупателя
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
    public int compareTo(Customer nextCustomer) {
        int result = this.name.compareTo(nextCustomer.name);
        if (result == 0) {
            result = this.surName.compareTo(nextCustomer.surName);
        }
        return result;
    }

}
