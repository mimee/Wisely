package com.timelord;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.timelord.pojo.Activity;

public class ListActivity extends BaseList {

	@Override
	protected int getContentView() {
		return R.layout.activities;
	}

	@Override
	protected List<?> getRowObjects() {
		return getHelper().getAllObjects(Activity.class);
	}

	@Override
	protected Button getButton() {
		return (Button) findViewById(R.id.newActivityButton);
	}

	public void startActivityHandler(View view) {

	}

	public void editActivityHandler(View view) {
		Intent i = new Intent(this, getEntryClass());
		i.putExtra("selectedItem", getSelectedItem(view));
		startActivity(i);
	}

	public void deleteActivityHandler(View view) {
		String name = getSelectedItem(view);
		Activity activity = (Activity) getHelper().getObjectByName(name,
				Activity.class);
		getHelper().deleteObject(activity, Activity.class);
		refreshData();
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
