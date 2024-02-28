import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class recipe {
    String recipe_name;
    String category;

    public recipe(String recipe_name, String category) {
        this.recipe_name = recipe_name;
        this.category = category;
    }

    public recipe() {

    }

    public void add_recipe(String recipe_name, ArrayList<category> list) {
        Scanner input = new Scanner(System.in);
        System.out.println("C:\\User\\Recipe_book\\NyX88> Enter category of this recipe ");
        System.out.print("C:\\User\\Recipe_book\\NyX88> ");
        String tempC = input.next();
        String getCategory = "";
        category c1 = new category();

        for (int j = 0 ; j < list.size() ; j++) {
            if (tempC.equals(list.get(j).category_name)) {
                list.get(j).add_recipe_list(recipe_name,category);
                getCategory = list.get(j).category_name;

                String csvFile = "Recipes_book\\data\\" + list.get(j).category_name + "_recipe_list.csv";
                String csvFile2 = "Recipes_book\\data\\" + list.get(j).category_name + "_" + recipe_name + "_ingredients.csv";
                String csvFile3 = "Recipes_book\\data\\" + list.get(j).category_name + "_" + recipe_name + "_instructions.csv";
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
                    bw.write(recipe_name);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile2))) {
                    bw.write("");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile3))) {
                    bw.write("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("C:\\User\\Recipe_book\\NyX88> Enter ingredient of this recipe line-by-line (put X to stop)");
        System.out.print("C:\\User\\Recipe_book\\NyX88> ");
        String tempi = input.next();
        boolean check = true;

        while(check) {
            if (tempi.equals("x") || tempi.equals("X")) {
                check = false;
            }
            else { 
                String csvFile = "Recipes_book\\data\\" + getCategory + "_" + recipe_name + "_ingredients.csv";
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
                    bw.write(tempi);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.print("C:\\User\\Recipe_book\\NyX88> ");
                tempi = input.next();
            }
        }

        System.out.println("C:\\User\\Recipe_book\\NyX88> Enter instructions of this recipe line-by-line (put X to stop)");
        System.out.print("C:\\User\\Recipe_book\\NyX88> ");
        String tempit = input.next();
        check = true;

        while(check) {
            if (tempit.equals("x") || tempit.equals("X")) {
                check = false;
            }
            else { 
                String csvFile = "Recipes_book\\data\\" + getCategory + "_" + recipe_name + "_instructions.csv";
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
                    bw.write(tempit);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.print("C:\\User\\Recipe_book\\NyX88> ");
                tempit = input.next();
            }
        }
    }

    public void remove_recipe(String recipe, ArrayList<category> list) {
        for (int i = 0 ; i < list.size() ; i++) {
            for (int j = 0 ; j < list.get(i).recipes_list.size() ; j++) {
                if (recipe.equals(list.get(i).recipes_list.get(j).recipe_name)) {
                    list.get(i).remove_recipe_list(j);

                    String csvFile = "Recipes_book\\data\\" + list.get(i).category_name + "_recipe_list.csv";
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

                    String csvFile2 = "Recipes_book\\data\\" + list.get(i).category_name + "_" + recipe + "_ingredients.csv";
                    File file = new File(csvFile2);
                    if (file.exists()) {
                    file.delete();
                    }

                    csvFile2 = "Recipes_book\\data\\" + list.get(i).category_name + "_" + recipe + "_instructions.csv";
                    File file2 = new File(csvFile2);
                    if (file2.exists()) {
                    file2.delete();
                    }
                }
            }
        }   
    }

    public void edit_recipe() {
        
    }
    public void view_recipe(String recipe, ArrayList<category> list) {

        for (int i = 0 ; i < list.size() ; i++) {
            for (int j = 0 ; j < list.get(i).recipes_list.size() ; j++) {
                if (recipe.equals(list.get(i).recipes_list.get(j).recipe_name)) {
                    System.out.println("\n\tRecipe name : " + list.get(i).recipes_list.get(j).recipe_name);
                    System.out.println("\tCategory : " + list.get(i).category_name);

                
                ArrayList<String> temp_list = new ArrayList<String>();
                String csvFile = "Recipes_book\\data\\" + list.get(i).category_name + "_" + list.get(i).recipes_list.get(j).recipe_name + "_ingredients.csv";

                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        temp_list.add(line);                 
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("\tIngredients :");
                for (int k = 0 ; k < temp_list.size() ; k++) {
                    System.out.println("\t" + temp_list.get(k));
                }

                ArrayList<String> temp_list2 = new ArrayList<String>();
                csvFile = "Recipes_book\\data\\" + list.get(i).category_name + "_" + list.get(i).recipes_list.get(j).recipe_name + "_instructions.csv";

                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        temp_list2.add(line);                 
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.print("\n");
                System.out.println("\tInstructions :");
                for (int k = 0 ; k < temp_list2.size() ; k++) {
                    System.out.println("\t" + temp_list2.get(k));
                }
                }
                System.out.print("\n");
        }
    }
    }

    public String getname() {
        return recipe_name;
    }
}
