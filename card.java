package blackjack;
/* Card.java
 */

public class Card {
    private int cardNumber; // // Number of cards
    private int rank; // Card's number
    private String front; // Card image file's name

    Card(int cardNumber, int rank, String front) { // Constructor initialization
        this.cardNumber = cardNumber;
        this.rank = rank;
        this.front = front;
    }

    public boolean isAce() { // Check if card is ace.
        return rank == 0;
    }

    public int rank() { // Return the value of card.
        if (rank == 0) {
            return 1;
        }
        if (rank >= 9) { // If card is J or Q or K, value is 10. 
            return 10;
        }
        return rank + 1;
    }

    public String toString() { // Return the front variable.
        return this.front;
    }

	public int getCardNumber() { // Return the cardNumber variable.
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) { // Set the cardNumber variable.
		this.cardNumber = cardNumber;
	}
}
