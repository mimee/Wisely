package com.timelord;

import java.sql.SQLException;
import java.util.List;

import android.view.View;
import android.widget.Button;

public class ListActivity extends BaseList {

	@Override
	protected int getContentView() {
		return R.layout.activities;
	}

	@Override
	protected List<?> getRowObjects() throws SQLException {
		return getHelper().getActivityDao().queryForAll();
	}

	@Override
	protected Button getButton() {
		return (Button) findViewById(R.id.newActivityButton);
	}

	public void startActivityHandler(View view) {

	}

	public void editActivityHandler(View view) {

	}

	public void deleteActivityHandler(View view) {

	}

	@Override
	protected Class<?> getEntryClass() {
		return NewActivity.class;
	}

	@Override
	protected int getRowLayout() {
		return R.layout.activity_row;
	}

	@Override
	protected int[] getRowId() {
		return new int[] { R.id.activityRow };
	}
}
