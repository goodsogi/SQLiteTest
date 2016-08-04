package com.example.sqldeleteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqldeleteexample.Constants.TestEntry;

/**
 * AllThatKorea 디비 helper
 * 
 * @author user
 * 
 */
public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "Test.db";
	private static final int DATABASE_VERSION = 1;

	private static final String TEXT_TYPE = " TEXT";
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";

	private static final String SQL_CREATE_TEST_TABLE = "CREATE TABLE "
			+ TestEntry.TABLE_NAME_TEST + " (" + TestEntry._ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT " + COMMA_SEP
			+ TestEntry.COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP
			+ TestEntry.COLUMN_NAME_PASS + TEXT_TYPE + COMMA_SEP
			+ TestEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP
			+ TestEntry.COLUMN_NAME_EXP + INTEGER_TYPE + " )";

	private static final String SQL_DELETE_TEST_TABLE = "DROP TABLE IF EXISTS "
			+ TestEntry.TABLE_NAME_TEST;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// create test table
		db.execSQL(SQL_CREATE_TEST_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// delete test table
		db.execSQL(SQL_DELETE_TEST_TABLE);

		// create new db
		onCreate(db);

	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}

}
