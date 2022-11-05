public class Card {
    
    private int value;

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return "This card has a vlue of" + getValue();
    }

    public Card(int value) {
        this.value = value;
    }
}