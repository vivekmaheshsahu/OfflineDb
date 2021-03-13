package com.android.erp.utility;

import android.database.sqlite.SQLiteDatabase;

import com.android.erp.database.DatabaseManager;

public class Utility {

    public static SQLiteDatabase getDatabase() {
        return DatabaseManager.getInstance().openDatabase();
    }

}
