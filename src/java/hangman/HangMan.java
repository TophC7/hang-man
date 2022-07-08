package hangman;

public class HangMan {

    public String getState(int triesCount) {

        switch (triesCount) {
        case 1:
            return "Head";

        case 2:
            return "torso";

        case 3:
            return "left arm";

        case 4:
            return "rigth arm";

        case 5:
            return "left leg";

        case 6:
            return "right leg";

        default:
            return "Default";
        }
    }

}
