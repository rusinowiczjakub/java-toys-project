package app.view;

import javax.swing.*;
import java.awt.*;

public class CompareDialog extends JDialog {

    JPanel mainPane = new JPanel();
    JComboBox<String> columnName = new JComboBox<>();
    JPanel buttonsPanel = new JPanel();
    JButton minButton = new JButton("minimalna");
    JButton maxButton = new JButton("maksymalna");

    public CompareDialog() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public JPanel getMainPane() {
        return mainPane;
    }

    public JComboBox<String> getColumnName() {
        return columnName;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public JButton getMinButton() {
        return minButton;
    }

    public JButton getMaxButton() {
        return maxButton;
    }
}
