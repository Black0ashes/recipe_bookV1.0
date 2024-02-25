import java.util.ArrayList;
import java.util.Vector;
import java.util.Locale.Category;


public class category {
    String category_name;

    public category(String category_name) {
        this.category_name = category_name;
    }

    public void add_category(String category_name, ArrayList<Object> list) {
        list.add(new category(category_name));
    }

    public void remove_category(String category_name, ArrayList<category> list) {
        for (category c : list) {
            if (c.category_name.equals(category_name)) {
                list.remove(c);
            }
        }

    }

    public void view_by_category() {

    }

    public String get_category_name() {
        return category_name;
    }
}

