package hangman;

public class HangMan {

    public String getState(int triesCount) {

        switch (triesCount) {
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
