package com.example.types;

public class Integer {

    private int value;

    public Integer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // i++
    public int plusplus() {
        int oldValue = value;

        value = value + 1; // side effect
        return oldValue; // return  value
    }

    // ++i
    public int plusplusFirst() {
        value = value + 1; // side effect
        return value; // return  value
    }
}
