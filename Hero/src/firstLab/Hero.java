package firstLab;

import firstLab.moveStrategy.MoveStrategy;

public class Hero {
    private MoveStrategy strategy;
    Hero(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(MoveStrategy strategy) {
        this.strategy = strategy;
    }
    public void move() {
        strategy.move();
    }
}
