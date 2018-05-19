package app;

import app.controller.Controller;
import app.model.Model;
import app.view.View;

import java.awt.*;

/**
 * The type App.
 */
public class App {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            //@Override
            public void run() {
                new App().init();
            }
        });
    }

    /**
     * Init.
     */
    public void init() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view, new SimpleServiceLocator());
        controller.init();

    }
}
