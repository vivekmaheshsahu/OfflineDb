package com.android.erp.database;

import android.os.Environment;

public class DatabaseContract {

    public static final String DATABASE_NAME = "Erp.sr";
    public static final int DATABASE_VERSION = 1;
    public static final String DB_LOCATION = Environment.getExternalStorageDirectory() + "/Erp";
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    public static final String TABLE_NAME_EMP = "Emp_Details";
    public static final String COLUMN_EMP_ID = "EmpId";
    public static final String COLUMN_Emp_Username = "Emp_Username";
    public static final String COLUMN_EMP_NAME = "EmpName";
    public static final String COLUMN_EMP_Email = "EmpEmail";
    public static final String COLUMN_EMP_Mobile = "EmpMobileNumber";
    public static final String COLUMN_EMP_Age = "EmpMobileAge";
    public static final String COLUMN_EMP_Dob = "EmpMobileDob";
    public static final String COLUMN_EMP_Gender = "EmpGender";
    public static final String COLUMN_EMP_Designation = "EmpDesignation";

    //Emp dependant details
    public static final String TABLE_NAME_Dep = "Emp_Dep";
    public static final String COLUMN_EMP_Dep_ID = "Dep_Id";
    public static final String COLUMN_Emp_no = "Emp_mob";
    public static final String COLUMN_EMP_Dep_Name = "Dep_Name";
    public static final String COLUMN_EMP_Dep_Dob = "Dep_Dob";
    public static final String COLUMN_EMP_Dep_Relation = "Dep_Relation";

    public static final class TableCreation {

        public static final String CREATE_TABLE1 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_Dep +
                "(" +
                COLUMN_EMP_Dep_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_Emp_no + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_Dep_Name + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_Dep_Dob + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_Dep_Relation + TEXT_TYPE +
                ")";

        public static final String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_EMP +
                "(" +
                COLUMN_EMP_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_Emp_Username + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_Email + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_Mobile + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_Age + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_Dob + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_Gender + TEXT_TYPE + COMMA_SEP +
                COLUMN_EMP_Designation + TEXT_TYPE +
                ")";
    }
}
