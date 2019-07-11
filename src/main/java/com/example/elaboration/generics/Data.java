package com.example.generics;

public class Data<T extends Number> { // means: you can call this class with a type 'is-a Number'. So Data<...> where ... is a number (i.e. Number, Integer).
    T t;
}
