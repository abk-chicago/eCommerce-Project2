package com.andreakim.artsupplies;

import android.os.Environment;

import java.io.Serializable;

/**
 * Created by andreakim on 6/27/16.
 */
public class ProductItem implements Serializable{    // <- because i created custom objects

    private String mPaintbrush;
    private String mProduct;
    private String mName;
    private String mStyle;
    private String mMfg;
    private String mPrice;

    public ProductItem(String mName, String mStyle, String mMfg, String mPrice) {
        this.mName = mName;
        this.mStyle = mStyle;
        this.mPrice = mPrice;
        this.mMfg = mMfg;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmStyle() {
        return mStyle;
    }

    public void setmStyle(String mStyle) {
        this.mStyle = mStyle;
    }

    public String getmMfg() {
        return mMfg;
    }

    public void setmMfg(String mMfg) {
        this.mMfg = mMfg;
    }

    public String getPaintbrush() {
        return mPaintbrush;
    }

    public String getProduct() {
        return mProduct;
    }

    public void setProduct(String mProduct) {
        this.mProduct = mProduct;
    }

    public void setPaintbrush(String paintbrush) {
        mPaintbrush = paintbrush;
    }


    @Override
    public String toString() {
        return mName + " * " + mStyle + " * " + mMfg + " * " + mPrice;
    }
}
