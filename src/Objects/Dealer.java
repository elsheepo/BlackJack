package Objects;

public class Dealer {

    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public int getHandValue() {
        return hand.getHandValue();
    }

    public Card getCard(int cardIndex) {
        return hand.getCardAtIndex(cardIndex);
    }

    public void hit(Card card) {
        if ((card.getValue() + getHandValue()) > 21) {
            for (Card c : hand.getCards()) {
                if (c.getRank() == Card.Rank.ACE) {
                    c.setValue(1);
                }
            }
        }
        if (card.getRank() == Card.Rank.ACE)
            if (card.getValue() + getHandValue() > 21) {
                card.setValue(1);
            }
        hand.add(card);
    }

    public String showHand() {
        return hand.toString();
    }

    public void clearHand() {
        hand.clear();
    }
}
