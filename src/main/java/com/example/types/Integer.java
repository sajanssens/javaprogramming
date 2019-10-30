package com.example.types;

public class Integer {

    private int value;

    public Integer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int plusplus() {
        int oldValue = value;

        value = value + 1; // side effect
        return oldValue; // return  value
    }

    public int plusplusFirst() {
        int oldValue = value;

        value = value + 1; // side effect
        return oldValue + 1; // return  value
    }
}
