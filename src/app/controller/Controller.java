package app.controller;

import app.model.Category;
import app.model.Model;
import app.model.Toy;
import app.model.ToyTableModel;
import app.view.ButtonRenderer;
import app.view.JTableButtonMouseListener;
import app.view.View;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


/**
 * The type Controller.
 */
public class Controller extends JPanel {

    private Model model;
    private View view;
    private JFrame addToyFrame;
    private List<Toy> tableData;

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
        renderCategoriesBox();
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
                        (double) view.getCreateToy().getPrice().getValue(),
                        (Category) view.getCreateToy().getCategoryBox().getSelectedItem()
                );

                addToyFrame.dispose();
                model.addToy(toy);

                ((AbstractTableModel) view.getToyPanel().getTable().getModel()).fireTableDataChanged();
            }
        });
    }

    public void createTable() {
        tableData = model.getToys();
        view.getToyPanel().getSearchButton().addActionListener(e -> {
            JTable table = Controller.this.view.getToyPanel().getTable();
            tableData = Controller.this.model.getToysByCategory((Category) Controller.this.view.getToyPanel().getCategoriesBox().getSelectedItem());
            table.setModel(new ToyTableModel(tableData));
            table.getColumn("").setCellRenderer(new ButtonRenderer());
            table.addMouseListener(new JTableButtonMouseListener(Controller.this.view.getToyPanel().getTable()));
            ((AbstractTableModel) Controller.this.view.getToyPanel().getTable().getModel()).fireTableDataChanged();
        });

        JTable table = new JTable(new ToyTableModel(tableData));
        table.setAutoCreateRowSorter(true);
        table.getColumn("").setCellRenderer(new ButtonRenderer());
        table.addMouseListener(new JTableButtonMouseListener(table));

        view.getToyPanel().setTable(table);
        view.getToyPanel().setScrollPane(
                new JScrollPane(view.getToyPanel().getTable())
        );
        view.getToyPanel().getToysPanel().add(
                view.getToyPanel().getScrollPane(), BorderLayout.CENTER
        );
    }

    public void renderCategoriesBox() {
        for(int i = 0; i < model.getCategories().size(); i++) {
            if (!(i == 0)) {
                view.getCreateToy().getCategoryBox().addItem(model.getCategories().get(i));
            }
            view.getToyPanel().getCategoriesBox().addItem(model.getCategories().get(i));
        }
    }


}
