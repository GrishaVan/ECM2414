
public class Card {

    private int value;

    /**
     * Get card value
     * 
     * @return the card value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * To string all the card information
     * 
     * @return string with the cards value
     */
    public String toString() {
        return "value: " + getValue();
    }

    /**
     * Crad class constructor
     * 
     * @param value the value of the card
     */
    public Card(int value) {
        this.value = value;
    }
}