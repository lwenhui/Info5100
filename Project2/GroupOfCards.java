import java.util.ArrayList;

public class GroupOfCards {
    private ArrayList<Card> cardArray; //Card[] card;
    int currentSize;
    public GroupOfCards() {}
    public GroupOfCards(int number) {
        this.cardArray = new ArrayList<Card>();
        this.currentSize = 0;

    }
    public int getCurrentSize() {
        return this.currentSize;
    }
    public Card getCard(int i) {
        return cardArray.get(i);
    }

    public void addCard(Card card) {
        this.cardArray.add(card);
        this.currentSize += 1;
    }
    public Card removeCard(int index) {
        Card card = this.getCard(index);
        this.cardArray.remove(index);
        this.currentSize -= 1;
        return card;
    }
    public void display() {
        for (Card card : cardArray) {
            card.display();
        }
    }

}