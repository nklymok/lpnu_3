package com.nklymok.fraction;

import java.math.BigInteger;

public class BigIntegerFraction implements Fraction {
    private final BigInteger numerator;
    private final BigInteger denominator;

    public static final Fraction ZERO = new BigIntegerFraction(BigInteger.ZERO, BigInteger.ONE);

    public BigIntegerFraction(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
    }

    @Override
    public Fraction add(Fraction fraction) {
        BigIntegerFraction other = (BigIntegerFraction) fraction;
        BigInteger newNumerator = this.numerator.multiply(other.denominator)
                .add(other.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(other.denominator);
        return new BigIntegerFraction(newNumerator, newDenominator);
    }

    @Override
    public String toString() {
        return numerator.toString() + "/" + denominator.toString();
    }
}
