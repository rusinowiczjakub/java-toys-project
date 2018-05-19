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
            if(toys.get(i).getCategory().toString().equals(category.toString())) {
                categorizedToys.add(toys.get(i));
            }
            if(category.toString().equals("")) {
                return toys;
            }
        }

        return categorizedToys;
    }
}
