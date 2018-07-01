package Objects;

public class Card {

    private int value;
    private final Rank rank;
    private final Suit suit;

    public enum Rank { ACE, TWO, THREE, FOUR, FIVE, SIX,
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }

    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

    Card(Rank rank, Suit suit, int value) {
        this.suit = suit;
        this.value = value;
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
