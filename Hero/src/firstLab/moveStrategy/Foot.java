package firstLab.moveStrategy;

public class Foot implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("Walking on foot");
    }
}
