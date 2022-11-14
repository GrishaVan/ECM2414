
public class Card {

    private int value;

    /**
     * Function to get the value of the crad
     * 
     * @return the int value of the card
     */
    public int getValue() {
        return this.value;
    }

    /**
     * To string method that returns info about the card object
     * 
     * @return a string with the value of the card
     */
    public String toString() {
        return "value: " + getValue();
    }

    /**
     * Card class object constructor 
     * 
     * @param value int value of the crad
     */
    public Card(int value) {
        this.value = value;
    }
}