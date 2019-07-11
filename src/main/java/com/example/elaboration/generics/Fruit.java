package com.example.elaboration.generics;

import java.io.Serializable;

public abstract class Fruit implements Serializable {
    public abstract boolean isPretty();
}


class Appel extends Fruit implements Schilbaar {
    @Override public boolean isPretty() {
        return false;
    }

    @Override public void schil() {
        System.out.println("lekker schillen...");
    }
}

class Peer extends Fruit {
    @Override public boolean isPretty() {
        return false;
    }
}

interface Schilbaar {
    void schil();
}

