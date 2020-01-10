package com.project.currency.arbs;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ProcessorTest {

    @Test
    void calculateArbPositive() {
        Processor.Pair positivePair = Processor.getInstance().new Pair("exch1", "exch2", 100f, 90f);
        Assert.assertTrue(Processor.getInstance().calculateArb(positivePair) > 0);
    }

    @Test
    void calculateArbNegative() {
        Processor.Pair negativePair = Processor.getInstance().new Pair("exch1", "exch2", 90f, 100f);
        Assert.assertTrue(Processor.getInstance().calculateArb(negativePair) < 0);
    }

    @Test
    void calculateArbNeutral() {
        Processor.Pair neutralPair = Processor.getInstance().new Pair("exch1", "exch2", 100f, 100f);
        Assert.assertTrue(Processor.getInstance().calculateArb(neutralPair) == 0);
    }
}