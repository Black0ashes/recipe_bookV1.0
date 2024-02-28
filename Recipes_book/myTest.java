import java.util.Vector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class myTest {
    public static void main(String[] args) {
        // Var field
        command c1 = new command();
        
        ArrayList<category> list = new ArrayList<category>(); // use to store category object
        Scanner input = new Scanner(System.in); 
        
        String temp;
        String a = "null";
        String b = "null";
        String c = "null";
        StringTokenizer token;
        
        // start
        c1.start_program();

        // program input command loop
        while(true) {
            System.out.print("C:\\User\\Recipe_book\\NyX88> ");
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

        if ((!a.equals(null)) && (!b.equals("null")) && (!c.equals("null"))) {
            if (a.equals("add")) {
                if (b.equals("category")) {
                    c2.add_category(c, list);
                }
                if (b.equals("recipe")) {
                    r1.add_recipe(c,  list);
                }
            }
            else if (a.equals("remove")) {
                if (b.equals("category")) {
                    c2.remove_category(c, list);
                }
                if (b.equals("recipe")) {
                    r1.remove_recipe(c, list);
                }
            }
        }
        else if((!a.equals(null)) && (!b.equals("null"))) {
            if (a.equals("view")) {
                if (b.equals("command")) {
                    System.out.println(c1.view_command() + "\n" );
                }
                else {
                    System.out.println("wrong value");
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
