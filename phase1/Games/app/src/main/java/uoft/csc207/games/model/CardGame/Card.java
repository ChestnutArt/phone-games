package uoft.csc207.games.model.CardGame;

import java.util.Random;

public class Card {
    private int atk, def;
    private String name;

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public String getName() {
        return name;
    }

    public Card(int atk, int def, String name) {
        this.atk = atk;
        this.def = def;
        this.name = name;
    }

    public static Card generateCard() {
        //generate a pronouncable name
        Random r = new Random();

        StringBuilder name = new StringBuilder();

        String[] vowels = {"a", "e", "i", "o", "u", "ou", "oi", "ae"};
        String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r",
                "s", "t", "v", "w", "x", "y", "z", "qu", "gh", "ph", "th"};

        int nameLen = r.nextInt(7) + 3; // 3 <= length <= 10
        for (int i = 0; i < nameLen; i++) {
            String[] currentLetter;

            // alternate between vowels and consonants
            if (i % 2 == 0) {
                currentLetter = consonants;
            } else {
                currentLetter = vowels;
            }

            name.append(currentLetter[r.nextInt(currentLetter.length)]);
        }

        return new Card(r.nextInt(2000), r.nextInt(2000), name.toString());
    }

    // draw a rectangle displaying card name, atk, & def
    public void Draw() {
        // TODO
    }

    // draw the back of the card. should always do the exact same thing, but it's in the card class
    // for consistency
    public void DrawBack() {
        // TODO
    }
}
