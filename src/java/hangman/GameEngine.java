package hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameEngine {

    private static final int TRIES = 5;
    private static final List<String> words = SafeWords.words;
    private static Random rand = new Random();

    /**
     * runs Game Loop
     */
    public void play() {

        // prints HANGMAN tittle art
        System.out.println(
                "  _    _          _   _  _____ __  __          _   _ \r\n | |  | |   /\\   | \\ | |/ ____|  \\/  |   /\\   | \\ | |\r\n | |__| |  /  \\  |  \\| | |  __| \\  / |  /  \\  |  \\| |\r\n |  __  | / /\\ \\ | . ` | | |_ | |\\/| | / /\\ \\ | . ` |\r\n | |  | |/ ____ \\| |\\  | |__| | |  | |/ ____ \\| |\\  |\r\n |_|  |_/_/    \\_|_| \\_|\\_____|_|  |_/_/    \\_|_| \\_|\n");

        Player player; // needs to reset when play again so we initialize inside loop
        HangMan hangman = new HangMan(); // we never need to reset this one so it can stay out of loop

        // runs every session of play
        do {

            // creates new every session player
            player = new Player();

            boolean notDone = true;

            // gets random index to access a random word from the word bank
            int rngIndex = rand.nextInt(words.size());
            String word = words.get(rngIndex);
            // we will use this to output the correct guesses and the spaces left for guessing
            String wordState = "";

            // splits the random word to save into an array
            List<String> wordAsList = new ArrayList<>(Arrays.asList(words.get(rngIndex).split("")));
            // letters correctly guessed by the player
            Set<String> guessed = new HashSet<>();
            // letters incorrectly guessed by the player
            Set<String> missedLetters = new HashSet<>();

            // while still guessing
            while (notDone) {

                wordState = wordState(wordAsList, guessed);

                displayGameState(player, hangman, wordState, missedLetters);

                notDone = verify(player, hangman, word, wordAsList, guessed, missedLetters);

            }

        } while (player.playAgain());

    }

    /**
     * Checks if guess is in word and adds guessed letter to word state if its found
     * 
     * @param word    - hang man word to guess
     * @param guessed - letters correctly guessed by the player
     * @return new wordstate with current guesses
     */
    private String wordState(List<String> word, Set<String> guessed) {

        StringBuilder bld = new StringBuilder();

        for (int i = 0; i < word.size(); i++) {

            if (guessed.contains(word.get(i))) {
                bld.append(word.get(i));
            } else {
                bld.append("_");
            }

        }

        return bld.toString();
    }

    /**
     * Displays the Games current state. The state of the hangman the words gueesed and the words
     * miseed.
     * 
     * @param player        - player object
     * @param hangman       - hangman utility class
     * @param wordState     - current wordState
     * @param missedLetters - players incorrect inputs
     */
    private void displayGameState(Player player, HangMan hangman, String wordState, Set<String> missedLetters) {

        System.out.println(hangman.getState(player.getFails()));
        System.out.println("Word: " + wordState);
        System.out.println("Missed letters: " + missedLetters.toString().replaceAll("[\\s\\[\\]\\,]", " "));

        System.out.print("Take a Guess: ");

    }

    /**
     * This functinos verifies the players guess to determine the correct answer from the game.
     * Determines if the player gusssed correctly or not, if the player tried to guess the same thing
     * twice, or if the player has won or is out of tries.
     * 
     * @param player        - player object
     * @param hangMan       - hangman utility class
     * @param word          - hangman word to guess
     * @param wordAsList    - hangman word as an ArrayList
     * @param guessed       - letters correctly guessed by the player
     * @param missedLetters - letters incorrectly guessed by the player
     * @return returns if were done or not
     */
    private boolean verify(Player player, HangMan hangMan, String word, List<String> wordAsList, Set<String> guessed, Set<String> missedLetters) {

        String guess = player.guess();

        if (guessed.contains(guess) || missedLetters.contains(guess)) {
            System.out.println("Letter already guessed try Again;");

        } else if (wordAsList.contains(guess)) {
            guessed.add(guess);

            if (guessed.containsAll(wordAsList)) {
                System.out.println("The word was " + word + ". You won!");
                return false;
            }

        } else {

            missedLetters.add(guess);

            if (player.getFails() >= TRIES) {
                System.out.println(hangMan.getState(6)); // returns hanged man
                System.out.println("Out of tries little man got hanged! Better luck next time. The word was " + word);
                return false;

            } else {
                player.setFails(player.getFails() + 1);
            }

        }

        return true;

    }

}
