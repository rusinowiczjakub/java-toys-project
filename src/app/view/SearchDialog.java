package app.view;

import javax.swing.*;
import java.awt.*;

public class SearchDialog extends JDialog {
    JPanel mainPane = new JPanel();
    JTextField valueField = new JTextField();
    JComboBox<String> columnName = new JComboBox<>();
    JButton searchButton = new JButton("Szukaj");

    public SearchDialog() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        valueField.setPreferredSize(new Dimension(100, 30));
    }

    public JPanel getMainPane() {
        return mainPane;
    }

    public JTextField getValueField() {
        return valueField;
    }

    public JComboBox<String> getColumnName() {
        return columnName;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
