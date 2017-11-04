public class Card{
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;
    public static final int CLUBS = 0;
    public static final int DIAMONDS = 1;
    public static final int HEARTS = 2;
    public static final int SPADES = 3;

    private int num;
    private int suit;
    public Card(int num, int suit) {
        this.num = num;
        this.suit = suit;
    }
    public int getNum() {
        return this.num;
    }
    public int getSuit() {
        return this.suit;
    }
    public void display() {
        StringBuilder cardDisplay = new StringBuilder();
        if (this.num > 1 && this.num <11) {
            cardDisplay.append(this.num);
        } else if (this.num == JACK) {
            cardDisplay.append("Jack");
        }else if (this.num == QUEEN) {
            cardDisplay.append("Queen");
        }else if (this.num == KING) {
            cardDisplay.append("King");
        }else if (this.num == ACE) {
            cardDisplay.append("Ace");
        }

        cardDisplay.append(" of ");

        if (this.suit == CLUBS) {
            cardDisplay.append("clubs");
        } else if (this.suit == DIAMONDS) {
            cardDisplay.append("diamonds");
        } else if (this.suit == HEARTS) {
            cardDisplay.append("hearts");
        } else if (this.suit == SPADES) {
            cardDisplay.append("spades");
        }

        System.out.print(cardDisplay.toString());
        System.out.println();
    }
}