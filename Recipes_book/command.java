import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class command{

    public String view_command() {
        return "Command List :\n\tadd recipe [recipe_name]\n\tremove recipe [recipe_name]\n\tview recipe [recipe_name]\n\tview recipe list" +
            "\n\tedit recipe [recipe_name]\n\tview command\n\tadd category [category_name]\n\tremove category [category_name]" +
            "\n\tadd bookmark [recipe_name]\n\tremove bookmark\n\tview bookmark\n\tview category [category_name]\n\tview category list\n\tsearch name [recipe_name]\n\tsearch ingredient [ingredient_name]";
    }

    // Search command
    public void search_by_name(String recipe, ArrayList<category> list) {
        int count = 0;
        for (int i = 0 ; i < list.size() ; i++) {
            for (int j = 0 ; j < list.get(i).recipes_list.size() ; j++) {
                if (recipe.equals(list.get(i).recipes_list.get(j).recipe_name)) {
                    System.out.print("\nresult form search using recipe name : ");
                    System.out.println("\t\n" + list.get(i).recipes_list.get(j).recipe_name + "\n");
                    count++;
                }
            }
        }
        if (count == 0) {
            System.out.println("\nnot found you recipe in list\n");
        }
    }

    public void search_by_ingredient(String ingerdient, ArrayList<category> list) {
        int count = 0;
        ArrayList<String> temp_list = new ArrayList<String>();
        ArrayList<String> temp_list2 = new ArrayList<String>();

        for (int i = 0 ; i < list.size() ; i++) {
            for (int j = 0 ; j < list.get(i).recipes_list.size() ; j++) {
                String csvFile = "Recipes_book\\data\\" + list.get(i).category_name + "_" + list.get(i).recipes_list.get(j).recipe_name + "_ingredients.csv";
                
                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        temp_list.add(line);                 
                    }

                    for (int l = 0 ; l < temp_list.size() ; l++) {
                        if (temp_list.get(l).equals(ingerdient)) {
                            temp_list2.add(list.get(i).recipes_list.get(j).recipe_name);
                            count++;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                temp_list.clear();
            }
        }

        if (count == 0) {
            System.out.println("\nnot found found recipe with this ingredients in lists\n");
        }
        else {
            System.out.println("\nresult form search using ingredient : ");
            for (int i = 0 ; i < temp_list2.size() ; i++) {
                System.out.println("\t" + temp_list2.get(i));
            }
            System.out.print("\n");
        }
        temp_list2.clear();
    }

    public void start_program(ArrayList<category> list) {

        ArrayList<String> temp_list = new ArrayList<String>();
        ArrayList<String> temp_list2 = new ArrayList<String>();
        String csvFile = "Recipes_book\\data\\category_list.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                temp_list.add(line);                 
            }
        } catch (IOException e) {
        e.printStackTrace();
        }

        for (int i = 0 ; i < temp_list.size() ; i++) {
            list.add(new category(temp_list.get(i)));
        }

        for (int i = 0 ; i < temp_list.size() ; i++) {
            csvFile = "Recipes_book\\data\\" + temp_list.get(i) + "_recipe_list.csv";

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    temp_list2.add(line);                 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int j = 0 ; j < temp_list2.size() ; j++) {
                list.get(i).add_recipe_list(temp_list2.get(j),list.get(i).category_name);
            }
        }
        command c1 = new command();
        System.out.println(c1.view_command() + "\n" );
        
    }
}

