package com.andreakim.artsupplies;

/**
 * Created by andreakim on 6/27/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Products.db";

    public static final String CREATE_TABLE = "create table products(id int primary key autoincrement, name text, manufacturer text, detail text, price integer);";
    public static final String DROP_TABLE = "drop table if exists products;";

    public DatabaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
        // new SQLiteOpenHelper(ctx, "Products.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void createProduct(int id, String name, String manufacturer, String detail, double price) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues products = new ContentValues();
        products.put("name", name);
        products.put("manufacturer", manufacturer);
        products.put("detail", detail);
        products.put("price", price);
        db.insert("products", null, products);
    }

    public ArrayList<String> showProductsAll() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query("products", null, null, null, null, null, null);
        ArrayList<String> products = new ArrayList<>();
        c.moveToFirst();

        while (!c.isAfterLast()) {
            products.add("# " + c.getString(0) + "; - product name: " + c.getString(1) + "; - product manufacturer: " + c.getString(2) + "; product detail: " + c.getString(3) + "; price: " + c.getString(4));
            c.moveToNext();
        }
        c.close();
        return products;
    }



    public Products getProductById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = new String[] { "id" , "name", "manufacturer", "details" , "price"};
        String selection = " id = ?";
        String[] SelectionArgs = new String[] {Integer.toString(id)};


        Cursor cursor = db.query("products", projection, selection, SelectionArgs, null, null, null, null);

        int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
        double productPrice = Double.parseDouble(cursor.getString(cursor.getColumnIndex("price")));
        String productName = cursor.getString(cursor.getColumnIndex("name"));
        String productManufacturer = cursor.getString(cursor.getColumnIndex("manufacturer"));
        String productDetail = cursor.getString(cursor.getColumnIndex("details"));

        Products products = new Products();
        cursor.close();
        return products;

    };


}
