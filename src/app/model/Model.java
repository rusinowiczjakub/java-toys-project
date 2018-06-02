package app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Model {

    private List<Toy> toys;
    private List<Category> categories;

    public Model() {
        toys = new ArrayList<Toy>();
        categories = new ArrayList<Category>();
        categories.add(new Category("Wszystkie zabawki"));
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
            if(category.toString().equals("Wszystkie zabawki")) {
                return toys;
            }
        }

        return categorizedToys;
    }

    public List<Toy> getToysByValue(String columnName, String stringColumn, double doubleColumn) {
        List<Toy> newToysList = new ArrayList<>();
        columnName = columnName.toLowerCase();

        switch(columnName) {
            case "producent":
                for (int i = 0; i < toys.size(); i++) {
                    if (toys.get(i).getProducer().toLowerCase().equals(stringColumn.toLowerCase())) {
                        newToysList.add(toys.get(i));
                    }
                }

                break;

            case "nazwa":
                for (int i = 0; i < toys.size(); i++) {
                    if (toys.get(i).getName().toLowerCase().equals(stringColumn.toLowerCase())) {
                        newToysList.add(toys.get(i));
                    }
                }

                break;

            case "waga":
                for (int i = 0; i < toys.size(); i++) {
                    if (toys.get(i).getWeight() == doubleColumn) {
                        newToysList.add(toys.get(i));
                    }
                }

                break;

            case "minimalny wiek":
                for (int i = 0; i <toys.size(); i++) {
                    if ((double) toys.get(i).getMinimalAge() == doubleColumn) {
                        newToysList.add(toys.get(i));
                    }
                }

                break;

            case "cena":
                for (int i = 0; i < toys.size(); i++) {
                    if (toys.get(i).getPrice() == doubleColumn) {
                        newToysList.add(toys.get(i));
                    }
                }
        }

        return newToysList;
    }

    public Toy getMinMaxValue(String columnName, boolean isMax) {
        switch(columnName.toLowerCase()) {
            case "kategoria":
                return isMax ?
                        Collections.max(toys, Comparator.comparing(t -> t.getCategory().getName())):
                        Collections.min(toys, Comparator.comparing(t -> t.getCategory().getName()));

            case "producent":
                return isMax ?
                        Collections.max(toys, Comparator.comparing(t -> t.getProducer())) :
                        Collections.min(toys, Comparator.comparing(t -> t.getProducer()));

            case "nazwa":
                return isMax ?
                        Collections.max(toys, Comparator.comparing(t -> t.getName())) :
                        Collections.min(toys, Comparator.comparing(t -> t.getName()));

            case "waga":

                return isMax ?
                        Collections.max(toys, Comparator.comparing(t -> t.getWeight())) :
                        Collections.min(toys, Comparator.comparing(t -> t.getWeight()));

            case "minimalny wiek":
                return isMax ?
                        Collections.max(toys, Comparator.comparing(t -> t.getMinimalAge())) :
                        Collections.min(toys, Comparator.comparing(t -> t.getMinimalAge()));

            case "cena":
                return isMax ?
                        Collections.max(toys, Comparator.comparing(t -> t.getPrice())) :
                        Collections.min(toys, Comparator.comparing(t -> t.getPrice()));
        }

        return null;
    }

    public List<Toy> findToysByPrice(Double price, String operator) {
        List<Toy> newToysList = new ArrayList<>();

        switch (operator) {

            case ">":

                for (int i = 0; i < toys.size(); i++) {
                    if (toys.get(i).getPrice() > price) {
                        newToysList.add(toys.get(i));
                    }
                }

                break;

            case "<":
                for (int i = 0; i < toys.size(); i++) {
                    if (toys.get(i).getPrice() < price) {
                        newToysList.add(toys.get(i));
                    }
                }

                break;
        }

        return newToysList;
    }
}
