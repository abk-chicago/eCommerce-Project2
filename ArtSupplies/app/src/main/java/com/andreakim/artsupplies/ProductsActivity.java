package com.andreakim.artsupplies;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ProductsActivity extends AppCompatActivity {

    Intent mMainIntent;
    Intent mShoppingCartIntent;
    private ListView mProductsView;
    private CursorAdapter mCursorAdapter;
    private ArtSuppliesSQLiteOpenHelper mHelper;
    public String mProducts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        handleIntent(getIntent());



        ArtSuppliesSQLiteOpenHelper db = new ArtSuppliesSQLiteOpenHelper(this);
        db.addProduct("paint brushes","pointed sable round set of 3", "Blick", "4.00");
        db.addProduct("watercolor paint", "set of 18","Camellia", "5.50");
        db.addProduct("x-act knife","#1 knife", "X-acto", "3.65");
        db.addProduct("art boards", "16 x 20", "Canson","5.99");
        db.addProduct("sculpey","1.75 lb","Sculpey","10.99");
        db.addProduct("mixed media gift set","watercolor pencils and pitt artists pens, set of 14","Albrecht Dürer","28.99");
        db.addProduct("lino cutter set","handle and 6 blades","Blick","8.00");
        db.addProduct("quartet char-kole squares","box of 3","Alphacolor","2.50");
        db.addProduct("sumi-e watercolor sets","set of 6","Yasutomo","19.99");
        db.addProduct("drawing pads","9 x 12","Canson Edition","16.50");






        mProductsView = (ListView) findViewById(R.id.listViewProducts);
        mHelper = new ArtSuppliesSQLiteOpenHelper(ProductsActivity.this);
        Cursor cursor = mHelper.getProducts();
        mCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{ArtSuppliesSQLiteOpenHelper.NAME}, new int[]{android.R.id.text1}, 0);
        mProductsView.setAdapter(mCursorAdapter);
    }


//    @Override
//    public void onNewIntent(Intent intent) {
//        handleIntent();
//    }


//        //Ignore the two lines below, they are for setup
//        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
//        dbSetup.getReadableDatabase();



    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = mHelper.searchArtSupplies(query);
            mCursorAdapter.changeCursor(cursor);
            mCursorAdapter.notifyDataSetChanged();
        }
    }




}
