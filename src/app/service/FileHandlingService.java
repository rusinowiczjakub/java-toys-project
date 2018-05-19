package app.service;

import app.Service;
import app.model.Category;
import app.model.Model;
import app.model.Toy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class FileHandlingService extends Service {

    private static final Type TOY_TYPE = new TypeToken<List<Toy>>() {
    }.getType();

    private static final Type CATEGORY_TYPE = new TypeToken<List<Category>>(){
    }.getType();

    public FileHandlingService() {

    }

    public FileReader getFile (String filename) {
        try {
            return new FileReader(filename);
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Category> getCategoryFromJson(String jsonFile) {

        Gson gson = new Gson();
        JsonReader reader = null;
        reader = new JsonReader(getFile(jsonFile));
        List<Category> data = gson.fromJson(reader, CATEGORY_TYPE); // contains the whole reviews list

        return data;
    }

    public List<Toy> getToyFromJson(String jsonFile) {
        Gson gson = new Gson();
        JsonReader reader = null;
        reader = new JsonReader(getFile(jsonFile));
        List<Toy> data = gson.fromJson(reader, TOY_TYPE); // contains the whole reviews list

        return data;
    }

    public void importCategoryData(Model model, List data) {
        for (int i = 0; i < data.size(); i++) {
            model.addCategory((Category) data.get(i));
        }
    }

    public void importToyData(Model model, List data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
            model.addToy((Toy) data.get(i));
            Toy toy = ((Toy) data.get(i));
        }

//        for (int j = 0; j < model.getCategories().size(); j++) {
//            System.out.println("=============");
//            System.out.println(model.getCategories().get(j).toString());
//            System.out.println(toy.getCategory().toString());
//            if (!model.getCategories().get(j).toString().equals(toy.getCategory().toString())) {
//                model.addCategory(new Category(toy.getCategory().toString()));
//            }
//        }
    }


}
