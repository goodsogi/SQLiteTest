package com.example.sqldeleteexample;

import android.provider.BaseColumns;

public class Constants {
	// sqllite test table
	public static abstract class TestEntry implements BaseColumns {
		public static final String TABLE_NAME_TEST = "test_table";
		public static final String _ID = "_id";
		public static final String COLUMN_NAME_ID = "id";
		public static final String COLUMN_NAME_PASS = "pass";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_EXP = "exp";
	}
}
