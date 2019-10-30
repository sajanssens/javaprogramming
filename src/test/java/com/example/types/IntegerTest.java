package com.example.types;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegerTest {

    @Test
    public void whenIPlusPlusThenIIsIncrementedAndReturnValueIsOldValue() {
        // Als:
        // int i = 4
        // int antwoord = i++;

        // ... dan moet hierna:
        // i 5
        // antwoord 4 zijn

        // given
        Integer i = new Integer(4);

        // when
        int antwoord = i.plusplus();

        // then
        assertEquals(i.getValue(), 5);
        assertEquals(antwoord, 4);
    }

    @Test
    public void whenPlusPlusIThenIIsIncrementedAndReturnValueIsNewValue() {
        // Als:
        // int i = 4
        // int antwoord = ++i;

        // ... dan moet hierna:
        // i 5 en
        // antwoord 5 zijn

        // given
        Integer i = new Integer(4);

        // when
        int antwoord = i.plusplusFirst();

        // then
        assertEquals(i.getValue(), 5);
        assertEquals(antwoord, 5);
    }
}