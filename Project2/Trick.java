public class Trick extends GroupOfCards {
    private int winner;
    private Card winningCard;
    private boolean hearts;
    private boolean queen;
    public Trick (int players) {
        super(2 * players - 1);
        this.hearts = false;
        this.queen = false;
    }

    public int getWinner() {
        return this.winner;
    }
    public Card getWinningCard() {
        return this.winningCard;
    }
    public boolean getHearts() {
        return this.hearts;
    }
    public boolean getQueen() {
        return this.queen;
    }

    public void update(int playerNum, Card card) {
        addCard(card);
        if (this.isWinner(card) == true) {
            this.winner = playerNum;
            this.winningCard = card;
        }
        if (card.getSuit() == Card.HEARTS) {
            this.hearts = true;
        }
        if (card.getSuit() == Card.SPADES && card.getNum() == Card.QUEEN) {
            this.queen = true;
        }
    }
    private boolean isWinner(Card card) {
        if (this.winningCard != null 
            && (card.getSuit() != this.winningCard.getSuit()
            || card.getNum() < this.winningCard.getNum())) {
            return false;
        }
        return true;
    }
}