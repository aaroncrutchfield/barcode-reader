package com.google.android.gms.samples.vision.barcodereader.data;



/**
 * Created by ioutd on 12/1/2017.
 */

public class Part {
    private String serial;
    private String partnumber;
    private String quantity;

    public Part(){
    }

    public Part(String serial, String partnumber, String quantity){
        this.serial = serial;
        this.partnumber = partnumber;
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPartnumber() {
        return partnumber;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
