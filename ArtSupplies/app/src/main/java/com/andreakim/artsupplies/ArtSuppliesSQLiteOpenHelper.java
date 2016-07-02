package com.andreakim.artsupplies;

/**
 * Created by andreakim on 7/2/16.
 */
import android.content.ContentValues;
        import android.content.Context;
        import android.content.res.Resources;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.text.TextUtils;
        import android.util.Log;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArtSuppliesSQLiteOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = ArtSuppliesSQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ART_SUPPLIES_DB";
    public static final String PRODUCTS_TABLE_NAME = "PRODUCTS";

    public static final String ID = "_id";
    public static final String NAME = "NAME";
    public static final String STYLE = "STYLE";
    public static final String MFG = "MFG";
    public static final String PRICE = "PRICE";

    public static final String[] PRODUCTS_COLUMNS = {ID,NAME,STYLE,MFG,PRICE};

    private static final String CREATE_PRODUCTS_TABLE =
            "CREATE TABLE " + PRODUCTS_TABLE_NAME +
                    "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    STYLE + " TEXT, " +
                    MFG + " TEXT, " +
                    PRICE + " TEXT )";

    public ArtSuppliesSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCTS_TABLE_NAME);
        this.onCreate(db);
    }

    public long addItem(String name, String style, String mfg, String price){
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(STYLE, style);
        values.put(MFG, mfg);
        values.put(PRICE, price);

        SQLiteDatabase db = this.getWritableDatabase();
        long returnId = db.insert(PRODUCTS_TABLE_NAME, null, values);
        db.close();
        return returnId;
    }




//
//    public Products getProductById(int id) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String[] projection = new String[] { "id" , "name", "manufacturer", "style" , "price"};
//        String selection = " id = ?";
//        String[] SelectionArgs = new String[] {Integer.toString(id)};
//
//
//        Cursor cursor = db.query("products", projection, selection, SelectionArgs, null, null, null, null);
//
//        int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
//        double productPrice = Double.parseDouble(cursor.getString(cursor.getColumnIndex("price")));
//        String productName = cursor.getString(cursor.getColumnIndex("name"));
//        String productManufacturer = cursor.getString(cursor.getColumnIndex("manufacturer"));
//        String productStyle = cursor.getString(cursor.getColumnIndex("style"));
//
//        Products products = new Products();
//        cursor.close();
//        return products;
//
//    };





    public Cursor getProducts(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PRODUCTS_TABLE_NAME, // a. table
                PRODUCTS_COLUMNS, // b. column names
                NAME + " AND " + STYLE, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }


    public Cursor searchArtSupplies(String query) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PRODUCTS_TABLE_NAME, // a. table
                PRODUCTS_COLUMNS, // b. column names
                NAME + " LIKE ?" + " OR " + STYLE + " LIKE ?", // c. selections
                new String[]{"found: " + "%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }

    public ArrayList<String> showProductsAll() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query("PRODUCTS", null, null, null, null, null, null);
        ArrayList<String> products = new ArrayList<>();
        c.moveToFirst();

        while (!c.isAfterLast()) {
            products.add("# " + c.getString(0) + "; - product name: " + c.getString(1) + "; - product manufacturer: " + c.getString(2) + "; product style: " + c.getString(3) + "; price: " + c.getString(4));
            c.moveToNext();
        }
        c.close();
        return products;
    }



    public Products getProductById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = new String[] { "ID" , "NAME", "MFG", "STYLE" , "PRICE"};
        String selection = " id = ?";
        String[] SelectionArgs = new String[] {Integer.toString(id)};


        Cursor cursor = db.query("products", projection, selection, SelectionArgs, null, null, null, null);

        String productId = cursor.getString(cursor.getColumnIndex("id"));
        String productPrice = cursor.getString(cursor.getColumnIndex("price"));
        String productName = cursor.getString(cursor.getColumnIndex("name"));
        String productManufacturer = cursor.getString(cursor.getColumnIndex("mfg"));
        String productDetail = cursor.getString(cursor.getColumnIndex("style"));

        Products products = new Products();
        cursor.close();
        return products;

    };


}
