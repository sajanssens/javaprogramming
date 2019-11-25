package com.example.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void testEquals() {
        Book a1 = new Book(1, "A");
        Book a2 = new Book(2, "A");
        Book b = new Book(3, "B");

        assertNotEquals(a1, a2);
        assertNotEquals(a1, b);
        assertNotEquals(a2, b);
    }
}