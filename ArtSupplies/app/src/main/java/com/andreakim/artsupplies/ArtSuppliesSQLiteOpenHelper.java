package com.andreakim.artsupplies;

/**
 * Created by andreakim on 7/2/16.
 */
import android.content.ContentValues;
        import android.content.Context;
import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;

public class ArtSuppliesSQLiteOpenHelper extends SQLiteOpenHelper{
    private static final String TAG = ArtSuppliesSQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ART_SUPPLIES_DB";
    public static final String PRODUCTS_TABLE_NAME = "PRODUCTS";
    public static final String CUSTOMER_TABLE_NAME = "CUSTOMERS";
    public static final String ORDER_DETAIL_TABLE_NAME = "ORDERS";

// for PRODUCTS TABLE
    public static final String ID = "_id";
    public static final String NAME = "NAME";
    public static final String STYLE = "STYLE";
    public static final String MFG = "MFG";
    public static final String PRICE = "PRICE";

// for ORDER_DETAIL AND CUSTOMER TABLES
    public static final String STATE = "STATE";
    public static final String ITEMS_ORDERED = "ITEMS ORDERED";
    public static final String ORDER_NO = "ORDER NUMBER";
    public static final String QUANTITY = "QUANTITY";
    public static final String PRETAX_TOTAL = "PRETAX TOTAL";



//setting up colums for the 3 tables in DB
    public static final String[] PRODUCTS_COLUMNS = {ID,NAME,STYLE,MFG,PRICE};
    public static final String[] CUSTOMER_COLUMNS = {ID,NAME,STATE,ITEMS_ORDERED};
    public static final String[] ORDER_DETAIL_COLUMNS = {ID,ORDER_NO,QUANTITY,PRETAX_TOTAL};


    //setting up tables
    private static final String CREATE_PRODUCTS_TABLE =
            "CREATE TABLE " + PRODUCTS_TABLE_NAME +
                    "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    STYLE + " TEXT, " +
                    MFG + " TEXT, " +
                    PRICE + " TEXT )";

    private static final String CREATE_CUSTOMER_TABLE =
            "CREATE TABLE " + CUSTOMER_TABLE_NAME +
                    "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    STATE + " TEXT, " +
                    ITEMS_ORDERED + " TEXT )";

    private static final String CREATE_ORDER_DETAIL_TABLE =
            "CREATE TABLE " + ORDER_DETAIL_TABLE_NAME +
                    "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ORDER_NO + " TEXT, " +
                    QUANTITY + " TEXT, " +
                    PRETAX_TOTAL + " TEXT )";

//setting up Drop Table SQL command strings for the 3 tables
    private static final String DROP_PRODUCTS_TABLE = "DROP PRODUCTS_TABLE IF EXISTS PRODUCTS";
    private static final String DROP_CUSTOMER_TABLE = "DROP CUSTOMER_TABLE IF EXISTS CUSTOMER";
    private static final String DROP_ORDER_DETAIL_TABLE = "DROP ORDER_DETAIL_TABLE IF EXISTS ORDER_DETAIL";

    public ArtSuppliesSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCTS_TABLE);




        db.execSQL("INSERT INTO PRODUCTS VALUES (paint brushes,pointed sable round set of 3,Blick,4.00)");
        db.execSQL("INSERT INTO PRODUCTS VALUES (x-acto knife,#1 knife,X-acto,3.65)");
        db.execSQL("INSERT INTO PRODUCTS VALUES (art boards,16 x 20,Canson,5.99)");
        db.execSQL("INSERT INTO PRODUCTS VALUES (sculpey,1.75 lb,Sculpey,10.99)");
        db.execSQL("INSERT INTO PRODUCTS VALUES (mixed media gift set,watercolor pencils and pitt artists pens, set of 14,Albrecht Dürer,28.99)");
        db.execSQL("INSERT INTO PRODUCTS VALUES (lino cutter set,handle and 6 blades,Blick,8.00)");
        db.execSQL("INSERT INTO PRODUCTS VALUES (quartet char-kole squares,box of 3,Alphacolor,2.50)");
        db.execSQL("INSERT INTO PRODUCTS VALUES (sumi-e watercolor sets,set of 6,Yasutomo,19.99)");
        db.execSQL("INSERT INTO PRODUCTS VALUES (drawing pads,9 x 12,Canson Edition,16.50)");

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_PRODUCTS_TABLE);
        this.onCreate(db);
    }

    public long addProduct(String name, String style, String mfg, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues product = new ContentValues();
        product.put(NAME, name);
        product.put(STYLE, style);
        product.put(MFG, mfg);
        product.put(PRICE, price);

        long returnId = db.insert(PRODUCTS_TABLE_NAME, null, product);
        db.close();
        return returnId;
    }


    public Cursor getProducts(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PRODUCTS_TABLE_NAME, // a. table
                PRODUCTS_COLUMNS, // b. column names
                NAME + " AND " + STYLE, null,null,null,null,null);
        return cursor;
    }


    public Cursor searchArtSupplies(String query) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PRODUCTS_TABLE_NAME, // a. table
                PRODUCTS_COLUMNS, // b. column names
                NAME + " LIKE ?" + " OR " + STYLE + " LIKE ?", // c. selections
                new String[]{"found: " + "%" + query + "%"}, // d. selections args
                null,null,null,null);
        return cursor;
    }

    SQLiteDatabase db = getWritableDatabase();

    Cursor c = db.query("PRODUCTS", null, null, null, null, null, null, null);



//in case I need it...
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

    public ProductItem getProductById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = new String[] { "ID" , "NAME", "STYLE", "MFG" , "PRICE"};
        String selection = " id = ?";
        String[] SelectionArgs = new String[] {Integer.toString(id)};


        Cursor cursor = db.query("PRODUCTS", projection, selection, SelectionArgs, null, null, null, null);

        String productId = cursor.getString(cursor.getColumnIndex("id"));
        String productPrice = cursor.getString(cursor.getColumnIndex("price"));
        String productName = cursor.getString(cursor.getColumnIndex("name"));
        String productManufacturer = cursor.getString(cursor.getColumnIndex("mfg"));
        String productStyle = cursor.getString(cursor.getColumnIndex("style"));

        ProductItem products = new ProductItem();
        cursor.close();
        return products;

    };


}
