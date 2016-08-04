package com.example.sqldeleteexample;

import com.example.sqldeleteexample.Constants.TestEntry;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {

	private LayoutInflater mLayoutInflater;

	public CustomCursorAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public void bindView(View view, Context arg1, Cursor c) {
		TextView id = (TextView) view.findViewById(R.id.id);
		TextView pass = (TextView) view.findViewById(R.id.pass);
		TextView name = (TextView) view.findViewById(R.id.name);
		TextView exp = (TextView) view.findViewById(R.id.exp);
		
		id.setText(c.getString(c.getColumnIndex(TestEntry.COLUMN_NAME_ID)));
		pass.setText(c.getString(c.getColumnIndex(TestEntry.COLUMN_NAME_PASS)));
		name.setText(c.getString(c.getColumnIndex(TestEntry.COLUMN_NAME_NAME)));
		exp.setText("" + c.getInt(c.getColumnIndex(TestEntry.COLUMN_NAME_EXP)));
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup parent) {
		return mLayoutInflater.inflate(R.layout.listview_item, parent, false);
	}

}
