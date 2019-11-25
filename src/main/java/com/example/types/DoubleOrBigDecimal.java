package com.example.types;

import java.math.BigDecimal;

public class DoubleOrBigDecimal {

    static double subtractWrong(double d1, double d2) {
        return d1 - d2;
    }

    static double subtractRight(String d1, String d2) {
        return new BigDecimal(d1).subtract(new BigDecimal(d2)).doubleValue();
    }

    static double multiplyWrong(double x, int times) {
        double total = 0.0;
        for (int i = 0; i < times; i++) {
            total += x;
        }
        return total;
    }

    static double multiplyRight(double x, int times) {
        return new BigDecimal(x /*+ ""*/).multiply(new BigDecimal(times /*+ ""*/)).doubleValue();
    }

}
