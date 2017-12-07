package com.google.android.gms.samples.vision.barcodereader.data;




/**
 * Created by ioutd on 12/1/2017.
 */

public class Part {
    private String serial;
    private String partnumber;
    private int quantity;

    private Part(Builder builder) {
        serial = builder.serial;
        partnumber = builder.partnumber;
        quantity = builder.quantity;
    }

    public String getSerial() {
        return serial;
    }

    public String getPartnumber() {
        return partnumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public static final class Builder {
        private String serial;
        private String partnumber;
        private int quantity;

        public Builder() {
        }

        public Builder serial(String val) {
            serial = val;
            return this;
        }

        public Builder partnumber(String val) {
            partnumber = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Part build() {
            return new Part(this);
        }

    }
}
