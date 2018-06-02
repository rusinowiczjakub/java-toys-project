package app.view;

import app.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The type View.
 */
public class View extends JFrame {

    private MainPanel mainPanel;
    private ToyPanel toyPanel;
    private CreateToy createToy;
    private CategoryPanel categoryPanel;
    private CreateCategory createCategory;
    private SearchDialog searchDialog;
    private CompareDialog compareDialog;
    private FindByPriceDialog findByPriceDialog;

    public View() {
        mainPanel = new MainPanel();
        toyPanel = new ToyPanel();
        createToy = new CreateToy();
        categoryPanel = new CategoryPanel();
        createCategory = new CreateCategory();
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    /**
     * Method is used to initialize main view of app. It is run in the controller.
     *
     * @return the view
     */
    public View createView() {
        this.setPreferredSize(new Dimension(1000, 600));
        this.setContentPane(mainPanel.getMainPane());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        return this;
    }

    /**
     * The method is used to switch between different JPanels
     *
     * @param element the element
     * @param panel   the panel
     */
    public void changePanel(Object element, JPanel panel, View view) {
        if (element instanceof JButton) {
            ((JButton)element).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.setVisible(true);
                    view.setContentPane(panel);
                    view.pack();
                }
            });
        }

        if (element instanceof JPanel) {
            ((JPanel)element).addMouseListener(new MouseAdapter() {
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

    public void hoverEffect(JPanel element, Color enterColor) {
        Color originalColor = element.getBackground();
        element.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                element.setBackground(enterColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                element.setBackground(originalColor);
            }
        });
    }

    public void showSearchDialog() {
        searchDialog = new SearchDialog();
        searchDialog.getMainPane().add(searchDialog.getColumnName(), BorderLayout.WEST);
        searchDialog.getMainPane().add(searchDialog.getValueField(), BorderLayout.EAST);
        searchDialog.getMainPane().add(searchDialog.getSearchButton(), BorderLayout.SOUTH);

        for (int i = 0; i < getToyPanel().getTable().getModel().getColumnCount(); i++) {
            searchDialog.getColumnName().addItem(getToyPanel().getTable().getModel().getColumnName(i));
        }
        searchDialog.setContentPane(searchDialog.getMainPane());
        searchDialog.setVisible(true);
        searchDialog.pack();
    }

    public void showCompareDialog() {
        compareDialog = new CompareDialog();
        compareDialog.getMainPane().add(compareDialog.getColumnName(), BorderLayout.CENTER);
        compareDialog.getButtonsPanel().add(compareDialog.getMinButton(), BorderLayout.EAST);
        compareDialog.getButtonsPanel().add(compareDialog.getMaxButton(), BorderLayout.WEST);
        compareDialog.getMainPane().add(compareDialog.getButtonsPanel(), BorderLayout.SOUTH);

        for (int i = 0; i < getToyPanel().getTable().getModel().getColumnCount(); i++) {
            compareDialog.getColumnName().addItem(getToyPanel().getTable().getModel().getColumnName(i));
        }
        compareDialog.setContentPane(compareDialog.getMainPane());
        compareDialog.setVisible(true);
        compareDialog.pack();
    }

    public void showFindByPriceDialog() {
        findByPriceDialog = new FindByPriceDialog();
        findByPriceDialog.setVisible(true);
        findByPriceDialog.pack();

    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public ToyPanel getToyPanel() {
        return toyPanel;
    }

    public CreateToy getCreateToy() {
        return createToy;
    }

    public CategoryPanel getCategoryPanel() {
        return categoryPanel;
    }

    public CreateCategory getCreateCategory() {
        return createCategory;
    }

    public SearchDialog getSearchDialog() {
        return searchDialog;
    }

    public CompareDialog getCompareDialog() {
        return compareDialog;
    }

    public FindByPriceDialog getFindByPriceDialog() {
        return findByPriceDialog;
    }
}
