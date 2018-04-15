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
public class PartPatternMatcherTest {
    private String barcodeInput;

    public PartPatternMatcherTest(String barcodeInput) {
        this.barcodeInput = barcodeInput;
    }

    @Parameterized.Parameters
    public static Collection barcodeInputs() {
        return Arrays.asList(
                 "P26280",      // 5 digits
                 "P26280-A-030",    // 5 digits w/dash suffix
                 "P308330",         // 6 digits
                 "P308330-A-030",   // 6 digits w/dash suffix
                 "PF26647",         // 5 digits w/'F' prefix
                 "P16348S",         // 5 digits w/'S' suffix
                 "P16348S-A-030",   // 5 digits w/'S' + dash suffix
                 "P26280R");        // 5 digits w/'R' suffix
    }

    @Test
    public void isMatchingPartPattern() throws Exception {

        boolean isMatching = PatternMatcher.isMatchingPartPattern(barcodeInput);

        System.out.println(barcodeInput + ": " + isMatching);
        assertTrue(isMatching);
    }

}