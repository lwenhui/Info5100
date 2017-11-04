
public class Hand extends GroupOfCards {
    public final int NUM;
    private int shortest;
    public Hand(int playerNum, int numberOfCards) {
        super(numberOfCards);
        NUM = playerNum;
    }

    public void sort() {
        for (int unsorted = this.getCurrentSize() - 1; unsorted >= 0; unsorted--) {
            Card max = this.getCard(unsorted);
            int maxIndex = unsorted;

            for (int index = 0; index < unsorted; index++) {
                if ((13 * max.getSuit() + max.getNum()) < (13 * this.getCard(index).getSuit() +this.getCard(index).getNum())) {
                    max = this.getCard(index);
                    maxIndex = index;
                }
            }
            this.removeCard(maxIndex);
            this.addCard(max);
        }
    }
    public void setShortest() {
        this.shortest = Card.CLUBS;
        if (this.findCout(this.shortest) == 0
            ||(this.findCout(Card.DIAMONDS) != 0 && this.findCout(Card.DIAMONDS) <= this.findCout(this.shortest))) {
            this.shortest = Card.DIAMONDS;
        }
        if ((this.findCout(this.shortest) == 0 || (this.findCout(Card.SPADES) != 0 && this.findCout(Card.SPADES) <= this.findCout(this.shortest)))
                && this.find(Card.ACE, Card.SPADES) == -1
                && this.find(Card.KING, Card.SPADES) == -1
                &&this.find(Card.QUEEN, Card.SPADES) == -1) {
            this.shortest = Card.SPADES;
        }
    }
    public int getShortest() {
        return this.shortest;
    }
    public Card playACard(Game game, Trick trick) {
        int index = -1;
        if (trick.getCurrentSize() == 0) {
            int suit = this.getShortest();
            index = this.findHighest(suit);
            //System.out.println("4.index " + index);
            if (index == -1) {
                index = findLowest(game);
                //System.out.println("3.index " + index);
                if (index == -1) {
                    index = findLowest(Card.HEARTS);
                }
            }
        } else if (trick.getCurrentSize() == game.PLAYERS - 1
                   && !trick.getHearts()
                   && !trick.getQueen()
                   && (index = findHighest(trick.getWinningCard().getSuit())) >= 0);
        else if ((trick.getCurrentSize() == game.PLAYERS - 1
                   && !trick.getHearts() 
                   && !trick.getQueen())
                   && (index = findLastHigh(trick.getWinningCard().getSuit())) >= 0);
        else if ((index = findHighestBelow(trick.getWinningCard())) >= 0);
        else if ((index = findMiddleHigh(game, trick.getWinningCard().getSuit())) >= 0);
        else if ((index = find(Card.QUEEN, Card.SPADES)) >= 0);
        else if ((index = find(Card.ACE, Card.SPADES)) >= 0);
        else if ((index = find(Card.KING, Card.SPADES)) >= 0);
        else if ((index = findHighest(Card.HEARTS)) >= 0);
        else {
            index = findHighest();
        }
        trick.update(NUM, getCard(index));
        return removeCard(index);
    }

    //return the index of lowest numbered card in suit. no card -1
    public int findLowest(int suit){
        //System.out.println("FindLowest suit " + suit);
        int low = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < super.getCurrentSize(); i++) {
            Card card = super.getCard(i);
            if (card.getSuit() != suit) {
                continue;
            }
            if (low > card.getNum()) {
                low = card.getNum();
                index = i;
            }
        }
        return index;
    }

    //number of cards in suit
    private int findCout(int suit) {
        int count = 0;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (this.getCard(i).getSuit() == suit) {
                count++;
            }
        }
        return count;
    }
    //return index of num = num, suit = suit
    private int find(int num, int suit) {
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (this.getCard(i).getSuit() == suit && this.getCard(i).getNum() == num) {
                return i;
            }
        }
        return -1;
    }
    //find highest in suit
    private int findHighest(int suit) {
        //System.out.println("findHighest suit " + suit);
        int high = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (this.getCard(i).getSuit() != suit) {
                continue;
            }
            if (high < this.getCard(i).getNum()) {
                high = this.getCard(i).getNum();
                index = i;
            }
        }
        return index;
    }
    //lowest in hand, not heart until hearts have been broken. not broken, all hearts -1
    private int findLowest(Game game) {
        // System.out.println("findLowest game ");
        // System.out.println("CurrentSize :" + this.getCurrentSize());
        // System.out.println("Game Heart: " + game.getHearts());
        int lowest = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            Card card = this.getCard(i);
            if (!game.getHearts() && card.getSuit() == Card.HEARTS) {
                continue;
            }
            if (lowest > card.getNum()) {
                lowest = card.getNum();
                index = i;
            }
        }
        return index;
    }

    //no bad cards-- highest card in suit
    //highest == queen of spades, have another spade---highest below queen
    private int findLastHigh(int suit) {
        int hightest = this.findHighest(suit);//////bad cards?
        if (hightest >= 0) {
            Card high = this.getCard(hightest);
            if (high.getNum() == Card.QUEEN
                    && high.getSuit() == Card.SPADES
                    && this.findCout(Card.SPADES) != 1) {
                hightest = findHighestBelow(high);
            }
        }
        return hightest;
    }

    //suit = winningCard.suit, num = first one having number less than winningCard.number
    private int findHighestBelow(Card winningCard) {///////
        // System.out.println("suit " + winningCard.getSuit());
        // System.out.println("num " + winningCard.getNum());
        if (this.findCout(winningCard.getSuit()) == 0) {
            return -1;
        }
        int index = -1;
        int hightestBelow = Integer.MIN_VALUE;
        for (int i = 0; i < super.currentSize; i++) {
            Card tempCard = super.getCard(i);
            if (tempCard.getSuit() != winningCard.getSuit()) {
                continue;
            }
            if (hightestBelow < winningCard.getNum() 
                && hightestBelow < tempCard.getNum()) {
                hightestBelow = tempCard.getNum();
                index = i;
            }
        }
        return index;
    }
    //suit=spade queue not played return spade no higher than j
    private int findMiddleHigh(Game game, int suit) {
        //System.out.println("findMiddleHigh suit " + suit);

        if (suit == Card.SPADES && !game.getQueenOfSpades()) {
            return this.findHighestBelow(new Card(Card.QUEEN, Card.SPADES));
        }
        return findHighest(suit);
    }
    private int findHighest() {
        //System.out.println("findHighest  ");
        int high = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (high < this.getCard(i).getNum()) {
                high = this.getCard(i).getNum();
                index = i;
            }
        }
        return index;
    }
}