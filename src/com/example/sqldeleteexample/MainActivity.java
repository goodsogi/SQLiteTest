package com.example.sqldeleteexample;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	String[] ids = { "Test01", "Test01", "Test01", "Test01", "Test02",
			"Test02", "Test03" };
	String[] passes = { "1234", "1234", "1234", "1234", "5678", "5678", "9010" };
	String[] names = { "테스터01", "테스터01", "테스터01", "테스터01", "테스터02", "테스터02",
			"테스터03" };
	int[] exps = { 500, 1500, 1500, 3950, 700, 500, 1000 };

	private DBConnector mDbConnector;
	private CustomCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDbConnector = new DBConnector(this);
		putTestDataInDB();
		makeListView();
		doButtonClickJob();
	}

	/**
	 * Delete overlapped data
	 */
	private void doButtonClickJob() {
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDbConnector.deleteDuplicateRow();
				makeListView();
			}
		});
	}

	/**
	 * put test data in sqllite database
	 */
	private void putTestDataInDB() {

		for (int i = 0; i < ids.length; i++) {
			mDbConnector.addItemInTestDB(ids[i], passes[i], names[i], exps[i]);
		}
	}

	private void makeListView() {
		Cursor c = mDbConnector.getDataFromTestDB();
		ListView listView = (ListView) findViewById(R.id.listview);
		mAdapter = new CustomCursorAdapter(this, c, false);
		listView.setAdapter(mAdapter);
	}

}
