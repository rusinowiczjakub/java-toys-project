package app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The type View.
 */
public class View extends JFrame {

    private MainPanel mainPanel;
    private ToyPanel toyPanel;
    private CreateToy createToy;

    public View() {
        mainPanel = new MainPanel();
        toyPanel = new ToyPanel();
        createToy = new CreateToy();
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
    public void changePanel(JPanel element, JPanel panel, View view) {
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
}
