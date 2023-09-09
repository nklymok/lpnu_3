package com.nklymok.harmonic_series;

import com.nklymok.fraction.BigIntegerFraction;
import com.nklymok.fraction.Fraction;
import com.nklymok.fraction.IntFraction;

import java.math.BigInteger;

public class BigIntegerHarmonicSeries extends HarmonicSeries {

    public BigIntegerHarmonicSeries(int stepCount) {
        super(stepCount);
    }

    @Override
    public Fraction calculate() {
        Fraction sum = BigIntegerFraction.ZERO;
        for (int i = 1; i <= this.getStepCount(); i++) {
            sum = sum.add(new BigIntegerFraction(BigInteger.ONE, BigInteger.valueOf(i)));
        }
        return sum;
    }
}
