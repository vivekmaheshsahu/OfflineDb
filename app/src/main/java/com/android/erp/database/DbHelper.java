package com.android.erp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.erp.database.model.EmpModelClass;
import com.android.erp.utility.Utility;

import java.io.File;

import static com.android.erp.database.DatabaseContract.DATABASE_NAME;
import static com.android.erp.database.DatabaseContract.DATABASE_VERSION;
import static com.android.erp.database.DatabaseContract.DB_LOCATION;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, DB_LOCATION + File.separator + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.TableCreation.CREATE_TABLE1);
        db.execSQL(DatabaseContract.TableCreation.CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(
                    DB_LOCATION, null,
                    SQLiteDatabase.OPEN_READONLY
            );
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        return checkDB != null;
    }

    public String checkUserAlreadyRegistered(String uniqueId) {
        Utility utility = new Utility();
        Cursor cursor = Utility.getDatabase().rawQuery("SELECT * "
                + " FROM "
                + DatabaseContract.TABLE_NAME_EMP
                + " WHERE "
                + DatabaseContract.COLUMN_EMP_Mobile
                + " = ? ", new String[]{uniqueId});

        return cursor.moveToFirst() ? cursor.getString(cursor.getColumnIndex(DatabaseContract.COLUMN_EMP_Mobile)) : "";
    }

    /**
     * Function to insert data of Employee
     */

    public long addEmployee(EmpModelClass emp) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Emp_Username", emp.getName());
        contentValues.put("EmpName", emp.getName());
        contentValues.put("EmpEmail", emp.getEmail());
        contentValues.put("EmpMobileNumber", emp.getMobile());
        contentValues.put("EmpMobileAge", emp.getAge());
        contentValues.put("EmpMobileDob", emp.getAge());
        contentValues.put("EmpGender", emp.getGender());
        contentValues.put("EmpDesignation", emp.getDesignation());
        return Utility.getDatabase().insert(DatabaseContract.TABLE_NAME_EMP, null, contentValues);
    }

    public long depEmployee(EmpModelClass emp) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Dep_Dob", emp.getAge());
        contentValues.put("Emp_mob", emp.getMobile());
        contentValues.put("Dep_Name", emp.getName());
        contentValues.put("Dep_Relation", emp.getDept());
        return Utility.getDatabase().insert(DatabaseContract.TABLE_NAME_Dep, null, contentValues);
    }

    public Cursor fetchAll() {
        return Utility.getDatabase().rawQuery("SELECT * From Emp_Details", null);
    }

    public Cursor fetchDependedDetails(String mobile) {
        return Utility.getDatabase().rawQuery("SELECT * From Emp_Dep WHERE Emp_mob = " + " ?", new String[]{mobile});
    }

    /**
     * Function to delete dependant
     */
    public int deleteData(String mobile) {
        int cursor1 = Utility.getDatabase().delete(DatabaseContract.TABLE_NAME_EMP
                , DatabaseContract.COLUMN_EMP_Mobile + " = ? "
                , new String[]{mobile});
        return cursor1;
    }


    /**
     * Function to delete dependant
     */
    public int deleteDepData(String mobile) {
        int cursor1 = Utility.getDatabase().delete(DatabaseContract.TABLE_NAME_Dep
                , DatabaseContract.COLUMN_Emp_no + " = ? "
                , new String[]{mobile});
        return cursor1;
    }
}



