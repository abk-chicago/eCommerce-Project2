package com.andreakim.artsupplies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import org.w3c.dom.Text;

public class ProductsActivity extends AppCompatActivity {

    Intent mMainIntent;
    Intent mDetailIntent;
    Intent mShoppingCartIntent;
    ListView mProductsView;
    Cursor mCursor;
    private CursorAdapter mCursorAdapter;
    private ArtSuppliesSQLiteOpenHelper mHelper;
    public String mProducts;
    AdapterView.OnItemClickListener mClickListener;


    Button btnMain = (Button) findViewById(R.id.btn_toMain);
    Button btnCart = (Button) findViewById(R.id.btn_toCart);

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        //   handleIntent(getIntent());


 //moved SQL db insert from here

        mProductsView = (ListView) findViewById(R.id.listViewProducts);

        mHelper = new ArtSuppliesSQLiteOpenHelper(ProductsActivity.this);
        mCursor = mHelper.getProducts();


        CursorAdapter mCursorAdapter = new CursorAdapter(ProductsActivity.this,mCursor,0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView txt = (TextView) view.findViewById(android.R.id.text1);
                String rowData = cursor.getString(cursor.getColumnIndex("NAME")) + " : " + cursor.getString(cursor.getColumnIndex("STYLE")) + " : " + cursor.getString(cursor.getColumnIndex("MFG")) + " : " + cursor.getString(cursor.getColumnIndex("PRICE"));
                txt.setText(rowData);
            }
        };
        //should sending the db info to the ListView
        mProductsView.setAdapter(mCursorAdapter);

        //sends the user to detail page when clicked
        mProductsView.setOnItemClickListener(mClickListener);

        mClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mDetailIntent = new Intent(ProductsActivity.this,IndivProdDetActivity.class);

            String name = mCursor.getString(mCursor.getColumnIndex("NAME"));
            String style = mCursor.getString(mCursor.getColumnIndex("STYLE"));
            String mfg = mCursor.getString(mCursor.getColumnIndex("MFG"));
            String price = mCursor.getString(mCursor.getColumnIndex("PRICE"));
            mDetailIntent.putExtra("NAME",name);
            mDetailIntent.putExtra("STYLE",style);
            mDetailIntent.putExtra("MFG",mfg);
            mDetailIntent.putExtra("PRICE",price);


            startActivity(mDetailIntent);
        }
    };


        btnMain.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           mMainIntent = new Intent(ProductsActivity.this, MainActivity.class);
                                           startActivity(mMainIntent);
                                           Button btn = (Button) findViewById(R.id.btn_toMain);
                                       }
                                   };

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShoppingCartIntent = new Intent(ProductsActivity.this, ShoppingCartActivity.class);
                startActivity(mShoppingCartIntent);


                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
            }
        }


            private void handleIntent(Intent intent) {
                if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                    String query = intent.getStringExtra(SearchManager.QUERY);
                    Cursor cursor = mHelper.searchArtSupplies(query);
                    mCursorAdapter.changeCursor(cursor);
                    mCursorAdapter.notifyDataSetChanged();
                }
            }

    @Override
    public void onNewIntent(Intent intent) {
        handleIntent(getIntent());
    }


            @Override
            public void onStart() {
                super.onStart();

                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                client.connect();
                Action viewAction = Action.newAction(
                        Action.TYPE_VIEW, // TODO: choose an action type.
                        "ProductItem Page", // TODO: Define a title for the content shown.
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
                        "ProductItem Page", // TODO: Define a title for the content shown.
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
    }