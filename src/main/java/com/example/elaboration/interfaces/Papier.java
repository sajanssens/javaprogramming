package com.example.elaboration.interfaces;

public class Papier implements Kneedbaar, Brandbaar {
    @Override public void kneed() {
        System.out.println("Papier wordt een prop als je het kneedt.");
    }

    @Override public void brand() {

    }
}
