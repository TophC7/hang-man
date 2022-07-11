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

    public void play() {

        Player player; // needs to reset when play again so we initialize inside loop
        HangMan hangman = new HangMan(); // we never need to reset this one so it can stay out of loop

        do {

            player = new Player();

            boolean notDone = true;

            int rngIndex = rand.nextInt(words.size());
            String word = words.get(rngIndex);
            String wordState = "";

            List<String> wordAsList = new ArrayList<>(Arrays.asList(words.get(rngIndex).split("")));
            System.out.println(word);
            Set<String> guessed = new HashSet<>();
            Set<String> missedLetters = new HashSet<>();

            while (notDone) {

                wordState = wordState(wordAsList, guessed);

                displayGameState(player, hangman, wordState, missedLetters);

                notDone = verify(player, hangman, word, wordAsList, guessed, missedLetters);

            }

        } while (player.playAgain());

    }

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

    private void displayGameState(Player player, HangMan hangman, String wordState, Set<String> missedLetters) {

        System.out.println(hangman.getState(player.getFails()));
        System.out.println("Missed letters: " + missedLetters.toString().replaceAll("[\\s\\[\\]\\,]", " "));
        System.out.println(wordState);
        System.out.println("Take a Guess:");

    }

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
                System.out.println("Out of tries little man got hanged! Better luck next time.");
                return false;

            } else {
                player.setFails(player.getFails() + 1);
            }

        }

        return true;

    }

}
