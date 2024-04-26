import java.util.Random;

public class Player {
    private final String name;
    private int balance = 1000;
    private String result = "PLAYING";
    private Card[] cards;

    Player(String name) {
        this.name = name;
    }
    void win(int bank) {
        balance += bank;
        result = "WINNER";
    }

    void wasted() {
        result = "WASTED";
    }

    void reset() {
        result = "PLAYING";
    }

    int set() {
        Random random = new Random();
        int set = random.nextInt(500);
        if (set > balance) {
            set = balance;
        }
        balance -= set;
        return set;
    }

    void setCards(Card[] cards) {
        this.cards = cards;
    }

    int getBalance() {
        return balance;
    }

    String getName() {
        return name;
    }

    String getResult() {
        return result;
    }
}
