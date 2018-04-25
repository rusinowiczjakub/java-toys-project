package app.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<Toy> toys;


    public Model() {
        toys = new ArrayList<Toy>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public List<Toy> getToys() {
        return toys;
    }
}
