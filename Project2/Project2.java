/* Good WOrk
 * Score 20
 */
import java.util.Scanner;

public class Project2 {

    public static void main(String args[]) {
        Game game = new Game(5);
        game.playAGame();

        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Play another game (y/n)?");
            String toLower = input.next().toLowerCase();
            if (toLower.equals("y")) {
                game = new Game(5);
                game.playAGame();
            } else if (toLower.equals("n")) {
                break;
            } else {
                System.out.println("Please enter y or n to tell me if you want to continue.");
            }
        }
    }
}
