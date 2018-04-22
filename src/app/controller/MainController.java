package app.controller;

import app.model.Model;
import app.view.View;

import javax.swing.*;

public class MainController extends Controller {

    private Model model;
    private View view;

    public MainController(Model model, View mainPanel) {
        this.model = model;
        this.view = mainPanel;
    }

    public void init() {

    }

    @Override
    public void initComponents() {

    }

    @Override
    public void initListeners() {

    }
}
