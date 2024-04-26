import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

public class Table {
    private final AtomicInteger bank = new AtomicInteger(0);
    private int activePlayers = 0;
    private int currentRound = 1;
    private final ArrayList<Player> players = new ArrayList<>();
    private final ConcurrentHashMap<Card, Boolean> deck = new ConcurrentHashMap<>();
    private final Phaser phaser = new Phaser();
    private static final Random random = new Random();
    private static final String[] suits = new String[] {"Hearts", "Spades", "Diamonds", "Clubs"};
    private static final String[] values = new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public static void  main(String[] args) {
        Table table = new Table();
        table.start();
    }

    public void start() {
        System.out.println("How many players are going to play poker?");
        Scanner scanner = new Scanner(System.in);
        int n;
        while (true) {
            String nPlayers = scanner.next();
            try {
                n = Integer.parseInt(nPlayers);
            } catch (NumberFormatException e) {
                System.err.println("Wrong input, enter a number from 2 to 10!");
                continue;
            }
            if (2 <= n && n <= 10)
                break;
            else
                System.err.println("Wrong input, enter a number from 2 to 10!");
        }
        activePlayers = n;
        phaser.register();

        System.out.println("WELCOME TO POLYTECH POKER!!!");
        for (int i = 0; i < activePlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
        }
        while (activePlayers != 1) {
            System.out.println("ROUND " + (currentRound++) + ":");
            if (deck.size() == 0) {
                for (String suit : suits) {
                    for (String value : values) {
                        deck.put(new Card(suit, value), false);
                    }
                }
            } else {
                deck.replaceAll((card, value) -> false);
            }
            for (int i = 0; i < activePlayers; i++) {
                new Thread(new PlayerPhases(phaser, players.get(i), this)).start();
            }
            phaser.arriveAndAwaitAdvance(); //РАЗДАЛИ КАРТЫ

            System.out.println("MAKE YOUR BETS!!!");
            phaser.arriveAndAwaitAdvance(); //СДЕЛАЛИ СТАВКИ 1

            System.out.println("BANK: " + bank);
            Object[] cards = deck.keySet().toArray();
            System.out.println("1:");
            for (int i = 0; i < 3; i++) {
                boolean gotCard = false;
                while (!gotCard) {
                    Card card = (Card) cards[random.nextInt(52)];
                    if (!deck.get(card)) {
                        deck.put(card, true);
                        gotCard = true;
                        System.out.printf("%11s\n", card);
                    }
                }
            }
            phaser.arriveAndAwaitAdvance(); //ПОЛОЖИЛИ 3 КАРТЫ НА СТОЛ

            System.out.println("MAKE YOUR BETS!!!");
            phaser.arriveAndAwaitAdvance(); //СДЕЛАЛИ СТАВКИ 2

            System.out.println("BANK: " + bank);
            System.out.println("2:");
            boolean gotCard = false;
            while (!gotCard) {
                Card card = (Card) cards[random.nextInt(52)];
                if (!deck.get(card)) {
                    deck.put(card, true);
                    gotCard = true;
                    System.out.printf("%11s\n", card);
                }
            }
            phaser.arriveAndAwaitAdvance(); //ПОЛОЖИЛИ 4 КАРТЫ НА СТОЛ

            System.out.println("MAKE YOUR BETS!!!");
            phaser.arriveAndAwaitAdvance(); //СДЕЛАЛИ СТАВКИ 3

            System.out.println("BANK: " + bank);
            System.out.println("3:");
            gotCard = false;
            while (!gotCard) {
                Card card = (Card) cards[random.nextInt(52)];
                if (!deck.get(card)) {
                    deck.put(card, true);
                    gotCard = true;
                    System.out.printf("%11s\n", card);
                }
            }
            phaser.arriveAndAwaitAdvance(); //ПОЛОЖИЛИ 5 КАРТ НА СТОЛ

            System.out.println("MAKE YOUR BETS!!!");
            phaser.arriveAndAwaitAdvance(); //СДЕЛАЛИ СТАВКИ 4

            System.out.println("BANK: " + bank);
            Player winner = players.get(random.nextInt(activePlayers));
            winner.win(bank.get());
            int counter = 0;
            for (int i = 0; i < activePlayers; i++) {
                if (players.get(i - counter).getBalance() == 0) {
                    players.get(i - counter).wasted();
                    players.remove(i - counter);
                    counter++;
                }
            }
            activePlayers -= counter;
            bank.set(0);
            System.out.println("RESULTS:");
            phaser.arriveAndAwaitAdvance(); //ОПРЕДЕЛИЛИ ПОБЕДИТЕЛЯ

            phaser.arriveAndAwaitAdvance(); //ОБЪЯВИЛИ РЕЗУЛЬТАТЫ
        }
    }

    public void giveCardsToPlayer(Player player) {
        Object[] cards = deck.keySet().toArray();
        Card[] playerCards = new Card[2];
        for (int i = 0; i < 2; i++) {
            boolean gotCard = false;
            while (!gotCard) {
                Card card = (Card) cards[random.nextInt(52)];
                if (!deck.get(card)) {
                    deck.put(card, true);
                    gotCard = true;
                    playerCards[i] = card;
                }
            }
        }
        System.out.printf("%10s %11s %11s\n", player.getName() + ":", playerCards[0], playerCards[1]);
        player.setCards(playerCards);
    }

    public void acceptSet(int set) {
        bank.addAndGet(set);
    }
}