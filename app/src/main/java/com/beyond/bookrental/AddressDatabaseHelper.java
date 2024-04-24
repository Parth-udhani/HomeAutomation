package com.beyond.bookrental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AddressDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "address_database";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    private static final String TABLE_ADDRESS = "address";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "full_name";
    private static final String COLUMN_CONTACT = "mobile_no";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_STATE = "state";
    private static final String COLUMN_PINCODE = "pin_code";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ADDRESS + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_CONTACT + " text not null, "
            + COLUMN_ADDRESS + " text not null, "
            + COLUMN_CITY + " text not null, "
            + COLUMN_STATE + " text not null, "
            + COLUMN_PINCODE + " text not null);";

    public AddressDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade operations, if any
    }

    // Add method to insert data into the database
    public long insertAddress(AddressModel address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, address.getFullName());
        values.put(COLUMN_CONTACT, address.getMobileNo());
        values.put(COLUMN_ADDRESS, address.getAddress());
        values.put(COLUMN_CITY, address.getCity());
        values.put(COLUMN_STATE, address.getState());
        values.put(COLUMN_PINCODE, address.getPinCode());

        // Insert row
        long id = db.insert(TABLE_ADDRESS, null, values);
        db.close();
        return id;
    }

    // Add method to retrieve all addresses from the database
    public List<AddressModel> getAllAddresses() {
        List<AddressModel> addressList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADDRESS, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String fullName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String mobileNo = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
                String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
                String pinCode = cursor.getString(cursor.getColumnIndex(COLUMN_PINCODE));

                AddressModel addressModel = new AddressModel(fullName, mobileNo, address, city, state, pinCode);
                addressList.add(addressModel);
            } while (cursor.moveToNext());

            // Close the cursor after retrieving data
            cursor.close();
        }

        // Close the database
        db.close();
        return addressList;
    }

}

