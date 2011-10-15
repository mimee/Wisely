package com.timelord;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.timelord.pojo.Activity;
import com.timelord.pojo.Category;

public class NewActivity extends BaseEntry {
	private Spinner categorySpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		categorySpinner = (Spinner) findViewById(R.id.categorySelect);
		try {
			List<CharSequence> categoryNames = new ArrayList<CharSequence>();
			List<Category> categories = getHelper().getCategoryDao()
					.queryForAll();
			for (Category category : categories) {
				categoryNames.add(category.getName());
			}
			ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<CharSequence>(
					this, android.R.layout.simple_spinner_dropdown_item,
					categoryNames);
			categorySpinner.setAdapter(arrayAdapter);
		} catch (SQLException e) {
			Log.e(NewActivity.class.getName(), e.getMessage(), e);
		}
	}

	public void onClick(View v) {
		Activity activity = new Activity();
		activity.setName(getNameEntried());
		String categoryName = categorySpinner.getSelectedItem().toString();
		try {
			Category category = (Category) getHelper().getCategoryDao()
					.queryForEq("name", categoryName).get(0);
			activity.setCategory(category);
			getHelper().getActivityDao().create(activity);
		} catch (SQLException e) {
			Log.e(NewCategory.class.getName(), e.getMessage(), e);
		}
		finish();
	}

	@Override
	protected int getContentView() {
		return R.layout.new_activity;
	}

	@Override
	protected int getSaveButtonId() {
		return R.id.activitySaveButton;
	}

	@Override
	protected int getNameEditId() {
		return R.id.activityNameEdit;
	}
}
