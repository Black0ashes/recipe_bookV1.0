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
                    c2.add_category(c, list);
                }
                if (b.equals("recipe")) {
                    r1.add_recipe(c,  list);
                }
                if (b.equals("bookmark")) {
                    b1.add_bookmark(c, list);
                }
            }
            else if (a.equals("remove")) {
                if (b.equals("category")) {
                    c2.remove_category(c, list);
                }
                if (b.equals("recipe")) {
                    r1.remove_recipe(c, list);
                }
                if (b.equals("bookmark")) {
                    b1.remove_bookmark(c, list);
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