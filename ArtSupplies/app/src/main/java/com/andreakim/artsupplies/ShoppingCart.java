package com.andreakim.artsupplies;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by andreakim on 7/11/16.
 */
public class ShoppingCart extends ArrayList<ProductItem> {


    // singleton for db instance
    private static ArtSuppliesAssetHelper mInstance;

    public static synchronized ArtSuppliesAssetHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new ArtSuppliesAssetHelper(ctx.getApplicationContext());
        }
        ;
        return mInstance;
    }


}
