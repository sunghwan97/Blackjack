package blackjack;
/* Deck.java
 */

public class Deck {
    final static int DECK_SIZE = 52; // Card's 1 set is 52.
    private Card[] cards; // Card Array variable declaration.
    private int N = 0; // Number of cards variable declaration.

    public Deck() { // Constructor initialization
        cards = new Card[DECK_SIZE]; // 
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[N] = new Card(N, j, i + "" + j + ".png");
                N++;
            }
        }
    }

    public Card dealFrom() { // Return the card.
        return cards[--N];
    }

    public boolean isEmpty() { // Check if deck is empty.
        return (N == 0);
    }

    public int size() { // Return the deck's size.
        return N;
    }

    public void shuffle() { // Shuffle the deck.
        for (int i = 0; i < N; i++) {
            int r = (int) (Math.random() * i);
            Card swap = cards[i];
            cards[i] = cards[r];
            cards[r] = swap;
        }
    }
}
