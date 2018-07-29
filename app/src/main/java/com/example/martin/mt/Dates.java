package com.example.martin.mt;
import com.google.gson.annotations.SerializedName;


public class Dates {

    @SerializedName("maximum")
    private String mMaximum;
    @SerializedName("minimum")
    private String mMinimum;

    public String getMaximum() {
        return mMaximum;
    }

    public void setMaximum(String maximum) {
        mMaximum = maximum;
    }

    public String getMinimum() {
        return mMinimum;
    }

    public void setMinimum(String minimum) {
        mMinimum = minimum;
    }

}
