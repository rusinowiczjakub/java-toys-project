package app.view;

import javax.swing.*;
import java.awt.*;

public class FindByPriceDialog extends JDialog {
    JPanel mainPane = new JPanel();
    JLabel label = new JLabel("Cena");
    JComboBox<String> operator = new JComboBox<>();
    JSpinner price;
    JButton searchButton;

    public FindByPriceDialog() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SpinnerNumberModel spinnerPriceModel = new SpinnerNumberModel(0.00, -999999999.00, 999999999.00, 0.1);
        price = new JSpinner(spinnerPriceModel);
        mainPane.add(label, BorderLayout.WEST);
        searchButton  = new JButton("Szukaj");

        operator.addItem(">");
        operator.addItem("<");
        mainPane.add(operator, BorderLayout.CENTER);
        mainPane.add(price, BorderLayout.EAST);
        mainPane.add(searchButton, BorderLayout.SOUTH);

        this.setContentPane(mainPane);
    }

    public JPanel getMainPane() {
        return mainPane;
    }

    public JComboBox<String> getOperator() {
        return operator;
    }

    public JSpinner getPrice() {
        return price;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
