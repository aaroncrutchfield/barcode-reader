package com.google.android.gms.samples.vision.barcodereader.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by ioutd on 12/1/2017.
 */

@Entity
public class Part implements Parcelable {

    @PrimaryKey
    @NonNull
    private String serial;

    @ColumnInfo
    private String partnumber;

    @ColumnInfo
    private int packQuantity;

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

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.serial);
        dest.writeString(this.partnumber);
        dest.writeInt(this.packQuantity);
    }

    protected Part(Parcel in) {
        this.serial = in.readString();
        this.partnumber = in.readString();
        this.packQuantity = in.readInt();
    }

    public static final Parcelable.Creator<Part> CREATOR = new Parcelable.Creator<Part>() {
        @Override
        public Part createFromParcel(Parcel source) {
            return new Part(source);
        }

        @Override
        public Part[] newArray(int size) {
            return new Part[size];
        }
    };
}
