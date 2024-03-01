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

    public recipe() {

    }

    public recipe(String recipe_name, String category) {
        this.recipe_name = recipe_name;
        this.category = category;
    }

    public void add_recipe(String recipe_name, ArrayList<category> list) {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        System.out.println("C:\\User\\Recipe_book\\NyX88> Enter category of this recipe ");
        System.out.print("C:\\User\\Recipe_book\\NyX88> ");
        String tempC = input.next();
        String getCategory = "";

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
                System.out.println("C:\\User\\Recipe_book\\NyX88> add recipe successfully");
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

                    String csvFile3 = "Recipes_book\\data\\bookmark_list.csv";

                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile3))) {
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
    
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile3))) {
                        for (String k : temp_list) {
                            bw.write(k + "\n");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }   
    }

    public void edit_recipe(String recipe, ArrayList<category> list) {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        String tempt = "";
        boolean check = true;
        for (int i = 0 ; i < list.size() ; i++) {
            for (int j = 0 ; j < list.get(i).recipes_list.size() ; j++) {
                if (recipe.equals(list.get(i).recipes_list.get(j).recipe_name)) {
                    int countN = 0;
                    String temp_lastN = "";

                    System.out.println("\nselect part that you need to edit : [X] to stop edit ");
                    System.out.print("\t[A] = recipe name\n");
                    System.out.print("\t[B] = ingredients\n");
                    System.out.println("\t[C] = instructions\n");

                    while (check) {
                        System.out.print("C:\\User\\Recipe_book\\NyX88> ");
                        String key = input.next();
                        
                        if (key.equals("a") || key.equals("A")) {
                            System.out.println("C:\\User\\Recipe_book\\NyX88> Enter new recipe name");
                            System.out.print("C:\\User\\Recipe_book\\NyX88> ");
                            tempt = input.next();

                            String csvFile = "Recipes_book\\data\\" + list.get(i).category_name + "_recipe_list.csv";
                            String csvFile2 = "Recipes_book\\data\\" + list.get(i).category_name + "_" + list.get(i).recipes_list.get(j).recipe_name + "_ingredients.csv";
                            String csvFile3 = "Recipes_book\\data\\" + list.get(i).category_name + "_" + list.get(i).recipes_list.get(j).recipe_name + "_instructions.csv";

                            list.get(i).recipes_list.get(j).setName(tempt);
                        
                            ArrayList<String> temp_list = new ArrayList<String>();
        
                            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    temp_list.add(line);                 
                                }
        
                                if (countN == 0) {
                                    for (int l = 0 ; l < temp_list.size() ; l++) {
                                        if (temp_list.get(l).equals(recipe)) {
                                            temp_lastN = tempt;
                                            temp_list.remove(l);
                                            temp_list.add(l, tempt);
                                            countN++;
                                            
                                        }
                                    }
                                }
                                else if (countN > 0) {
                                    for (int l = 0 ; l < temp_list.size() ; l++) {
                                        if (temp_list.get(l).equals(temp_lastN)) {
                                            temp_lastN = tempt;
                                            temp_list.remove(l);
                                            temp_list.add(l, tempt);
                                        }
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

                            temp_list.clear();

                            File oldFile = new File(csvFile2);       
                            File newFile = new File(oldFile.getParent(), list.get(i).category_name + "_" + list.get(i).recipes_list.get(j).recipe_name + "_ingredients.csv");
                            oldFile.renameTo(newFile);
                            
                            File oldFile2 = new File(csvFile3);       
                            File newFile2 = new File(oldFile.getParent(), list.get(i).category_name + "_" + list.get(i).recipes_list.get(j).recipe_name + "_instructions.csv");
                            oldFile2.renameTo(newFile2);
                        }
                        else if (key.equals("b") || key.equals("B")) {
                            String csvFile = "Recipes_book\\data\\" + list.get(i).category_name + "_" + list.get(i).recipes_list.get(j).recipe_name + "_ingredients.csv";

                            try {
                                BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
                                writer.close();
                    
                            } catch (IOException e) {
                                System.out.println("An error occurred: " + e.getMessage());
                                e.printStackTrace();
                            }

                            System.out.println("C:\\User\\Recipe_book\\NyX88> Enter new ingredient of this recipe line-by-line (put X to stop)");
                            System.out.print("C:\\User\\Recipe_book\\NyX88> ");
                            String tempi = input.next();
                            boolean checkIG = true;

                            while(checkIG) {
                                if (tempi.equals("x") || tempi.equals("X")) {
                                    checkIG = false;
                                }
                                else { 
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
                                        for (String k : temp_list) {
                                            bw.write(k + "\n");
                                        }
                                        bw.write(tempi);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    System.out.print("C:\\User\\Recipe_book\\NyX88> ");
                                    tempi = input.next();
                                }
                            }
                        }
                        else if (key.equals("c") || key.equals("C")) {
                            String csvFile = "Recipes_book\\data\\" + list.get(i).category_name + "_" + list.get(i).recipes_list.get(j).recipe_name + "_instructions.csv";

                                    try {
                                        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
                                        writer.close();
                            
                                    } catch (IOException e) {
                                        System.out.println("An error occurred: " + e.getMessage());
                                        e.printStackTrace();
                                    }

                                    System.out.println("C:\\User\\Recipe_book\\NyX88> Enter new instruction of this recipe line-by-line (put X to stop)");
                                    System.out.print("C:\\User\\Recipe_book\\NyX88> ");
                                    String tempi = input.next();
                                    boolean checkIN = true;

                                    while(checkIN) {
                                        if (tempi.equals("x") || tempi.equals("X")) {
                                            checkIN = false;
                                        }
                                        else { 
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
                                                for (String k : temp_list) {
                                                    bw.write(k + "\n");
                                                }
                                                bw.write(tempi);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                            System.out.print("C:\\User\\Recipe_book\\NyX88> ");
                                            tempi = input.next();
                                        }
                                    }
                        }
                        else if (key.equals("x") || key.equals("X")) {
                            check = false;
                        }
                    }
                }
            }   
        }
        
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

    public void setName(String recipe_name) {
        this.recipe_name = recipe_name;
    }
}
