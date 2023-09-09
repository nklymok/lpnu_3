package com.nklymok.fraction;

import com.nklymok.util.GCDUtil;

public class IntFraction implements Fraction {

    private final int numerator;
    private final int denominator;

    public static final Fraction ZERO = new IntFraction(0, 1);

    public IntFraction(int numerator, int denominator) {
        int gcd = GCDUtil.gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    @Override
    public Fraction add(Fraction fraction) {
        IntFraction other = (IntFraction) fraction;
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new IntFraction(newNumerator, newDenominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
