package blackjack;
/* Player.java
 */

public class Player {  
    final static int MAX_CARDS = 52; // Total number of cards 
    public Card[] cards = new Card[MAX_CARDS]; // Declare a card class by the number of cards
    private int N; // Number of cards player has
    private String name; // Name of player

    public Player(String name) { // Constructor initialization
        this.setName(name);
        N = 0;
    }

    public int inHand() { // Return the number of cards player has
        return N;
    }

    public Card dealTo(Card c) { // When getting a card and return that card
        cards[N++] = c;
        return c;
    }

    public void reset() { // When player restarts the game
        N = 0;
    }

    public int value() { //Return the Player's Card Sum
        int val = 0;
        boolean hasAce = false;
        for (int i = 0; i < N; i++) {
            val = val + cards[i].rank();
            if (cards[i].isAce()) { // When player has a 'ace' card
                hasAce = true;
            }
        }
        if (hasAce && (val <= 11)) { // Calculate when player has a 'ace(1,11)' card
            val = val + 10;
        }
        return val;
    }

	public void setName(String name) { // Return the name
		this.name = name;
	}
}

