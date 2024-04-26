import java.util.concurrent.Phaser;

public class PlayerPhases implements Runnable {
    private final Phaser phaser;
    private final Player player;
    private final Table table;

    PlayerPhases(Phaser phaser, Player player, Table table) {
        this.phaser = phaser;
        phaser.register();
        this.player = player;
        this.table = table;
    }

    @Override
    public void run() {
        player.reset();
        table.giveCardsToPlayer(player);
        phaser.arriveAndAwaitAdvance(); //РАЗДАЛИ КАРТЫ

        int set = player.set();
        table.acceptSet(set);
        System.out.printf("%10s %1s\n", player.getName() + ":", "sets " + set + '$');
        phaser.arriveAndAwaitAdvance(); //СДЕЛАЛИ СТАВКИ 1

        phaser.arriveAndAwaitAdvance(); //ПОЛОЖИЛИ 3 КАРТЫ НА СТОЛ

        set = player.set();
        table.acceptSet(set);
        System.out.printf("%10s %1s\n", player.getName() + ":", "sets " + set + '$');
        phaser.arriveAndAwaitAdvance(); //СДЕЛАЛИ СТАВКИ 2

        phaser.arriveAndAwaitAdvance(); //ПОЛОЖИЛИ 4 КАРТЫ НА СТОЛ

        set = player.set();
        table.acceptSet(set);
        System.out.printf("%10s %1s\n", player.getName() + ":", "sets " + set + '$');
        phaser.arriveAndAwaitAdvance(); //СДЕЛАЛИ СТАВКИ 3

        phaser.arriveAndAwaitAdvance(); //ПОЛОЖИЛИ 5 КАРТ НА СТОЛ

        set = player.set();
        table.acceptSet(set);
        System.out.printf("%10s %1s\n", player.getName() + ":", "sets " + set + '$');
        phaser.arriveAndAwaitAdvance(); //СДЕЛАЛИ СТАВКИ 4

        phaser.arriveAndAwaitAdvance(); //ОПРЕДЕЛИЛИ ПОБЕДИТЕЛЯ

        System.out.printf("%9s %7s %1s\n", player.getName(), player.getResult(), player.getBalance() + "$");
        phaser.arriveAndDeregister(); //ОБЪЯВИЛИ РЕЗУЛЬТАТЫ
    }
}
