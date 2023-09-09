package com.nklymok.harmonic_series;

import com.nklymok.fraction.Fraction;
import com.nklymok.fraction.IntFraction;

public class IntHarmonicSeries extends HarmonicSeries {

    public IntHarmonicSeries(int stepCount) {
        super(stepCount);
    }

    @Override
    public Fraction calculate() {
        Fraction sum = IntFraction.ZERO;
        for (int i = 1; i <= this.getStepCount(); i++) {
            sum = sum.add(new IntFraction(1, i));
        }
        return sum;
    }
}
