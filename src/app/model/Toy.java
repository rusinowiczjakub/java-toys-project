package app.model;

public class Toy extends Model {
    private String producer;
    private String name;
    private double weight;
    private int minimalAge;
    private double price;

    public Toy(String producer, String name, double weight, int minimalAge, double price) {
        this.producer = producer;
        this.name = name;
        this.weight = weight;
        this.minimalAge = minimalAge;
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("************************************");
        sb.append("\nproducer: ").append(producer);
        sb.append("\nname: ").append(name);
        sb.append("\nweight: ").append(weight);
        sb.append("\nminimalAge: ").append(minimalAge);
        sb.append("\nprice: ").append(price);
        sb.append("\n************************************");

        return sb.toString();
    }
}

