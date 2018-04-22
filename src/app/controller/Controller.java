package app.controller;

import app.model.Model;
import app.view.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * The type Controller.
 */
public class Controller extends JPanel {

    private Model model;
    private View view;

    /**
     * Instantiates a new Controller.
     *
     * @param model the model
     * @param view  the view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Init.
     */
    public void init() {
        view.createView();
        initListeners();
    }


    /**
     * Init listeners.
     *
     * All events listeners should be placed in this method to run on App initializing
     *
     */
    public void initListeners() {
        changePanel(this.view.getMainPanel().getToys(), new JPanel());
    }

    /**
     * The method is used to switch between different JPanels
     *
     * @param element the element
     * @param panel   the panel
     */
    public void changePanel(JPanel element, JPanel panel) {
        element.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                panel.setVisible(true);
                view.setContentPane(panel);
                view.pack();
            }
        });
    }

}
