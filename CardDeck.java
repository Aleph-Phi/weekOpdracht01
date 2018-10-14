import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    static ArrayList<Card> newDeck = new ArrayList<Card>();
    public static ArrayList<Card> cardGen() {
        String[] Harten = {"Aas", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Boer", "Koningin", "Koning"};
        String[] Ruiten = {"Aas", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Boer", "Koningin", "Koning"};
        String[] Schoppen = {"Aas", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Boer", "Koningin", "Koning"};
        String[] Klaver = {"Aas", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Boer", "Koningin", "Koning"};
        String[][] Kaarten = {Harten, Ruiten, Schoppen, Klaver};
        int mcount = 0;
        int index;

        for (String[] arr : Kaarten) {
            mcount++;
            String Suit = "";
            if (mcount == 1) {
                Suit = "Harten";
            } else if (mcount == 2) {
                Suit = "Ruiten";
            } else if (mcount == 3) {
                Suit = "Schoppen";
            } else if (mcount == 4) {
                Suit = "Klaver";
            }
            index = 0;
            System.out.println(" ");
            for (String v : arr) {
                arr[index] = (Suit + " " + v);
                index++;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {

                newDeck.add(new Card(Kaarten[i][j], j));
            }
        }
        Collections.shuffle(newDeck);
        return newDeck;
    }
}
class Card {
    String name;
    int value;
    int resVal;

    Card(String naam, int val) {
        name = naam;
        if (naam.contains("Koning") || naam.contains("Boer")) {
            value = 10;
            resVal=value;
        } else if (naam.contains("Aas")) {
            value = 11;
            resVal = 1;
        } else {
            value = val + 1;
            resVal=value;
        }
    }
}
