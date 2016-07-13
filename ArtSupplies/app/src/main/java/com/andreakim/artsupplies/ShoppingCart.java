package com.andreakim.artsupplies;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by andreakim on 7/11/16.
 */
public class ShoppingCart extends ArrayList<ProductItem> {

public static ShoppingCart mInstance;
public ArrayList<String> items;

    ShoppingCart() {
        items = new ArrayList<>();
    }


    public static synchronized ShoppingCart getInstance() {
        if (mInstance == null) {
            mInstance = new ShoppingCart();
        }
        return mInstance;
    }


}
