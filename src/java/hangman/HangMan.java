package hangman;

public class HangMan {

    public String getState(int triesCount) {

        switch (triesCount) {
        case 1:
            return "+====+\r\n O   |\r\n     |\r\n     |\r\n    ===";

        case 2:
            return "+====+\r\n O   |\r\n |   |\r\n     |\r\n    ===";

        case 3:
            return "+====+\r\n O   |\r\n-|   |\r\n     |\r\n    ===";

        case 4:
            return "+====+\r\n O   |\r\n-|-  |\r\n     |\r\n    ===";

        case 5:
            return "+====+\r\n O   |\r\n-|-  |\r\n/    |\r\n    ===";

        case 6:
            return "+====+\r\n O   |\r\n-|-  |\r\n/\\   |\r\n    ===";

        default:
            return "+====+\r\n     |\r\n     |\r\n     |\r\n    ===";
        }
    }

}
