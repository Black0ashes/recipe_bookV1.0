import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

public class category {
    String category_name;

    public category(String category_name) {
        this.category_name = category_name;
    }

    public void add_category(String category_name, ArrayList<Object> list) {
        list.add(new category(category_name));
        String csvFile = "C:\\Users\\Black\\Desktop\\java project\\Recipes_book\\data\\category_list.txt";
        ArrayList<String> temp_list = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                temp_list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String i : temp_list) {
                bw.write(i + "\n");
            }
            bw.write(category_name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
    
// "C:\\Users\\Black\\Desktop\\java project\\Recipes_book\\data\\category_list.txt"

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

