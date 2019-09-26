package com.example.app.interfaces;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Bakker bart = new Bakker();

        Kneedbaar k1 = new Klei();
        Kneedbaar k2 = new Deeg();
        Kneedbaar k3 = new Papier();

        bart.bak(List.of(k1, k2, k3));
    }

}
