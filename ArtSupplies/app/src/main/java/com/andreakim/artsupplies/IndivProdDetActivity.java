package com.andreakim.artsupplies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class IndivProdDetActivity extends AppCompatActivity {

    TextView mTxtName;
    TextView mTxtStyle;
    TextView mTextMfg;
    TextView mTextPrice;
    Intent mMainIntent;
    Intent mShoppingCartIntent;
    ShoppingCart cart;
    String extra;
    ListView lv;
    ArrayList<String> mStringArray;



    private GoogleApiClient client;

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
        String mMfg = "Manufacturer: ";
        String mftMfg = mMfg + mfg;
        String price = listIntent.getStringExtra("PRICE");
        String prc = "Price: ";
        String prcPrice = prc + price;

        mTxtName.setText(prdProd);
        mTxtStyle.setText(style);
        mTextMfg.setText(mftMfg);
        mTextPrice.setText(prcPrice);


        Button btnMain = (Button) findViewById(R.id.back_main);

        lv = (ListView) findViewById(R.id.listView_cart);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShoppingCartIntent = new Intent(IndivProdDetActivity.this, ShoppingCartActivity.class);
                cart = ShoppingCart.getInstance();
                mShoppingCartIntent.putStringArrayListExtra("cart", cart.items);
                startActivity(mShoppingCartIntent);
            }
        });

        Bundle stringArrayList = getIntent().getExtras();
        mStringArray = stringArrayList.getStringArrayList("nope");


        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainIntent = new Intent(IndivProdDetActivity.this, MainActivity.class);
                startActivity(mMainIntent);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "IndivProdDet Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.andreakim.artsupplies/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "IndivProdDet Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.andreakim.artsupplies/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}



