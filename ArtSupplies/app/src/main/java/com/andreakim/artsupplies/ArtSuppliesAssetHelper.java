package com.andreakim.artsupplies;

/**
 * Created by andreakim on 7/2/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class ArtSuppliesAssetHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Art_Supplies.db";
    private static final int DATABASE_VERSION = 12;
    public static final String PRODUCTS_TABLE_NAME = "Products";
    public static final String CUSTOMER_TABLE_NAME = "Customer";
    public static final String ORDER_DETAIL_TABLE_NAME = "Order";


    private static final String TAG = SQLiteOpenHelper.class.getCanonicalName();


    // for PRODUCTS TABLE
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String STYLE = "style";
    public static final String MFG = "mfg";
    public static final String PRICE = "price";

    // for ORDER_DETAIL AND CUSTOMER TABLES
    public static final String STATE = "STATE";
    public static final String ITEMS_ORDERED = "ITEMS ORDERED";
    public static final String ORDER_NO = "ORDER NUMBER";
    public static final String QUANTITY = "QUANTITY";
    public static final String PRETAX_TOTAL = "PRETAX TOTAL";

    //setting up colums for the 3 tables in DB
    public static final String[] products_columns = {ID,NAME,STYLE,MFG,PRICE};
    public static final String[] customer_columns = {ID,NAME,STATE,ITEMS_ORDERED};
    public static final String[] order_columns = {ID,ORDER_NO,QUANTITY,PRETAX_TOTAL};

    // setting up 2nd and 3rd tables
    // products table is already in the database file

    private static final String CREATE_PRODUCTS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + PRODUCTS_TABLE_NAME +
                    "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NAME + " TEXT, " +
                    STYLE + " TEXT, " +
                    MFG + " TEXT, " +
                    PRICE + " TEXT );";

    private static final String CREATE_CUSTOMER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CUSTOMER_TABLE_NAME +
                    "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    STATE + " TEXT, " +
                    ITEMS_ORDERED + " TEXT );";

    private static final String CREATE_ORDER_DETAIL_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ORDER_DETAIL_TABLE_NAME +
                    "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ORDER_NO + " TEXT, " +
                    QUANTITY + " TEXT, " +
                    PRETAX_TOTAL + " TEXT );";

    //setting up Drop Table SQL command strings for the 3 tables
    private static final String DROP_PRODUCTS_TABLE = "DROP TABLE IF EXISTS PRODUCTS";
    private static final String DROP_CUSTOMER_TABLE = "DROP CUSTOMER_TABLE IF EXISTS CUSTOMER";
    private static final String DROP_ORDER_DETAIL_TABLE = "DROP ORDER_DETAIL_TABLE IF EXISTS ORDER_DETAIL";

    public ArtSuppliesAssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCTS_TABLE);
            db.execSQL("INSERT INTO PRODUCTS VALUES (null,\"paint brushes\",\"pointed sable round set of 3\",\"Blick\",\"$4.00\");");
            db.execSQL("INSERT INTO PRODUCTS VALUES (null,\"watercolor paint\",\"set of 18\",\"Camellia\",\"$5.50\");");
            db.execSQL("INSERT INTO PRODUCTS VALUES (null, \"x-acto knife\",\"#1 knife\",\"X-acto\",\"$3.65\");");
            db.execSQL("INSERT INTO PRODUCTS VALUES (null, \"art boards\",\"16 x 20\",\"Canson\",\"$5.99\");");
            db.execSQL("INSERT INTO PRODUCTS VALUES (null, \"sculpey\",\"1.75 lb\",\"Sculpey\",\"$10.99\");");
            db.execSQL("INSERT INTO PRODUCTS VALUES (null, \"mixed media gift set\",\"watercolor pencils and pitt artists pens - set of 14\",\"Albrecht Dürer\",\"$28.99\");");
            db.execSQL("INSERT INTO PRODUCTS VALUES (null, \"lino cutter set\",\"handle and 6 blades\",\"Blick\",\"$8.00\");");
            db.execSQL("INSERT INTO PRODUCTS VALUES (null, \"quartet char-kole squares\",\"box of 3\",\"Alphacolor\",\"$2.50\");");
            db.execSQL("INSERT INTO PRODUCTS VALUES (null, \"sumi-e watercolor sets\",\"set of 6\",\"Yasutomo\",\"$19.99\");");
            db.execSQL("INSERT INTO PRODUCTS VALUES (null, \"drawing pads\",\"9 x 12\",\"Canson Edition\",\"$16.50\");");


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

    // HERE ********** is the getProducts method.
    public Cursor getProducts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PRODUCTS_TABLE_NAME, // a. table
                null,null, null,null,null,null,null);
        return cursor;
    }

    // to search Products Table
    public Cursor searchArtSupplies(String query) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PRODUCTS_TABLE_NAME, // a. table
                null, // b. column names
                NAME + " LIKE ?" + " OR " + STYLE + " LIKE ?" + " OR " + MFG, // c. selections
                new String[]{ "%" + query + "%"}, // d. selections args
                null,null,null,null);
        return cursor;
    }
    public Cursor searchArt(String query) {

        if ((NAME != null) && (MFG != null) && (STYLE != null)) {
            String name = "%" + NAME + "% ";
            String mfg = "%" + MFG + "% ";
            String style = "%" + STYLE + "% ";

            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = " select * from Products where name like '" + name + "' or style like '" + style + "' or mfg like '" + mfg + " %" + query + "%";

            Cursor cursor = db.rawQuery(selectQuery, null);
            return cursor;
        }


        //SQLiteAssetHelper dbSetup = new SQLiteAssetHelper();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(PRODUCTS_TABLE_NAME, null, null, null, null, null, null, null);
        return c;
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




