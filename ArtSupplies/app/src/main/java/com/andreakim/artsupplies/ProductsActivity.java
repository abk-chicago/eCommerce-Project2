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
