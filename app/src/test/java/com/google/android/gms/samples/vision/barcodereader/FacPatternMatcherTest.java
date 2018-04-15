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
public class FacPatternMatcherTest {
    private String barcodeInput;

    public FacPatternMatcherTest(String barcodeInput) {
        this.barcodeInput = barcodeInput;
    }

    @Parameterized.Parameters
    public static Collection barcodeInputs() {
        return Arrays.asList(
                "P26280-A-600", // 5 digits w/dash 600 suffix
                "P26280-A-000",     // 5 digits w/dash 000 suffix
                "P308330-A-600",    // 6 digits w/dash 600 suffix
                "P308330-A-000"     // 6 digits w/dash 000 suffix
        );
    }

    @Test
    public void isMatchingFacPattern() throws Exception {

        boolean isMatching = PatternMatcher.isMatchingFacPartPattern(barcodeInput);

        System.out.println(barcodeInput + ": " + isMatching);
        assertTrue(isMatching);
    }

}