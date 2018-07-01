package Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final List<Card> deck = new ArrayList<>(312);

    static {
        for (int i = 0; i < 6; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                int j = 1;
                for (Card.Rank rank : Card.Rank.values()) {
                    int value;
                    switch (j) {
                        case 1:
                            value = 11;
                            break;
                        case 11:
                            value = 10;
                            break;
                        case 12:
                            value = 10;
                            break;
                        case 13:
                            value = 10;
                            break;
                        default:
                            value = j;
                            break;
                    }
                    deck.add(new Card(rank, suit, value));
                    j++;
                }
            }
            Collections.shuffle(deck);
        }
    }

    public Card draw() {
        return deck.remove(deck.size() - 1);
    }
}
