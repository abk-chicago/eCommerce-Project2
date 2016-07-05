package com.andreakim.artsupplies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IndivProdDetActivity extends AppCompatActivity {

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

    public IndivProdDetActivity(TextView mTxtName, TextView mTxtStyle, TextView mTextMfg, TextView mTextPrice) {
        this.mTxtName = mTxtName;
        this.mTxtStyle = mTxtStyle;
        this.mTextMfg = mTextMfg;

        this.mTextPrice = mTextPrice;
    }

    TextView mTxtName;
    TextView mTxtStyle;
    TextView mTextMfg;
    TextView mTextPrice;

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
        String style = listIntent.getStringExtra("STYLE");
        String mfg = listIntent.getStringExtra("MFG");
        String price = listIntent.getStringExtra("PRICE");

        mTxtName.setText(name);
        mTxtStyle.setText(style);
        mTextMfg.setText(mfg);
        mTextPrice.setText(price);

    }
}




