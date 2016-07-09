package com.andreakim.artsupplies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ProductsActivity extends AppCompatActivity {

    //declaring member variables, intents, cursor, buttons
    Intent mMainIntent;
    Intent mDetailIntent;
    Intent mShoppingCartIntent;
    ListView mProductsView;
    Cursor mCursor;
    Button btnCart;
    Button btnMain;

    private CursorAdapter mCursorAdapter;
    private ArtSuppliesAssetHelper mHelper;
    public String mProducts;
    AdapterView.OnItemClickListener mClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

//attach buttons
        btnCart = (Button) findViewById(R.id.btn_toCart);
        btnMain = (Button) findViewById(R.id.btn_toMain);

//onClickListener for mMainIntent
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainIntent = new Intent(ProductsActivity.this, MainActivity.class);
                startActivity(mMainIntent);
            }
        });

//onClickListener for ShoppingCartIntent
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShoppingCartIntent = new Intent(ProductsActivity.this, ShoppingCartActivity.class);
                startActivity(mShoppingCartIntent);
            }
        });

        mProductsView = (ListView) findViewById(R.id.listViewProducts);
        mHelper = new ArtSuppliesAssetHelper(ProductsActivity.this);

        mCursor = mHelper.getProducts();
        handleIntent(getIntent());
        mProductsView.setAdapter(mCursorAdapter);

// to put db items in ListView rows
        CursorAdapter mCursorAdapter = new CursorAdapter(ProductsActivity.this, mCursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView txt = (TextView) view.findViewById(android.R.id.text1);
                String rowData = mCursor.getString(cursor.getColumnIndex("mfg")) + " : " + mCursor.getString(cursor.getColumnIndex("name")) + " : " + mCursor.getString(cursor.getColumnIndex("style")) + " : " + mCursor.getString(cursor.getColumnIndex("price"));

                txt.setText(rowData);
            }
        };


        //sends the user to detail page when clicked
        mProductsView.setOnItemClickListener(mClickListener);

        mClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDetailIntent = new Intent(ProductsActivity.this, IndivProdDetActivity.class);

                String name = mCursor.getString(mCursor.getColumnIndex("name"));
                String style = mCursor.getString(mCursor.getColumnIndex("style"));
                String mfg = mCursor.getString(mCursor.getColumnIndex("mfg"));
                String price = mCursor.getString(mCursor.getColumnIndex("price"));
                mDetailIntent.putExtra("NAME", name);
                mDetailIntent.putExtra("STYLE", style);
                mDetailIntent.putExtra("MFG", mfg);
                mDetailIntent.putExtra("PRICE", price);
                startActivity(mDetailIntent);
            }
        };

        //attach cursor to the ListView
        mProductsView.setAdapter(mCursorAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.products_menu, menu);


        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public void onNewIntent(Intent intent) {
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mCursor = mHelper.searchArtSupplies(query);
            mCursorAdapter.changeCursor(mCursor);
            mCursorAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }



}