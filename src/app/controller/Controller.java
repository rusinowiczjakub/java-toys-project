package app.controller;

import app.SimpleServiceLocator;
import app.model.*;
import app.service.FileHandlingService;
import app.view.ButtonRenderer;
import app.view.JTableButtonMouseListener;
import app.view.View;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
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
    private JFrame addCategoryFrame;
    private List<Toy> tableData;
    private SimpleServiceLocator serviceLocator;

    /**
     * Instantiates a new Controller.
     *
     * @param model the model
     * @param view  the view
     */
    public Controller(Model model, View view, SimpleServiceLocator serviceLocator) {
        this.model = model;
        this.view = view;
        addToyFrame = new JFrame();
        addCategoryFrame = new JFrame();
        this.serviceLocator = serviceLocator;

        FileHandlingService service =(FileHandlingService) this.serviceLocator.get("file_handler");
        service.importCategoryData(model, service.getCategoryFromJson("src/app/data/categories.json"));
        service.importToyData(model, service.getToyFromJson("src/app/data/toys.json"));

        System.out.println(model.getToys());
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
        view.changePanel(view.getCategoryPanel().getBackBtn(), view.getMainPanel().getMainPane(), view);
        view.changePanel(view.getToyPanel().getBackBtn(), view.getMainPanel().getMainPane(), view);
        view.changePanel(view.getMainPanel().getToys(), view.getToyPanel().getToysPanel(), view);
        view.changePanel(view.getMainPanel().getCategories(), view.getCategoryPanel().getCategoriesPane(), view);

        view.hoverEffect(this.view.getMainPanel().getToys(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getCategories(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getAbout(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getClose(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getFileExport(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getFileImport(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getFileImport(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getToyPanel().getAddNewBtn(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getCategoryPanel().getAddNewCategoryBtn(), new Color(25,181,254, 60));

        filterTable();
        showAddCategoryFrame();
        addToyAction();
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
        JTable table = new JTable(new ToyTableModel(tableData));
        table.setAutoCreateRowSorter(true);
        table.getColumn("").setCellRenderer(new ButtonRenderer());
        table.addMouseListener(new JTableButtonMouseListener(table));
        view.getToyPanel().setTable(table);

        JScrollPane scrollPane = new JScrollPane(view.getToyPanel().getTable());
        view.getToyPanel().setScrollPane(scrollPane);

        view.getToyPanel().getToysPanel().add(
                view.getToyPanel().getScrollPane(), BorderLayout.CENTER
        );
    }

    public void renderCategoriesBox() {
        CategoryBoxModel boxModel = new CategoryBoxModel(model.getCategories());
        view.getCreateToy().getCategoryBox().setModel(boxModel);
        view.getToyPanel().getCategoriesBox().setModel(boxModel);
        view.getCategoryPanel().getCategoriesBox().setModel(boxModel);
    }

    public void filterTable() {
        view.getToyPanel().getSearchButton().addActionListener(e -> {
            JTable table = Controller.this.view.getToyPanel().getTable();
            tableData = Controller.this.model.getToysByCategory((Category) Controller.this.view.getToyPanel().getCategoriesBox().getSelectedItem());

            table.setModel(new ToyTableModel(tableData));
            table.getColumn("").setCellRenderer(new ButtonRenderer());
            table.addMouseListener(new JTableButtonMouseListener(Controller.this.view.getToyPanel().getTable()));

            ((AbstractTableModel) Controller.this.view.getToyPanel().getTable().getModel()).fireTableDataChanged();
        });

    }

    public void showAddCategoryFrame() {
        view.getCategoryPanel().getAddNewCategoryBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                addCategoryFrame.setSize(new Dimension(300, 200));
                addCategoryFrame.add(view.getCreateCategory().getAddCategoryPane());
                addCategoryFrame.setVisible(true);
                view.pack();
            }
        });

        view.getCreateCategory().getAddCategory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category category = new Category(view.getCreateCategory().getCategoryNameField().getText());

                model.addCategory(category);
                System.out.println(model.getCategories());
                addCategoryFrame.dispose();
            }
        });
    }
}
