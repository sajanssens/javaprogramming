package com.example.types;

import org.junit.jupiter.api.Test;

class ISBNTest {

    @Test
    void testIdentity() {
        ISBN of1 = ISBN.of("123");
        ISBN of2 = ISBN.of("123");
    }
}