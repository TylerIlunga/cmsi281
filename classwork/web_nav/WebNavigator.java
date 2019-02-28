//Tyler Ilunga
package web_nav;

// TODO: Choose imports based on DS choice
import java.util.Scanner;
import java.util.Stack;

public class WebNavigator {

    // Fields
    private String current; // Tracks currently visited site
    private Stack<String> prevHistory;
    private Stack<String> currentHistory; // Tracks all sites

    // Constructor
    WebNavigator () {
        current = null;
        prevHistory = new Stack<String>();
        currentHistory = new Stack<String>();
    }

    // Methods
    // [!] YOU DO NOT HAVE TO MODIFY THIS METHOD FOR YOUR SOLUTION
    public boolean getNextUserCommand (Scanner input) {
        String command = input.nextLine();
        String[] parsedCommand = command.split(" ");

        // Switch on the command (issued first in input line)
        switch(parsedCommand[0]) {
        case "exit":
            System.out.println("Goodbye!");
            return false;
        case "visit":
            visit(parsedCommand[1]);
            break;
        case "back":
            back();
            break;
        case "forward":
            forw();
            break;
        default:
            System.out.println("[X] Invalid command, try again");
        }

        System.out.println("Currently Visiting: " + current);

        return true;
    }

    /*
     *  Visits the current site, clears the forward history,
     *  and records the visited site in the back history
     */
    public void visit (String site) {
        try {
          if (!site.contains("www.")) {
            throw new IllegalArgumentException("Site must have the 'www.' prefix.\n");
          }
          current = site;
          currentHistory.push(site);
        } catch(Error error) {
          System.out.printf("visit error: %s", error.getMessage());
        }
    }

    /*
     *  Changes the current site to the one that was last
     *  visited in the order on which visit was called on it
     */
    public void back () {
      try {
        if (currentHistory.size() < 2) {
          throw new Error("There is no site in your history to go back to!\n");
        }
        prevHistory.push(currentHistory.pop());
        current = currentHistory.peek();
      } catch(Error error) {
        System.out.printf("back error: %s", error.getMessage());
      }
    }

    public void forw () {
        try {
          if (prevHistory.size() < 1) {
            throw new Error("There is no site in your history to go forward to!\n");
          }
          current = prevHistory.pop();
          currentHistory.push(current);
        } catch(Error error) {
          System.out.printf("forw error: %s", error.getMessage());
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WebNavigator navi = new WebNavigator();

        System.out.println("Welcome to ForneyFox, enter a command from your ForneyFox user manual!");
        while (navi.getNextUserCommand(input)) {}
        System.out.println("Goodbye!");
    }

}
