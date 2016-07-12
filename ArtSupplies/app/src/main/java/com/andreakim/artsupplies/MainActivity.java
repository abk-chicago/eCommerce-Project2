package com.andreakim.artsupplies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Intent mProductIntent;
    Intent mShoppingCartIntent;

    Button mClickBtn;

    @Override
    protected void onSaveInstanceState(Bundle state) {
        Log.i("TEST","ROTATING");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (savedInstanceState !=null) {
            Log.i("OnCreate","savedInstanceState is NOT NULL");
        } else Log.i("OnCreate","savedInstanceState is NULL");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        mClickBtn = (Button) findViewById(R.id.btn_main);
        mClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductIntent = new Intent(MainActivity.this, ProductsActivity.class);
                startActivity(mProductIntent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShoppingCartIntent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(mShoppingCartIntent);
            }
        });


    }
}

