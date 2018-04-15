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
public class SerialPatternMatcherTest {
    private String barcodeInput;

    public SerialPatternMatcherTest(String barcodeInput) {
        this.barcodeInput = barcodeInput;
    }

    @Parameterized.Parameters
    public static Collection barcodeInputs() {
        return Arrays.asList(
                "JAC1234567890",     // JAC + 10 digits
                "JAC12345",              // JAC + 5 digits
                "S1234567890",           // S + 10 digits
                "S12345",                // S + 5 digits
                "1S1234567890",          // 1S + 10 digits
                "1S12345",               // 1S + 5 digits
                "1SFA1234567890",        // 1SFA + 10 digits
                "1SFA12345");            // 1SFA + 5 digits
    }

    @Test
    public void isMatchingSerialPattern() throws Exception {

        boolean isMatching = PatternMatcher.isMatchingSerialPattern(barcodeInput);

        System.out.println(barcodeInput + ": " + isMatching);
        assertTrue(isMatching);
    }

}