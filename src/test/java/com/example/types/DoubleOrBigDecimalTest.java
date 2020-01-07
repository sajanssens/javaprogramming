package com.example.types;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.example.types.DoubleOrBigDecimal.*;
import static java.lang.Integer.toBinaryString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DoubleOrBigDecimalTest {

    @Test
    void testSubtract() {
        System.out.println(1.00d); // Most accurate representation = 1.0E0
        System.out.println(0.41d); // Most accurate representation = 4.09999999999999975575093458247E-1

        double subtractWrong = subtractWrong(1.00, 0.41);// Actually, this is: 1.0 - 0.409999... = 0.5900000000000001
        double subtractRight = subtractRight("1.00", "0.41");

        System.out.println(subtractWrong);
        System.out.println(subtractRight);

        assertNotEquals(subtractWrong, 0.59);
        assertEquals(subtractRight, 0.59);
    }

    @Test
    void testMultiply() {
        // 0.2: Most accurate representation = 2.00000000000000011102230246252E-1
        double f = 0.200000000000000011102230246252f;
        System.out.println("float     " + f); // float has less precision
        double d = 0.200000000000000011102230246252d;
        System.out.println("double    " + d); // when doing toString, d is boxed into Double and then toString is called; see javadoc of Double.toString why only x decimals are printed: "There must be at least one digit to represent the fractional part, and beyond that as many, but only as many, more digits as are needed to uniquely distinguish the argument value from adjacent values of type double."

        System.out.println("0.2 * 3 = " + 0.2 * 3); // Actually this is 3 * 2.00000000000000011102230246252E-1 = ‭0,600000000000000033306690738756
        System.out.println("0.2 * 5 = " + 0.2 * 5); // Actually this is 5 * 2.00000000000000011102230246252E-1 = ‭‭1,00000000000000005551115123126‬

        double multiplyWrong = multiplyWrong(0.10, 100);
        System.out.println("multiplyWrong = " + multiplyWrong);

        double multiplyRight = multiplyRight(0.10, 100);
        System.out.println("multiplyRight = " + multiplyRight);

        assertNotEquals(multiplyWrong, 10.00d);
        assertEquals(multiplyRight, 10.00d);
    }

    @Test
    void printSpecialDoubles() {
        printBits("Double.NaN:               ", Double.NaN);
        printBits("Double.POSITIVE_INFINITY: ", Double.POSITIVE_INFINITY);
        printBits("Double.NEGATIVE_INFINITY: ", Double.NEGATIVE_INFINITY);
        printBits("Double.MAX_VALUE:         ", Double.MAX_VALUE);
        printBits("Double.MIN_VALUE:         ", Double.MIN_VALUE);
        printBits("Positive zero:            ", 0.0d);// Positive zero = all bits 0.
        printBits("Negative zero:            ", -0.0d);// Negative zero = all bits 0, except sign bit which is 1.
        printBits("0.1 (binary):             ", 0.1d);
        System.out.println();
        System.out.println("0.1 (decimal):            " + new BigDecimal(0.1));
        System.out.println("Double.MAX_EXPONENT:      " + Double.MAX_EXPONENT);
        System.out.println("Double.MIN_EXPONENT:      " + Double.MIN_EXPONENT);
    }

    @Test
    void printAllPossiblePositiveValuesWith8BitFloat() {
        // prints all possible positive values for an 8 bits floating point number, where:

        // s | exp  | mantissa
        // _ | ____ | ___

        // exp is four bits long (-7 .. 8)
        // mantissa is three bits long (0 .. 7); each bit represents a 1/8th fraction of the exponent

        // example: 0|011|0010 = +|3|2  = 1.011 * 2^(2-7)  = 1.011 * 2^-5 = (1+3/8) * 1/32 = ...
        // example: 0|101|1100 = +|5|10 = 1.101 * 2^(10-7) = 1.101 * 2^3  = (1+5/8) * 8    = ...

        printHeader();

        for (byte m = 0; m <= 7; m++) {
            System.out.print("m=" + binaryString(m, 3) + " |");
            for (int e = -7; e <= 8; e++) {
                double r = (1 + (double) m * 1 / 8) * Math.pow(2, e);
                System.out.print(String.format("%15s | ", r));
            }
            System.out.println();
        }

        // As you can see, the range is from 0.0078125 (1.000*2^-7) to 480 (1.111*2^8).
        // There are only 128 positive possibilities (idem for negative).
        // Any literal not equal to one of these possibilities will be rounded to the nearest possibility.
        // The values in each colomn increase linearly
        // Horizontally, the values increase by a factor of 2
        // Anomalies:
        // The number 0 is not possible!
        // The numbers 0.1, 0.01 are not possible! Not suited for money.
        // Also, the distribution is pretty unfair:
        //  * 7:16 (44%) is between 0..1
        //  * 9:16 (56%) is between 1..480
    }

    private void printBits(String s, double d) {
        String bits = String.format("%64s", Long.toBinaryString(Double.doubleToRawLongBits(d))).replace(' ', '0');
        String sign = bits.substring(0, 1);
        String expo = bits.substring(1, 13);
        String mant = bits.substring(13, 64);
        System.out.println(s + sign + "|" + expo + "|" + mant);
    }

    private void printHeader() {
        System.out.print(String.format("%7s", "   |"));
        for (int e = -7; e <= 8; e++) {
            System.out.print(String.format("%15s | ", e));
        }
        System.out.println();

        System.out.print(String.format("%7s", "e= |"));
        for (int e = -7; e <= 8; e++) {
            System.out.print(String.format("%15s | ", binaryString((byte) (e + 7), 4)));
        }

        System.out.println();
        for (int i = 0; i < 16 * 18 + 6; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private String binaryString(byte m, int width) {
        return String.format("%" + width + "s", toBinaryString(m & 0xFF)).replace(' ', '0');
    }
}