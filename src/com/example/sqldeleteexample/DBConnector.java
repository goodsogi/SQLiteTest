package com.example.sqldeleteexample;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqldeleteexample.Constants.TestEntry;

/**
 * 디비 조작을 위한 메소드들
 * 
 * @author user
 * 
 */
public class DBConnector {
	private SQLiteDatabase mDB;

	private static final String SQL_SELECT_TEST = "SELECT * FROM "
			+ TestEntry.TABLE_NAME_TEST;

	private static final String LOG_TAG = "sqlliteDelete";

	public DBConnector(Context context) {
		DBHelper dbHelper = new DBHelper(context);
		mDB = dbHelper.getWritableDatabase();
		mDB.delete(TestEntry.TABLE_NAME_TEST, null, null);
	}

	public void disconnectDB() {
		this.mDB.close();
	}

	/**
	 * Add test data in test db
	 * 
	 * @param id
	 * @param pass
	 * @param name
	 * @param exp
	 */
	public void addItemInTestDB(String id, String pass, String name, int exp) {
		ContentValues cv = new ContentValues();
		cv.put(TestEntry.COLUMN_NAME_ID, id);
		cv.put(TestEntry.COLUMN_NAME_PASS, pass);
		cv.put(TestEntry.COLUMN_NAME_NAME, name);
		cv.put(TestEntry.COLUMN_NAME_EXP, exp);

		mDB.insert(TestEntry.TABLE_NAME_TEST, null, cv);

	}

	/**
	 * Get data from test DB
	 * 
	 * @return
	 */
	public Cursor getDataFromTestDB() {
		Cursor c = mDB.rawQuery(SQL_SELECT_TEST, null);
		c.moveToFirst();
		return c;
	}

	/**
	 * Delete duplicate row
	 */
	public void deleteDuplicateRow() {
		// Get all data from database
		Cursor c = mDB.rawQuery(SQL_SELECT_TEST, null);

		c.moveToFirst();

		// Put data in arraylist
		ArrayList<Data> datas = new ArrayList<Data>();
		for (int i = 0; i < c.getCount(); i++) {
			String id = c.getString(c.getColumnIndex(TestEntry.COLUMN_NAME_ID));
			int exp = c.getInt(c.getColumnIndex(TestEntry.COLUMN_NAME_EXP));

			if (id.equals((datas.size() == 0) ? ""
					: datas.get(datas.size() - 1).id)) {

				if (exp > datas.get(datas.size() - 1).maxValue) {
					datas.get(datas.size() - 1).maxValue = exp;
				}
			} else {
				Data data = new Data();
				data.id = id;
				data.maxValue = exp;
				datas.add(data);
			}
			c.moveToNext();

		}

		c.close();

		// Delete other rows except max exp
		for (int i = 0; i < datas.size(); i++) {
			mDB.execSQL("DELETE FROM test_table WHERE id = '" + datas.get(i).id
					+ "' AND exp != " + datas.get(i).maxValue);
		}

	}
}
