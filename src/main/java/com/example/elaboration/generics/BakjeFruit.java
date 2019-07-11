package com.example.elaboration.generics;

public class BakjeFruit<T extends Fruit> extends BakjeGeneric<T> { // means: you can call this class with a type 'is-a Fruit'.
    T fruit;

    public BakjeFruit(T inhoud) {
        super(inhoud);
    }

    boolean isMooi() { return fruit.isPretty(); }
}
