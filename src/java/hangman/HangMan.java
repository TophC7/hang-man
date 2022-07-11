package hangman;

public class HangMan {

    /**
     * This function returns the state of the hangman according to the players fails.
     * 
     * @param fails - how many times the player has failed
     * @return the correct state of the hangman acording to the fails
     */
    public String getState(int fails) {

        switch (fails) {
        case 1:
            return "\n+====+\r\n O   |\r\n     |\r\n     |\r\n    ===";

        case 2:
            return "\n+====+\r\n O   |\r\n |   |\r\n     |\r\n    ===";

        case 3:
            return "\n+====+\r\n O   |\r\n-|   |\r\n     |\r\n    ===";

        case 4:
            return "\n+====+\r\n O   |\r\n-|-  |\r\n     |\r\n    ===";

        case 5:
            return "\n+====+\r\n O   |\r\n-|-  |\r\n/    |\r\n    ===";

        case 6:
            return "\n+====+\r\n O   |\r\n-|-  |\r\n/ \\  |\r\n    ===";

        default:
            return "\n+====+\r\n     |\r\n     |\r\n     |\r\n    ===";
        }
    }

}
