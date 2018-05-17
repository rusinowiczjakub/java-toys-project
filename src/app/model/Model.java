package app.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<Toy> toys;
    private List<Category> categories;

    public Model() {
        toys = new ArrayList<Toy>();
        categories = new ArrayList<Category>();
        categories.add(new Category(""));
        categories.add(new Category("Samochody"));
        categories.add(new Category("Lalki"));
        categories.add(new Category("Puzzle"));
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public List<Toy> getToys() {
        return toys;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Toy> getToysByCategory(Category category) {
        List<Toy> categorizedToys = new ArrayList<>();
        for (int i = 0; i < toys.size(); i++) {
            if(toys.get(i).getCategory() == category) {
                categorizedToys.add(toys.get(i));
            }
            if(category.toString() == "") {
                return toys;
            }
        }

        return categorizedToys;
    }
}
