package com.google.android.gms.samples.vision.barcodereader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Tests the partnumber regex against strings to ensure the desired output
 */
@RunWith(Parameterized.class)
public class QuantityPatternMatcherTest {
    private String barcodeInput;

    public QuantityPatternMatcherTest(String barcodeInput) {
        this.barcodeInput = barcodeInput;
    }

    @Parameterized.Parameters
    public static Collection barcodeInputs() {
        return Arrays.asList(
                "Q80",      // 2 digits
                "Q0000080",     // 7 digits w/leading zeros
                "Q00080",       // 5 digits w/leading zeros
                "Q1234567");     // 7 digits
    }

    @Test
    public void isMatchingQuantityPattern() throws Exception {

        boolean isMatching = PatternMatcher.isMatchingQuantityPattern(barcodeInput);

        System.out.println(barcodeInput + ": " + isMatching);
        assertTrue(isMatching);
    }

}
