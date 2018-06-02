package app.controller;

import app.Service;
import app.SimpleServiceLocator;
import app.model.*;
import app.service.FileHandlingService;
import app.view.ButtonRenderer;
import app.view.JTableButtonMouseListener;
import app.view.View;
import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;


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
        service.importToyData(model, service.getToyFromJson("src/app/data/toys.json"));
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
        view.hoverEffect(this.view.getMainPanel().getFileExport(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getFileImport(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getMainPanel().getFileImport(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getToyPanel().getAddNewBtn(), new Color(25,181,254, 60));
        view.hoverEffect(this.view.getCategoryPanel().getAddNewCategoryBtn(), new Color(25,181,254, 60));

        filterTable();
        showAddCategoryFrame();
        addToyAction();
        importFile();
        exportToFile();
        findBy();
        findMinMaxValue();
    }


    /* EVENTS LISTENERS */

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
                try {
                    if (
                        !((double)view.getCreateToy().getWeight().getValue() > 0) ||
                        !((int)view.getCreateToy().getMinAge().getValue() > 0) ||
                        !((double)view.getCreateToy().getPrice().getValue() >= 0)
                    ) {
                        throw new Exception("Wszystkie wartości muszą być dodatnie");
                    }
                    if (
                        (view.getCreateToy().getProducer().getText().isEmpty()) ||
                        (view.getCreateToy().getName().getText().isEmpty())
                    ) {
                        throw new Exception("Pola Producent i Nazwa Produktu sa wymagane");
                    }

                    if (
                        (view.getCreateToy().getCategoryBox().getSelectedItem()).toString().equals("")
                    ) {
                        throw new NullPointerException("Musisz wybrać kategorię");
                    }

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
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
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
                addCategoryFrame.dispose();
            }
        });
    }

    public void importFile() {

        view.getMainPanel().getFileImport().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Service service = ((FileHandlingService) Controller.this.serviceLocator.get("file_handler"));
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter(".json", "json"));

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().toString();
                    renderProgressBar(new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            ((FileHandlingService) service).importToyData(Controller.this.model, ((FileHandlingService) service).getToyFromJson(path));

                            return null;
                        }
                    });
                } else {
                    System.out.println("NO FILE CHOOSEN");
                }
            }
        });
    }

    public void exportToFile() {
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                FileHandlingService service = (FileHandlingService) Controller.this.serviceLocator.get("file_handler");
                service.exportToyData(model, "src/app/data/toys.json");
            }
        };

        view.addWindowListener(exitListener);


        view.getMainPanel().getFileExport().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame parentFrame = new JFrame();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelection = fileChooser.showSaveDialog(parentFrame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String fileToSave = fileChooser.getSelectedFile().toString();
                    renderProgressBar(new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            FileHandlingService service = (FileHandlingService) Controller.this.serviceLocator.get("file_handler");

                            service.exportToyData(Controller.this.model, fileToSave);
                            return null;
                        }
                    });
                }
            }
        });
    }

    public void renderProgressBar(Callable<Void> func) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JProgressBar progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(250, 50));
        frame.add(progressBar);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);

        int timerDelay = 10;
        new javax.swing.Timer(timerDelay , new ActionListener() {
            private int index = 0;
            private int maxIndex = 100;
            public void actionPerformed(ActionEvent e) {
                if (index < maxIndex) {
                    progressBar.setValue(index);
                    index++;
                } else {
                    progressBar.setValue(maxIndex);
                    try {
                        func.call();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    ((javax.swing.Timer)e.getSource()).stop();
                    frame.dispose();
                }
            }
        }).start();

        progressBar.setValue(progressBar.getMinimum());

        frame.setVisible(true);
        frame.pack();
    }

    public void findBy() {
        view.getToyPanel().getFindByButton().addActionListener(e -> {
            view.showSearchDialog();

            view.getSearchDialog().getSearchButton().addActionListener(e1 -> {
                Double doubleVal;
                if (isNumeric(Controller.this.view.getSearchDialog().getValueField().getText())) {
                    doubleVal = Double.parseDouble(Controller.this.view.getSearchDialog().getValueField().getText());
                } else {
                    doubleVal = 0.0;
                }

                List newList = Controller.this.model.getToysByValue(
                        Controller.this.view.getSearchDialog().getColumnName().getSelectedItem().toString(),
                        Controller.this.view.getSearchDialog().getValueField().getText(),
                        doubleVal
                );
                Controller.this.view.getToyPanel().getTable().setModel(new ToyTableModel(newList));
                ((AbstractTableModel) Controller.this.view.getToyPanel().getTable().getModel()).fireTableDataChanged();
            });
        });
    }

    public void findMinMaxValue() {
        view.getToyPanel().getCompareButton().addActionListener(e -> {
            view.showCompareDialog();
            view.getCompareDialog().getMaxButton().addActionListener(e1 -> {
                System.out.println(
                    Controller.this.model.getMinMaxValue(view.getCompareDialog().getColumnName().getSelectedItem().toString(), true)
                );
            });

            view.getCompareDialog().getMinButton().addActionListener(e2 -> {
                System.out.println(
                        Controller.this.model.getMinMaxValue(view.getCompareDialog().getColumnName().getSelectedItem().toString(), false)
                );
            });
        });
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
