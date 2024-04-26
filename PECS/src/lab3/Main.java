package lab3;

import lab3.chordates.mammals.insectivores.*;
import lab3.chordates.mammals.predatory.*;
import lab3.chordates.mammals.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test 1:");
        Collection<Mammals> srcCollection = List.of(new CommonHedgehog(), new CommonHedgehog(), new Manul(), new Manul(), new Lynx(), new Lynx());
        Collection<Hedgehogs> collection1 = new ArrayList<>();
        Collection<Feline> collection2 = new ArrayList<>();
        Collection<Predatory> collection3 = new ArrayList<>();
        segregate(srcCollection, collection1, collection2, collection3);
        print(srcCollection, collection1, collection2, collection3);
        System.out.println("Test 2:");
        Collection<Predatory> srcCollect = List.of(new Manul(), new Manul(), new Lynx(), new Lynx());
        Collection<Chordates> collect1 = new ArrayList<>();
        Collection<Manul> collect2 = new ArrayList<>();
        Collection<Feline> collect3 = new ArrayList<>();
        segregate(srcCollect, collect1, collect2, collect3);
        print(srcCollect, collect1, collect2, collect3);
        System.out.println("Test 3:");
        Collection<Hedgehogs> srcColl = List.of(new CommonHedgehog(), new CommonHedgehog());
        Collection<Insectivores> coll1 = new ArrayList<>();
        Collection<Predatory> coll2 = new ArrayList<>();
        Collection<Predatory> coll3 = new ArrayList<>();
        segregate(srcColl, coll1, coll2, coll3);
        print(srcColl, coll1, coll2, coll3);
    }

    public static void segregate(Collection<? extends Chordates> srcCollection,
                                 Collection<? super CommonHedgehog> collection1,
                                 Collection<? super Manul> collection2,
                                 Collection<? super Lynx> collection3) {
        for (Chordates animal: srcCollection) {
            Class<? extends Chordates> animalClass = animal.getClass();
            if (animalClass == CommonHedgehog.class) {
                collection1.add((CommonHedgehog) animal);
            } else if (animalClass == Manul.class) {
                collection2.add((Manul) animal);
            } else if (animalClass == Lynx.class) {
                collection3.add((Lynx) animal);
            }
        }
    }

    public static void print(Collection<? extends Chordates> srcCollection,
                             Collection<? super CommonHedgehog> collection1,
                             Collection<? super Manul> collection2,
                             Collection<? super Lynx> collection3) {
        System.out.println("Вся коллекция: " + srcCollection);
        System.out.println("Коллекция ежов: " + collection1);
        System.out.println("Коллекция манулов: " + collection2);
        System.out.println("Коллекция рысей: " + collection3);
    }
}
