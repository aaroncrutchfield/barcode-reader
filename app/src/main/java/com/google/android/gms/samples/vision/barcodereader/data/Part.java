package com.google.android.gms.samples.vision.barcodereader.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.support.annotation.NonNull;

/**
 * Created by ioutd on 12/1/2017.
 */

@Entity
public class Part {
    @PrimaryKey
    @NonNull
    private String serial;

    @ColumnInfo(name = "partnumber")
    private String partnumber;

    @ColumnInfo(name = "quantity")
    private int quantity;

    public Part(){
    }

    @Ignore
    public Part(String serial, String partnumber, int quantity){
        this.serial = serial;
        this.partnumber = partnumber;
        this.quantity = quantity;
    }

    @Ignore
    public Part(ContentValues contentValues){
        this.serial = contentValues.getAsString("serial");
        this.partnumber = contentValues.getAsString("partnumber");
        this.quantity = contentValues.getAsInteger("quantity");
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
