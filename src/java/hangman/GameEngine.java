package hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameEngine {

    Player player = new Player();

    private static boolean notDone = true;
    private static final int LIMBS = 6;
    private static final List<String> words = SafeWords.words;
    private static Random rand = new Random();

    public void play() {

        do {

            HangMan hangman = new HangMan();

            int limbCount = 0;
            int rngIndex = rand.nextInt(words.size());
            String wordState = "";

            List<String> word = new ArrayList<>(Arrays.asList(words.get(rngIndex).split("")));
            Set<String> missedLetters = new HashSet<>();
            Set<String> guessed = new HashSet<>();

            // !FIXME: this shouldnt loop with the tries only if notDone
            while ((LIMBS >= limbCount) && notDone) {

                wordState = wordState(word, guessed);

                displayGameState(hangman, limbCount, missedLetters, wordState);

                verify(player.guess(), word, missedLetters, guessed);

                limbCount++;

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

    private void displayGameState(HangMan hangman, int triesCount, Set<String> missedLetters, String wordState) {

        System.out.println(hangman.getState(triesCount));

        System.out.println("Missed letters: " + missedLetters.toString().replaceAll("[\\s\\[\\]\\,]", " "));

        System.out.println(wordState);

        System.out.println("Take a Guess:");

    }

    private void verify(String guess, List<String> word, Set<String> missedLetters, Set<String> guessed) {

        if (word.contains(guess)) {
            guessed.add(guess);
        } else {
            missedLetters.add(guess);
        }

    }

}
