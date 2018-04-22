package app.controller;

import app.model.Model;
import app.view.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Controller extends JPanel {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void init() {
        view.createView();
        initListeners();
    }

    /**
     *
     *
     */
    public void initListeners() {
        changePanel(this.view.getMainPanel().getToys(), new JPanel());
    }

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
