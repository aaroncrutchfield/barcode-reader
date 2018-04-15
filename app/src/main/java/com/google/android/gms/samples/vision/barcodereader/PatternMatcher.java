package com.google.android.gms.samples.vision.barcodereader;

import java.util.regex.Pattern;

/**
 * Created by Aaron Crutchfield on 4/15/2018.
 */

public class PatternMatcher {

    public static boolean isMatchingPartPattern(String barcodeInput) {
        String partRegex = "^P((J|F)?\\d{5,6}(S|R)?(-[A-Z]-\\d{3})?)$|" +
                "^P((J|F)?\\d{5,6}(S|R)?)$";
        Pattern partPattern = Pattern.compile(partRegex);
        return partPattern.matcher(barcodeInput).matches();
    }

    public static boolean isMatchingFacPartPattern(String barcodeInput) {
        String partRegex = "^P((J)?\\d{5,6}(-[A-Z]-(000|600)))$";
        Pattern partPattern = Pattern.compile(partRegex);
        return partPattern.matcher(barcodeInput).matches();
    }

    public static boolean isMatchingQuantityPattern(String barcodeInput) {
        String quantityRegex = "^Q\\d{1,7}";
        Pattern partPattern = Pattern.compile(quantityRegex);
        return partPattern.matcher(barcodeInput).matches();
    }

    public static boolean isMatchingSerialPattern(String barcodeInput) {
        String quantityRegex = "^(JAC|S|1S|1SFA)\\d{5,10}";
        Pattern partPattern = Pattern.compile(quantityRegex);
        return partPattern.matcher(barcodeInput).matches();
    }
}
