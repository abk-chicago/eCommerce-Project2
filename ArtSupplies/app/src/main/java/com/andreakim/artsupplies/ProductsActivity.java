package com.andreakim.artsupplies;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    //declaring member variables, intents, cursor, buttons
    Intent mMainIntent;
    Intent mShoppingCartIntent;
    Intent mDetailIntent;
    ListView mProductsView;
    Cursor mCursor;
    Button btnMain;

    private CursorAdapter mCursorAdapter;
    private ArtSuppliesAssetHelper mHelper;
    public String mProducts;
    AdapterView.OnItemClickListener mClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ShoppingCart cart = ShoppingCart.getInstance();

        //cart.items.add("Test1 from ProductsActivity");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_products);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShoppingCartIntent = new Intent(ProductsActivity.this, ShoppingCartActivity.class);
                startActivity(mShoppingCartIntent);
            }
        });

        //attach button
        btnMain = (Button) findViewById(R.id.btn_toMain);

        mProductsView = (ListView) findViewById(R.id.listViewProducts);
        mHelper = new ArtSuppliesAssetHelper(ProductsActivity.this);
        mCursor = mHelper.getProducts();


        //onClickListener for mMainIntent
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainIntent = new Intent(ProductsActivity.this, MainActivity.class);
                startActivity(mMainIntent);
            }
        });

        // to put db items in ListView rows
         mCursorAdapter = new CursorAdapter(ProductsActivity.this, mCursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                try {
                    System.out.println(mCursor.getPosition());  // for debugging
                    TextView txt = (TextView) view.findViewById(android.R.id.text1);

                    String rowData = mCursor.getString(cursor.getColumnIndex("mfg")) +
                            " : " + mCursor.getString(cursor.getColumnIndex("name")) +
                            " : " + mCursor.getString(cursor.getColumnIndex("style")) +
                            " : " + mCursor.getString(cursor.getColumnIndex("price"));

                    txt.setText(rowData);
                } catch (CursorIndexOutOfBoundsException cioobe) {
                    cioobe.printStackTrace();
                }
            }
        };

        // to send (putExtra) info from ProductsActivity into the detail activity (IndivProdDetailActivity)
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
        //sends the user to detail page when clicked
        mProductsView.setOnItemClickListener(mClickListener);

        //attach cursor to the ListView
        mProductsView.setAdapter(mCursorAdapter);

        //moved to end of method
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.products_menu, menu);

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
        mHelper = new ArtSuppliesAssetHelper(this);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mCursor = mHelper.searchAllArtCategories(query);


            if(mHelper.flag == 1){

                Log.i("Hey","garbage");
            }


                mCursor.moveToFirst();
                if (mCursor != null && mCursorAdapter != null) {
                    mCursorAdapter.changeCursor(mCursor);
                    mCursorAdapter.notifyDataSetChanged();
                }
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