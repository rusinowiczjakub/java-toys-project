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
}
