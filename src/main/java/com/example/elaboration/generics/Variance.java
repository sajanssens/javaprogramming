package com.example.elaboration.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;

public class Variance {

    // The question here is: if we have a method which receives a generified bounded parameter,
    // what are the implications for:
    // - calling the method (what type can I pass as a parameter) and
    // - using the parameter inside the method (what can I do with it)

    // If A << B and List<A> << List<B>, we call a method covariant
    // If A << B and List<A> !< List<B>, we call a method contravariant

    // Note:
    // A <<  B means: type A is compatible with type B, e.g. Integer << Number, since aNumber = anInteger; is valid.
    // A !< B means: type A is NOT compatible with type B
    // Coll<A> << Coll<B> means: type Coll<A> is compatible with type Coll<B>
    // Coll<A> !< Coll<B> means: type Coll<A> is NOT compatible with type Coll<B>

    // Covariant means "with-different".
    // Contravariant means "against-different"

    public static void main(String[] args) {
        // allowed arguments: ArrayList with all T's that extend Number and Number itself == covariant to Number
        coVariant(new ArrayList<Number>());
        coVariant(new ArrayList<Integer>());
        coVariant(new ArrayList<Float>());
        // not allowed: T's super Number
        // coVariant(new ArrayList<Object>());

        // allowed arguments: ArrayList with all T's that are super Number and Number itself == contravariant to Number
        contraVariant(new ArrayList<Number>());
        contraVariant(new ArrayList<Object>());
        // not allowed: T's extend Number
        // contraVariant(new ArrayList<Integer>());
        // contraVariant(new ArrayList<Float>());

        // List<Number> numbers = new ArrayList<>();
        // List<Integer> integers = new ArrayList<>();
        // numbers = integers;
    }

    // co because here holds: Integer << Number, List<Integer> << List<Number>, i.e. I can pass a List of anything extends Number
    static void coVariant(List<? extends Number> list) { // remember: list is a *formal* parameter; actual parameter can be a List of any Number
        // You don't know what's exactly in list. Can be any Number, e.g. sometimes an Integer, then a Float, a Double, etc.
        Number number = list.get(0);// it's a Number, since that's the only thing we know for sure

        // disallowed are all additions, since the list instance can contain any specific subclass of Number
        //   T's extend Number and Number itself
        //   list.add(0);
        //   list.add(1L);
        //   list.add(2D);
        //   list.add(new AtomicInteger());
        //   list.add(new LongAccumulator((a, b) -> a + b, 1l));

        //   T's !extends Number
        //   list.add(new Number());
        //   list.add(new Object());
    }

    // contra because here holds: Integer << Number, List<Integer> !< List<Number>, i.e. although Integer << Number, I can NOT pass a List<Integer> to this method.
    static void contraVariant(List<? super Number> list) {
        Object object = list.get(0); // can be anything...

        // allowed are T's that extend Number and Number itself, since that's the only thing we know for sure:
        list.add(0);
        list.add(1L);
        list.add(2D);
        list.add(new AtomicInteger());
        list.add(new LongAccumulator(Long::sum, 1l));

        // disallowed are T's !extends Number, since the list instance can contain anything
        // list.add(new Object());
        //list.add("0");
    }

    static void coVariantForObject(List<?> list) {
        Object o = list.get(0);

        // additions not allowed:
        // list.add(1);
        // list.add("1");
        // list.add(new Object());
    }

    static void coVariant(BakjeGeneric<? extends Number> b) { // b can be BakjeGeneric<Number>, BakjeGeneric<Integer>, BakjeGeneric<Double>, ..., anything extends Number.
        Number inhoud = b.getInhoud();

        // disallowed are all additions, since b can contain any specific subclass of Number; you don't know which
        // b.setInhoud(new Object());
        // b.setInhoud(1);
        // ...

    }

    static void contraVariant(BakjeGeneric<? super Number> b) { // b can be either BakjeGeneric<Number> or BakjeGeneric<Object>
        Object inhoud = b.getInhoud();

        // b.setInhoud(new Object()); not allowed, because b could be a BakjeGeneric<Number>
        b.setInhoud(0.1d); // allowed
    }
}
