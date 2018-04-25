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
