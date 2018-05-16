package app.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ToyTableModel extends AbstractTableModel {

    private String[] columnNames = {
            "Producent",
            "Nazwa",
            "Waga",
            "Minimalny wiek",
            "Cena",
            "Kategoria"
    };
    private List<Toy> toysList;

    public ToyTableModel(Model model) {
        toysList = model.getToys();
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
        }
        return temp;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
}
