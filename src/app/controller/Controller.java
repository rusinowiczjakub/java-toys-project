package app.controller;

import app.model.Model;
import app.model.Toy;
import app.model.ToyTableModel;
import app.view.View;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * The type Controller.
 */
public class Controller extends JPanel {

    private Model model;
    private View view;
    private JFrame addToyFrame;

    /**
     * Instantiates a new Controller.
     *
     * @param model the model
     * @param view  the view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        addToyFrame = new JFrame();
    }

    /**
     * Init.
     */
    public void init() {
        view.createView();
        initListeners();
        createTable();
    }

    /**
     * Init listeners.
     *
     * All events listeners should be placed in this method to run on App initializing
     *
     */
    public void initListeners() {
        view.changePanel(view.getMainPanel().getToys(), view.getToyPanel().getToysPanel(), view);
        addToyAction();
        view.hoverEffect(this.view.getMainPanel().getToys(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getCategories(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getAbout(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getClose(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getFileExport(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getFileImport(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getFileImport(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getToyPanel().getAddNewBtn(), new Color(25,181,254, 60));
    }


    public void addToyAction() {
        view.getToyPanel().getAddNewBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                addToyFrame.setSize(new Dimension(340, 400));
                addToyFrame.add(view.getCreateToy().getCreateToyPanel());
                addToyFrame.setVisible(true);
                view.pack();
            }
        });

        view.getCreateToy().getSaveBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toy toy = new Toy(
                        view.getCreateToy().getProducer().getText(),
                        view.getCreateToy().getName().getText(),
                        (double) view.getCreateToy().getWeight().getValue(),
                        (int) view.getCreateToy().getMinAge().getValue(),
                        (double) view.getCreateToy().getPrice().getValue()
                );

                addToyFrame.dispose();
                model.addToy(toy);

                ((AbstractTableModel) view.getToyPanel().getTable().getModel()).fireTableDataChanged();
            }
        });
    }

    public void createTable() {
        view.getToyPanel().setTable(new JTable(
                new ToyTableModel(model)
        ));

        view.getToyPanel().setScrollPane(
                new JScrollPane(view.getToyPanel().getTable())
        );
        view.getToyPanel().getToysPanel().add(
                view.getToyPanel().getScrollPane(), BorderLayout.CENTER
        );
    }
}
