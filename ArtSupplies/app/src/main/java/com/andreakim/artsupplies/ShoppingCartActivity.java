package com.andreakim.artsupplies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShoppingCartActivity extends AppCompatActivity {

    Intent mMainIntent;
    Button btnMain;
    private static ArtSuppliesAssetHelper mInstance;

    public static synchronized ArtSuppliesAssetHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new ArtSuppliesAssetHelper((ctx.getApplicationContext()));
        }
        return mInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ArtSuppliesAssetHelper db = getInstance(this);
        btnMain = (Button) findViewById(R.id.cart_btn_bk_main);


        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainIntent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(mMainIntent);

            }
        });

    }
}