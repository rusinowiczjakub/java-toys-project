package app.model;

import javax.swing.*;
import java.util.List;

public class CategoryBoxModel extends AbstractListModel implements ComboBoxModel {
    List<Category> categories;
    Object selection;

    public CategoryBoxModel(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return categories.size();
    }

    @Override
    public Object getElementAt(int index) {
        return categories.get(index);
    }
}
