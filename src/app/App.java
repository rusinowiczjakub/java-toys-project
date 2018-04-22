package app;

import app.controller.Controller;
import app.model.Model;
import app.view.View;

import java.awt.*;

public class App {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            //@Override
            public void run() {
                new App().init();
            }
        });
    }

    public void init() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.init();
    }
}
