package com.example.elaboration.generics;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();

        // BASICS -------------------
        //      Arrays
        Object[] objectArray = new Integer[3];
        objectArray[0] = 1;
        objectArray[1] = "2"; // allowed: no compile time check... but runtimeexception...! :-(
        Object o = objectArray[0]; // type unknown... casting required :-(

        //      Collections not generic
        List objectList = new ArrayList();
        objectList.add(1);
        objectList.add("2"); // allowed: li would contain a String instead of Integer; no runtimeexception...
        Object o1 = objectList.get(0); // type unknown... casting required :-(
        Object o2 = objectList.get(1);

        //      Collections generic
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        // intList.add("2"); // not allowed :-) : li would contain a String instead of Integer
        // List<Object> objectList = intList; // idem :-)
        Integer integer = intList.get(0); // no casting required :-)

        // Not allowed:
        // List<Number> numbers = new ArrayList<Integer>(); // left and right of = should be equal
        // Allowed:
        List<Number> numbers = new ArrayList<Number>(); // left and right of = should be equal; use diamond.

        // Type parameters -------------------
        //      On class with bounds

        // Allowed
        Data<Integer> integerData = new Data<Integer>(); // left and right of = are equal and Integer is-a Number
        Data<Float> floatData = new Data<>(); // Float is-a Number; diamond used: types are equal by definition

        // Not allowed
        // Data<Number> di  = new Data<Integer>(); // though Integer is-a Number T left and T right of = should be equal
        // Data<Object> dob = new Data<Integer>(); // though Number is-an Object
        // Data<String> dos = new Data<String>(); // String is NaN
        // Data<String> dst = new Data<Integer>();

        //      On method with bounds
        m.print1(new ArrayList());
        m.print1(new ArrayList<>());
        m.print1(new ArrayList<Object>());
        m.print1(new ArrayList<Integer>());
        m.print1(new ArrayList<Peer>());

        m.print2(new ArrayList());
        m.print2(new ArrayList<>());
        m.print2(new ArrayList<Object>());
        m.print2(new ArrayList<Integer>());
        m.print2(new ArrayList<Peer>());

        m.print3(new ArrayList());
        m.print3(new ArrayList<>());
        m.print3(new ArrayList<Object>());
        m.print3(new ArrayList<Integer>());
        m.print3(new ArrayList<Peer>());

        // not allowed: must be ArrayList<Fruit>
        // m.printIsPretty1(new ArrayList<Peer>());

        // allowed: can be a List of any kind of Fruit
        m.printIsPretty1(new ArrayList<Fruit>());
        m.printIsPretty2(new ArrayList<Peer>());
        m.printIsPretty2(new ArrayList<Fruit>());
        m.printIsPretty3(new ArrayList<Peer>());
        m.printIsPretty3(new ArrayList<Fruit>());

        m.addIfPretty(new ArrayList<Appel>(), new Appel()); // allowed: T is the same
        // m.addIfPretty(new ArrayList<Peer>(), new Appel()); // not allowed; only one and the same generic type T allowed

        // m.schilFruit1(new ArrayList<Appel>()); // not allowed: List<Schilbaar> != ArrayList<Fruit> :-(
        m.schilFruit2(new ArrayList<Appel>()); // allowed :-)
        // m.schilFruit2(new ArrayList<Peer>()); // not allowed :-)

        Bakje b = new Bakje("Hallo!");


        BakjeInteger bi = new BakjeInteger();
        bi.setInhoud(1);
        Integer inhoud3 = bi.getInhoud();
        BakjeString bakjeString = new BakjeString();
        bakjeString.setInhoud("aaa");
        String inhoud4 = bakjeString.getInhoud();





        Object inhoud1 = b.getInhoud();
        b.setInhoud(1);
        Object inhoud2 = b.getInhoud();
        if (inhoud2 instanceof Integer) {
            Integer i = (Integer) inhoud2;
            int y = i + 9;
        }

        BakjeGeneric<String> bakjeGeneric = new BakjeGeneric<>("Hallo!");
        String inhoud = bakjeGeneric.getInhoud();
        // bakjeGeneric.setInhoud(1); // not allowed

        BakjeGeneric<Integer> integerBakjeGeneric = new BakjeGeneric<>(1);
        integerBakjeGeneric.setInhoud(2);
        Integer inhoud5 = integerBakjeGeneric.getInhoud();

        BakjeFruit<Appel> bakjeFruit = new BakjeFruit<>(new Appel());
        Appel appel = bakjeFruit.getInhoud();
        boolean mooi = bakjeFruit.isMooi();
    }

    public <T> void print1(List<T> objects) { // T can be anything, so Object
        for (T object : objects) {
            // only methods from Object allowed
        }
    }

    public void print2(List<?> objects) { // ? can be anything, so Object (same as above, without explicit type parameter declaration)
        for (Object object : objects) {
            // only methods from Object allowed
        }
    }

    public void print3(List objects) { // contents can be anything, so Object (same as above, without wildcard)
        for (Object object : objects) {
            // only methods from Object allowed
        }
    }

    public void printIsPretty1(List<Fruit> fruits) { // must be List<Fruit> and nothing else
        for (Fruit fruit : fruits) {
            // methods from Fruit and below allowed
            System.out.println(fruit.isPretty());
        }
    }

    public <T extends Fruit> void printIsPretty2(List<T> fruits) { // equivalent with below, but T is declared and therefore reusable
        for (T fruit : fruits) {
            System.out.println(fruit.isPretty());
        }
    }

    public void printIsPretty3(List<? extends Fruit> fruits) { // ? must be any kind of Fruit
        for (Fruit fruit : fruits) {
            System.out.println(fruit.isPretty());
        }
    }

    // Here we reuse T, so we have to declare it:
    public <T extends Fruit> void addIfPretty(List<T> fruits, T fruit) {// T must be any kind of Fruit
        if (fruit.isPretty()) {
            fruits.add(fruit);
        }
    }

    public void schilFruit1(List<Schilbaar> list) { // not very useful this way
        for (Schilbaar sb : list) {
            sb.schil();
        }
    }

    public void schilFruit2(List<? extends Schilbaar> list) { // useful
        for (Schilbaar sb : list) {
            sb.schil();
        }
    }


}
