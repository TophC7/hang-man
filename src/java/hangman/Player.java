package hangman;

import java.util.Scanner;

public class Player {

    static Scanner scanner = new Scanner(System.in);
    int fails = 0;

    public int getFails() {
        return fails;
    }

    public void setFails(int fails) {
        this.fails = fails;
    }

    /**
     * This method Asks the player if they would like to play again y or n
     * 
     * @return wether we play again or not
     */
    public boolean playAgain() {

        try {

            System.out.println("Would you like to play again? y / n");

            String answer = scanner.nextLine();

            if (answer.equals("y"))
                return true;
            else if (answer.equals("n"))
                return false;
            else
                throw new WrongInputException("User did not input Y or N");

        } catch (WrongInputException e) {

            System.out.println("Wrong Input Try Again.");

            return playAgain();
        }
    }

    /**
     * This method asks for the players guess and makes sure its a alphabetic guess and only one letter
     * 
     * @return returns player guess
     */
    public String guess() {

        String guess = scanner.nextLine();

        try {
            if (!isAlpha(guess) || guess.length() > 1) {
                throw new WrongInputException("player input a string lager than 1 or something not in the alphabet.");
            } else {
                return guess;
            }
        } catch (WrongInputException e) {
            System.out.println("Your guess should be a single alphabet letter. Try Again");
            return guess();
        }

    }

    /**
     * Checks if given string is alphabetic or not
     * 
     * @param str - string to check
     * @return wether its alphabetic or not
     */
    private boolean isAlpha(String str) {
        return str.matches("[a-zA-Z]+");
    }

}
