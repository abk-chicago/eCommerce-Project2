package com.andreakim.artsupplies;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IndivProdDetActivity extends AppCompatActivity {

    TextView mTxtName;
    TextView mTxtStyle;
    TextView mTextMfg;
    TextView mTextPrice;
    Intent mMainIntent;
    Intent mShoppingCartIntent;

    public TextView getmTxtName() {
        return mTxtName;
    }

    public void setmTxtName(TextView mTxtName) {

        this.mTxtName = mTxtName;
    }

    public TextView getmTxtStyle() {
        return mTxtStyle;
    }

    public void setmTxtStyle(TextView mTxtStyle) {
        this.mTxtStyle = mTxtStyle;
    }

    public TextView getmTextMfg() {
        return mTextMfg;
    }

    public void setmTextMfg(TextView mTextMfg) {
        this.mTextMfg = mTextMfg;
    }

    public TextView getmTextPrice() {
        return mTextPrice;
    }

    public void setmTextPrice(TextView mTextPrice) {
        this.mTextPrice = mTextPrice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indiv_prod_det);

        mTxtName = (TextView) findViewById(R.id.textV_name);
        mTextMfg = (TextView) findViewById(R.id.textV_mfg);
        mTxtStyle = (TextView) findViewById(R.id.textV_style);
        mTextPrice = (TextView) findViewById(R.id.textV_price);

        Intent listIntent = getIntent();
        String name = listIntent.getStringExtra("NAME");
        String prd = "Product Selected: ";
        String prdProd = prd + name;
        String style = listIntent.getStringExtra("STYLE");
        String mfg = listIntent.getStringExtra("MFG");
        String mMfg =  "Manufacturer: ";
        String mftMfg = mMfg + mfg;
        String price = listIntent.getStringExtra("PRICE");
        String prc = "Price: ";
        String prcPrice = prc + price;

        mTxtName.setText(prdProd);
        mTxtStyle.setText(style);
        mTextMfg.setText(mftMfg);
        mTextPrice.setText(prcPrice);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShoppingCartIntent = new Intent(IndivProdDetActivity.this, ShoppingCartActivity.class);
                startActivity(mShoppingCartIntent);
            }
        });

        Button btnMain= (Button) findViewById(R.id.back_main);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainIntent = new Intent(IndivProdDetActivity.this, MainActivity.class);
                startActivity(mMainIntent);
            }
        });
    }
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }


}




