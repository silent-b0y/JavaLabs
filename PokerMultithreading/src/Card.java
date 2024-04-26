public class Card {

    private final String suit;
    private final String value;

    Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.suit + " " + this.value;
    }

}
