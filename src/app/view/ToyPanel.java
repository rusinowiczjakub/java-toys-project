package app.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ToyPanel {
    private JPanel toysPanel;
    private JPanel addNewBtn;
    private JTable table;
    private JScrollPane scrollPane;

    public ToyPanel() {
        addNewBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public JPanel getToysPanel() {
        return toysPanel;
    }

    public JPanel getAddNewBtn() {
        return addNewBtn;
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        toysPanel = new JPanel();
        toysPanel.setLayout(new BorderLayout(0, 0));
        toysPanel.setBackground(new Color(-1));
        toysPanel.setMinimumSize(new Dimension(800, 600));
        toysPanel.setOpaque(false);
        toysPanel.setPreferredSize(new Dimension(800, 600));
        addNewBtn = new JPanel();
        addNewBtn.setLayout(new BorderLayout(0, 0));
        addNewBtn.setBackground(new Color(-1));
        addNewBtn.setPreferredSize(new Dimension(135, 40));
        toysPanel.add(addNewBtn, BorderLayout.NORTH);
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/app/images/add-button-inside-black-circle.png")));
        label1.setText("Utwórz nowy");
        addNewBtn.add(label1, BorderLayout.NORTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return toysPanel;
    }
}