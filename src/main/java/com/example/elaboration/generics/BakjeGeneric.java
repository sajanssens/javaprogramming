package com.example.elaboration.generics;

public class BakjeGeneric<T> {
    T inhoud;

    public BakjeGeneric() { }

    public BakjeGeneric(T inhoud) {
        this.inhoud = inhoud;
    }

    public T getInhoud() { return inhoud; }

    public void setInhoud(T inhoud) {
        this.inhoud = inhoud;
    }
}
