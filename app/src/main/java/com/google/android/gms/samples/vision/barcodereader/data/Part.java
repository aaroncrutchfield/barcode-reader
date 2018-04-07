package com.google.android.gms.samples.vision.barcodereader.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by ioutd on 12/1/2017.
 */

@Entity
public class Part {

    @PrimaryKey
    @NonNull
    private String serial;

    @ColumnInfo
    private String partnumber;

    @ColumnInfo
    private int packQuantity;

    @ColumnInfo
    private int containers;

    public Part(){

    }

    public String getPartnumber() {
        return partnumber;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    public int getPackQuantity() {
        return packQuantity;
    }

    public void setPackQuantity(int packQuantity) {
        this.packQuantity = packQuantity;
    }

    public int getContainers() {
        return containers;
    }

    public void setContainers(int containers) {
        this.containers = containers;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

}
