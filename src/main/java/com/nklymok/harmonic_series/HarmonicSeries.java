package com.nklymok.harmonic_series;

import com.nklymok.fraction.Fraction;

public abstract class HarmonicSeries {
    private final int stepCount;

    public HarmonicSeries(int stepCount) {
        this.stepCount = stepCount;
    }

    public abstract Fraction calculate();

    public int getStepCount() {
        return stepCount;
    }
}
