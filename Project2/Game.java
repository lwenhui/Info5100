import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public final int PLAYERS;
    private Deck deck;
    private ArrayList<Hand> players;
    private Trick[] tricks;
    private int numberOfTricks;
    private boolean hearts;
    private boolean queenOfSpades;

    public Game(int numberOfPlayers) {
        PLAYERS = numberOfPlayers;

        this.players = new ArrayList<Hand>();//players = new Hand[numberOfPlayer]
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players.add(new Hand(i, Deck.TOTAL_CARDS / PLAYERS));
        }
        this.tricks = new Trick[Deck.TOTAL_CARDS / PLAYERS];

        this.deck = new Deck();
        this.hearts = false;
        this.queenOfSpades = false;
        this.numberOfTricks = 0;
    }

    public int getNumberOfTricks() {
        return this.numberOfTricks;
    }
    public boolean getHearts() {
        return this.hearts;
    }
    public boolean getQueenOfSpades() {
        return this.queenOfSpades;
    }

    //make sure undelt card has no point
    private void avoidPointINUndelt(int cardsLeft) {
        for (int undeltindex = Deck.TOTAL_CARDS - 1; undeltindex >= Deck.TOTAL_CARDS - cardsLeft; undeltindex--) {
            Card undeltcard = deck.getCard(undeltindex);
            //if undelt card has point, move it to the front
            if (undeltcard.getSuit() != Card.HEARTS
                    && (undeltcard.getSuit() != Card.SPADES || undeltcard.getNum() != Card.QUEEN)) {
                this.deck.addCard(this.deck.removeCard(undeltindex));
            }
        }
        //Loop undelt card which has point. Randomly choose one card without point in the front, move it to the end
        for (int undeltindex = 0; undeltindex < cardsLeft; undeltindex++) {
            Card undeltcard = deck.getCard(Deck.TOTAL_CARDS - cardsLeft);

            if (undeltcard.getSuit() == Card.HEARTS
                    || (undeltcard.getNum() == Card.QUEEN && undeltcard.getSuit() == Card.QUEEN)) {
                int index = (int)(Math.random() * (Deck.TOTAL_CARDS - cardsLeft - 1));
                Card randomCard = deck.getCard(index);
                while (randomCard.getSuit() == Card.HEARTS
                        || (randomCard.getSuit() == Card.SPADES && randomCard.getNum() == Card.QUEEN)) {
                    index = (int)(Math.random() * (Deck.TOTAL_CARDS - cardsLeft - 1));
                    randomCard = deck.getCard(index);
                }
                deck.addCard(deck.removeCard(index));
            }
        }
    }

    public void playAGame() {
        this.deck.shuffle();
        int cardsLeft = Deck.TOTAL_CARDS % PLAYERS;
        avoidPointINUndelt(cardsLeft);

        for (Trick trick : tricks) {
            for (int i = 0; i < PLAYERS; i++) {
                players.get(i).addCard(this.deck.dealCard());
            } 
        }
        
        int playerNum = -1;
        int lowest = Integer.MAX_VALUE;
        int indexOfLowestFinal = -1;
        System.out.println("Output – first part: ");
        System.out.println();
        for (Hand handOfPlayer : players) {

            handOfPlayer.sort();
            handOfPlayer.setShortest();

            System.out.println("         player " + handOfPlayer.NUM + "  shortest= " + handOfPlayer.getShortest());
            handOfPlayer.display();

            int indexOfLowest = handOfPlayer.findLowest(Card.CLUBS);
            if (indexOfLowest != -1 && handOfPlayer.getCard(indexOfLowest).getNum() < lowest) {
                lowest = handOfPlayer.getCard(indexOfLowest).getNum();
                playerNum = handOfPlayer.NUM;
                indexOfLowestFinal = indexOfLowest;
            }
        }

        System.out.println();
        System.out.println("Output – second part: ");
        
        int index = -1;

        for (int i = 0; i < tricks.length; i++) {
            System.out.println();
            tricks[i] = new Trick(PLAYERS);
            this.numberOfTricks++;
            Card card = new Card(0, -1);
            //System.out.println("NumberOfTricks " + numberOfTricks);
            if (this.numberOfTricks == 1) {
                index = indexOfLowestFinal;
                card = players.get(playerNum).getCard(index);
                players.get(playerNum).removeCard(index);
                tricks[i].update(playerNum, card);
                
            } else {
                card = players.get(playerNum).playACard(this, tricks[i]);///////
            }
            System.out.print("player " + playerNum + "        ");
            card.display();
            updateHeartsAndQueen(card);

            for(int num = 0; num < this.PLAYERS - 1; num++) {
                playerNum = (playerNum + 1) % this.PLAYERS;
                card = players.get(playerNum).playACard(this, tricks[i]);
                System.out.print("player " + playerNum + "        ");
                card.display();
                updateHeartsAndQueen(card);
            }

            playerNum = tricks[i].getWinner();

            if (this.numberOfTricks == 1) {
                for (int undeltindex = 0; undeltindex < cardsLeft; undeltindex++) {
                    Card undeltacard = deck.getCard(undeltindex);
                    System.out.print("undelt card     ");
                    undeltacard.display();
                }
            }
        }

        for (Hand handOfPlayer : players) {
            int[] points = new int[PLAYERS];
            points[handOfPlayer.NUM] += computePoints(handOfPlayer.NUM);
            System.out.println("Player " + handOfPlayer.NUM + " score= " + points[handOfPlayer.NUM]);
        }
    }
    public void updateHeartsAndQueen(Card card) {
        if (card.getSuit() == Card.HEARTS && !this.hearts) {
            System.out.println("Hearts is now broken");
            this.hearts = true;
        }
        if (card.getSuit() == Card.SPADES && card.getNum() == Card.QUEEN) {
            this.queenOfSpades = true;
        }
    }
    private int computePoints(int playerNum) {
        int point = 0;
        for (int i = 0; i < tricks.length; i++) {

            if (tricks[i].getWinner() == playerNum) {
                for (int j = 0; j < tricks[i].getCurrentSize(); j++) {
                    Card card = tricks[i].getCard(j);
                    if (card.getSuit() == Card.SPADES && card.getNum() == Card.QUEEN) {
                        point += 13;
                    }
                    if (card.getSuit() == Card.HEARTS) {
                        point += 1;
                    }
                }

            }
        }
        return point;
    }
}













