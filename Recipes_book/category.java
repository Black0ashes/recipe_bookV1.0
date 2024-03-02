import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;

public class category {
    String category_name;
    public ArrayList<recipe> recipes_list = new ArrayList<recipe>();

    public category() {
    
    }

    public category(String category_name) {
        this.category_name = category_name;
    }

    public void add_category(String category_name, ArrayList<category> list) {
        list.add(new category(category_name));
        String csvFile = "Recipes_book\\data\\category_list.csv";
        String csvFile2 = "Recipes_book\\data\\" + category_name + "_recipe_list.csv";
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

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile2))) {
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    

    public void remove_category(String category_name, ArrayList<category> list) {
        for (int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).category_name.equals(category_name)) {
                list.remove(i);
            }
        }

        String csvFile = "Recipes_book\\data\\category_list.csv";
        ArrayList<String> temp_list = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                temp_list.add(line);
            }

            for (int i = 0 ; i < temp_list.size() ; i++) {
                if (temp_list.get(i).equals(category_name)) {
                    temp_list.remove(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String i : temp_list) {
                bw.write(i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String csvFile2 = "Recipes_book\\data\\" + category_name + "_recipe_list.csv";

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile2))) {
                String line;
                while ((line = br.readLine()) != null) {
                    temp_list.add(line);
                }
            } catch (IOException e) {
            e.printStackTrace();
            }

            for (int i = 0 ; i < temp_list.size() ; i++) {
                csvFile2 = "Recipes_book\\data\\" + category_name + "_" + temp_list.get(i) + "_ingredients.csv";
                File file = new File(csvFile2);
                    if (file.exists()) {
                    file.delete();
                    }

                csvFile2 = "Recipes_book\\data\\" + category_name + "_" + temp_list.get(i) + "_instructions.csv";
                File file2 = new File(csvFile2);
                    if (file2.exists()) {
                    file2.delete();
                    }
            }

            csvFile2 = "Recipes_book\\data\\" + category_name + "_recipe_list.csv";
            File file2 = new File(csvFile2);
                if (file2.exists()) {
                file2.delete();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }       
    }


    public void view_by_category(String category, ArrayList<category> list) {
        for (int i = 0 ; i < list.size() ; i++) {
            if (category.equals(list.get(i).category_name)) {
            ArrayList<String> temp_list = new ArrayList<String>();
            String csvFile2 = "Recipes_book\\data\\" + list.get(i).category_name + "_recipe_list.csv";

                try (BufferedReader br = new BufferedReader(new FileReader(csvFile2))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        temp_list.add(line);
                    }

                    System.out.println("\n  List of recipe in category[" + list.get(i).category_name + "] :");
                    for (int j = 0 ; j < temp_list.size() ; j++) {
                        System.out.println("\t\t" + temp_list.get(j));
                    }
                    System.out.print("\n");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void view_category() {
        ArrayList<String> temp_list = new ArrayList<String>();
        String csvFile2 = "Recipes_book\\data\\category_list.csv";

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

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }

    public void add_recipe_list(String recipe, String category) {
        recipes_list.add(new recipe(recipe, category));  
    }

    public void remove_recipe_list(int i) {
        recipes_list.remove(i);
    }

}

