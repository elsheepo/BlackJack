package Objects;

public class Player {

    private double cash;
    private Hand hand1;
    private Hand hand2;

    public Player(double cash) {
        this.cash = cash;
        this.hand1 = new Hand();
        this.hand2 = new Hand();
    }

    public double getCash() { return cash; }

    public void setCash(double cash) { this.cash = cash; }

    public Hand getHand1() { return hand1; }
    public Hand getHand2() { return hand2; }

    public int getHandValue(Hand hand) { return hand.getHandValue(); }

    public Card getCard1(int cardIndex) { return hand1.getCardAtIndex(cardIndex); }
    public Card getCard2(int cardIndex) { return hand2.getCardAtIndex(cardIndex); }

    public void hit(Card card, Hand hand) {
        if ((card.getValue() + getHandValue(hand)) > 21) {
            for (Card c : hand.getCards()) {
                if (c.getRank() == Card.Rank.ACE) {
                    c.setValue(1);
                }
            }
        }
        if (card.getRank() == Card.Rank.ACE)
            if (card.getValue() + getHandValue(hand) > 21) {
                card.setValue(1);
            }
        hand.add(card);
    }

    public void split() { hand2.add(hand1.remove(1)); }

    public String showHand1() { return hand1.toString(); }
    public String showHand2() { return hand2.toString(); }

    public void clearHand1() { hand1.clear(); }
    public void clearHand2() { hand2.clear(); }
}
