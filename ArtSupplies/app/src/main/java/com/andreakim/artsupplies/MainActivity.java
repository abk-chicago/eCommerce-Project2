package com.andreakim.artsupplies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Intent mProductIntent;
    Intent mShoppingCartIntent;

    Button mClickBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        mClickBtn = (Button) findViewById(R.id.btn_main);

        mClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductIntent = new Intent(MainActivity.this, Products.class);
                startActivity(mProductIntent);
            }
                                     });
    }
}

