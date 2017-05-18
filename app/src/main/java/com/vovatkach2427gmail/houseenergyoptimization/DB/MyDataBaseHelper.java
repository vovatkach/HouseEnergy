package com.vovatkach2427gmail.houseenergyoptimization.DB;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by vovat on 31.03.2017.
 */

public class MyDataBaseHelper extends SQLiteAssetHelper {
    public MyDataBaseHelper(Context context) {
        super(context, Contact.DATABASE_NAME, null, Contact.DATABASE_VERSION);
    }
}
