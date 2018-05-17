package app.model;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ToyTableModel extends AbstractTableModel {

    private String[] columnNames = {
            "Producent",
            "Nazwa",
            "Waga",
            "Minimalny wiek",
            "Cena",
            "Kategoria",
            ""
    };
    private List<Toy> toysList;

    public ToyTableModel(List<Toy> toys) {
        toysList = toys;
    }

    @Override
    public int getRowCount() {
        return toysList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;

        switch (columnIndex) {
            case 0:
                temp = toysList.get(rowIndex).getProducer();

                break;
            case 1:
                temp = toysList.get(rowIndex).getName();

                break;
            case 2:
                temp = toysList.get(rowIndex).getWeight();

                break;
            case 3:
                temp = toysList.get(rowIndex).getMinimalAge();

                break;
            case 4:
                temp = toysList.get(rowIndex).getPrice();

                break;

            case 5:
                temp = toysList.get(rowIndex).getCategory().toString();

                break;

            case 6:
                final JButton deleteButton = new JButton("usuń");
                deleteButton.setBackground(new Color(255, 255, 255, 100));
                deleteButton.addActionListener(e -> {
                        toysList.remove(rowIndex);
                        fireTableDataChanged();
                });
                return deleteButton;
        }
        return temp;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
}
