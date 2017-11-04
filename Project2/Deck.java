public class Deck extends GroupOfCards {
    public static final int TOTAL_CARDS = 52;

    public Deck() {
        super(TOTAL_CARDS);
        for (int i = 2; i <= Card.ACE; i++) {
            for (int j = Card.CLUBS; j <= Card.SPADES; j++) {
                addCard(new Card(i, j));
            }
        }
    }

    public void shuffle() {
        for (int i = super.getCurrentSize(); i > 0; i--) {
            int index = (int)(Math.random() * i);
            Card temp = super.getCard(index);
            super.removeCard(index);
            super.addCard(temp);
        }
    }
    public Card dealCard() {
        return super.removeCard(0);
    }
}