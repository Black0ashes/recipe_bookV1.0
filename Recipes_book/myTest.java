import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class myTest {
    public static void main(String[] args) {
        // Var field
        command c1 = new command();
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in); 
        
        ArrayList<category> list = new ArrayList<category>(); // use to store category object
        String temp;        
        StringTokenizer token;
        
        // start
        c1.start_program(list);

        // program input command loop
        while(true) {
            System.out.print("C:\\User\\Recipe_book\\NyX88> ");
            String a = "";
            String b = "";
            String c = "";
            temp = input.nextLine();
            token = new StringTokenizer(temp);
            a = token.nextToken();

            // seperate input for matching
            if (token.hasMoreTokens()) {
                b = token.nextToken();
                if (token.hasMoreTokens()) {
                    c = token.nextToken();
                    match_command(a, b, c,list);
                }
                else {
                    match_command(a,b,c,list);
                }
            } 
            else {
                match_command(a,b,c,list);
            }
        }
    }

    // use for match command
    public static void match_command(String a, String b, String c,ArrayList<category> list) {
        command c1 = new command();
        category c2 = new category("null");
        recipe r1 = new recipe("null","null");
        bookmark b1 = new bookmark();

        if ((!a.equals("")) && (!b.equals("")) && (!c.equals(""))) {
            if (a.equals("add")) {
                if (b.equals("category")) {
                    int s = 0;
                    for (int i = 0 ; i < list.size() ; i++) {
                            if (c.equals(list.get(i).category_name) && s == 0) {
                                System.out.println("C:\\User\\Recipe_book\\NyX88> already have this category in list ");
                                s++;
                            }
                
                    }
                    if (s == 0) {
                        c2.add_category(c, list);
                    } 
                }
                if (b.equals("recipe")) {
                    int s = 0;
                    for (int i = 0 ; i < list.size() ; i++) {
                        for (int j = 0 ; j < list.get(i).recipes_list.size() ; j++) {
                            if (c.equals(list.get(i).recipes_list.get(j).recipe_name) && s == 0) {
                                System.out.println("C:\\User\\Recipe_book\\NyX88> already have this recipe in list ");
                                s++;
                            }
                        }
                    }
                    if (s == 0) {
                        r1.add_recipe(c,  list);
                    } 
                }
                if (b.equals("bookmark")) {
                    int s = 0;
                    ArrayList<String> temp_list = new ArrayList<String>();
                    String csvFile2 = "Recipes_book\\data\\bookmark_list.csv";

                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile2))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            temp_list.add(line);
                        }
                    }catch (IOException e) {
                    e.printStackTrace();
                    }
                    
                    for (int i = 0 ; i < temp_list.size() ; i++) {
                        if (c.equals(temp_list.get(i)) && s == 0) {
                            System.out.println("C:\\User\\Recipe_book\\NyX88> already have this bookmark in list ");
                            s++;
                        }
                    }
                    if (s == 0) {
                        b1.add_bookmark(c, list);
                    } 
                }
            }
            else if (a.equals("remove")) {
                if (b.equals("category")) {
                    int s = 0;
                    for (int i = 0 ; i < list.size() ; i++) {
                            if (c.equals(list.get(i).category_name) && s == 0) {
                                c2.remove_category(c, list);
                                s++;
                            }
                    }
                    if (s == 0) {
                        System.out.println("C:\\User\\Recipe_book\\NyX88> not found this category in list ");
                    } 
                }
                if (b.equals("recipe")) {
                    int s = 0;
                    for (int i = 0 ; i < list.size() ; i++) {
                        for (int j = 0 ; j < list.get(i).recipes_list.size() ; j++) {
                            if (c.equals(list.get(i).category_name) && s == 0) {
                                r1.remove_recipe(c, list);
                                s++;
                            }
                        }     
                    }
                    if (s == 0) {
                        System.out.println("C:\\User\\Recipe_book\\NyX88> not found this recipe in list ");
                    } 

                }
                if (b.equals("bookmark")) {
                    int s = 0;
                    ArrayList<String> temp_list = new ArrayList<String>();
                    String csvFile2 = "Recipes_book\\data\\bookmark_list.csv";

                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile2))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            temp_list.add(line);
                        }
                    }catch (IOException e) {
                    e.printStackTrace();
                    }

                    for (int i = 0 ; i < temp_list.size() ; i++) {
                        if (c.equals(temp_list.get(i)) && s == 0) {
                            b1.remove_bookmark(c, list);
                            s++;
                        }
                    }
                    if (s == 0) {
                        System.out.println("C:\\User\\Recipe_book\\NyX88> not found this bookmark in list ");
                    } 
                }
            }
            else if (a.equals("view")) {
                if (b.equals("recipe")) {
                    if (c.equals("list")) {
                        r1.view_recipe_list(list);
                    }
                    else {
                        r1.view_recipe(c, list);
                    }

                }
                if (b.equals("category")) {
                    if (c.equals("list")) {
                        c2.view_category();
                    }
                    else {
                        c2.view_by_category(c, list);
                    }
                    
                }
            }
            else if (a.equals("search")) {
                if (b.equals("name")) {
                    c1.search_by_name(c, list);
                }
                if (b.equals("ingredient")) {
                    c1.search_by_ingredient(c, list);
                }
            }
            else if (a.equals("edit")) {
                if (b.equals("recipe")) {
                    r1.edit_recipe(c, list);
                }
            }
        }
        else if((!a.equals("")) && (!b.equals("")) && (c.equals(""))) {
            if (a.equals("view")) {
                if (b.equals("command")) {
                    System.out.println(c1.view_command() + "\n" );
                }
                if (b.equals("bookmark")) {
                    b1.view_bookmark();
                }
                if (b.equals("recipe")) {
                    System.out.println(" wrong value");
                }
                if (b.equals("category")) {
                    System.out.println(" wrong value");
                }

            }
            else {
                System.out.println("wrong value");
            }
        }
        else {
            System.out.println("wrong value");
        }

    }
}