package com.andreakim.artsupplies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {

    Intent mMainIntent;
    ListView lv;
    Button btnMain;
    ShoppingCart cart;
    String mExtra;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);


        cart = ShoppingCart.getInstance();

        btnMain = (Button) findViewById(R.id.cart_btn_bk_main);

        ArrayList<String> cart_items = getIntent().getStringArrayListExtra("cart");
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cart_items);

        lv = (ListView) findViewById(R.id.listView_cart);
        lv.setAdapter(itemsAdapter);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainIntent = new Intent(ShoppingCartActivity.this, MainActivity.class);
                startActivity(mMainIntent);
            }
        });
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }
}