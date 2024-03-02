import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class bookmark {
    
    public void add_bookmark(String recipe, ArrayList<category> list) {
        int check = 0;
        for (int i = 0 ; i < list.size() ; i++) {
            for (int j = 0 ; j < list.get(i).recipes_list.size() ; j++) {
                if (recipe.equals(list.get(i).recipes_list.get(j).recipe_name)) { // use for find recipe in list
                    check++;
                    ArrayList<String> temp_list = new ArrayList<String>();
                    String csvFile = "Recipes_book\\data\\bookmark_list.csv";

                    // read and write in bookmark_list
                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                    temp_list.add(line);                 
                        }
                    } catch (IOException e) {
                    e.printStackTrace();
                    }

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
                    for (String k : temp_list) {
                        bw.write(k + "\n");
                                }
                    bw.write(recipe);
                        } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (check == 0) {
            System.out.println("C:\\User\\Recipe_book\\NyX88> not found this bookmark in list");
        }
    }

    public void view_bookmark() {
        ArrayList<String> temp_list = new ArrayList<String>();
        String csvFile2 = "Recipes_book\\data\\bookmark_list.csv";

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile2))) {
                String line;
                while ((line = br.readLine()) != null) {
                    temp_list.add(line);
                }

                System.out.println("\n  List of recipe in bookmark :");
                for (int j = 0 ; j < temp_list.size() ; j++) {
                    System.out.println("\t\t" + temp_list.get(j));
                }
                
                System.out.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove_bookmark(String recipe, ArrayList<category> list) {
        String csvFile = "Recipes_book\\data\\bookmark_list.csv";
        ArrayList<String> temp_list = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
            temp_list.add(line);                 
            }

            for (int l = 0 ; l < temp_list.size() ; l++) {
                if (temp_list.get(l).equals(recipe)) {
                    temp_list.remove(l);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String k : temp_list) {
                bw.write(k + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
