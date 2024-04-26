package firstLab;

import firstLab.moveStrategy.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Choose move strategy:");
        System.out.println("Type \"1\" to choose Foot");
        System.out.println("Type \"2\" to choose Horse");
        System.out.println("Type \"3\" to choose Fly");
        System.out.println("Type \"0\" to exit");
        Scanner scanner = new Scanner(System.in);
        Hero hero = new Hero(null);
        String type = scanner.nextLine();
        while (!type.equals("0")) {
            if (type.equals("1")) {
                hero.setStrategy(new Foot());
                hero.move();
            } else if (type.equals("2")) {
                hero.setStrategy(new Horse());
                hero.move();
            } else if (type.equals("3")) {
                hero.setStrategy(new Fly());
                hero.move();
            } else {
                System.out.println("Wrong input");
            }
            type = scanner.nextLine();
        }
    }
}