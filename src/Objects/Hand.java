package Objects;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private int handValue;
    private List<Card> cards;

    Hand() {
        this.handValue = 0;
        this.cards = new ArrayList<>();
    }

    int getHandValue() {
        return handValue;
    }

    Card getCardAtIndex(int index) {
        return cards.get(index);
    }

    List<Card> getCards() {
        return cards;
    }

    void add(Card card) {
        cards.add(card);
        calculateHandValue();
    }

    Card remove(int i) {
        return cards.remove(i);
    }

    void clear() {
        cards.clear();
        calculateHandValue();
    }

    private void calculateHandValue() {
        handValue = 0;
        for (Card c : cards)
            handValue += c.getValue();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("| ");
        for (Card c : cards)
            s.append(c.toString()).append(" | ");
        s.append("(Hand Value: ").append(getHandValue()).append(")");
        return s.toString();
    }
}
