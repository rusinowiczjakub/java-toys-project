package app.service;

import app.Service;
import app.model.Category;
import app.model.Model;
import app.model.Toy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileHandlingService extends Service {

    private static final Type TOY_TYPE = new TypeToken<List<Toy>>() {
    }.getType();

    public FileHandlingService() {

    }

    public FileReader getFile (String filename) throws Exception {
        String ext = filename.substring(filename.lastIndexOf('.') + 1);
        if (!ext.equals("json")) {
            throw new Exception("Plik powinien posiadać rozszerzenie .json");
        }

        try {
            return new FileReader(filename);
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Toy> getToyFromJson(String jsonFile) {

        List<Toy> data = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonReader reader = null;
            reader = new JsonReader(getFile(jsonFile));
            data = gson.fromJson(reader, TOY_TYPE);
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public void importToyData(Model model, List data) {
        for (int i = 0; i < data.size(); i++) {
            model.addToy((Toy) data.get(i));
            Toy toy = ((Toy) data.get(i));

            for (int j = 0; j < model.getCategories().size(); j++) {
                if (!containsCategory(model.getCategories(), toy.getCategory().toString())) {
                    model.addCategory(new Category(toy.getCategory().toString()));
                }
            }
        }
    }

    public void exportToyData(Model model, String filepath) {
        Gson gson = new Gson();

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = new PrintWriter(fileWriter);
        writer.print(gson.toJson(model.getToys()));
        writer.close();
    }

    public boolean containsCategory(final List<Category> list, final String name){
        return list.stream().filter(o -> o.toString().equals(name)).findFirst().isPresent();
    }


}
