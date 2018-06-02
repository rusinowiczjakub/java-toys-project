package app.model;

import java.text.DecimalFormat;

/**
 * The type Toy.
 */
public class Toy extends Model {
    private String producer;
    private String name;
    private double weight;
    private int minimalAge;
    private double price;
    private Category category;

    /**
     * Instantiates a new Toy.
     *
     * @param producer   the producer
     * @param name       the name
     * @param weight     the weight
     * @param minimalAge the minimal age
     * @param price      the price
     */
    public Toy(String producer, String name, double weight, int minimalAge, double price, Category category) {
        this.producer = producer;
        this.name = name;
        this.weight = weight;
        this.minimalAge = minimalAge;
        this.price = Math.round(price * 100.0) / 100.0;
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = Math.round(price * 100.0) / 100.0;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Producent: " +producer + "\n"+
                "Nazwa: " + name + "\n"+
                "Waga: " + weight + "\n"+
                "Minimalny wiek:" + minimalAge + "\n" +
                "Cena: " + price + " \n" +
                "Kategoria: " + category;
    }
}



