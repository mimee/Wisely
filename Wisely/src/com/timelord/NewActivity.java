package com.timelord;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.timelord.dao.DatabaseHelper;
import com.timelord.pojo.Category;

public class NewActivity extends OrmLiteBaseActivity<DatabaseHelper> {
	private Button saveButton;
	private Spinner categorySpinner;
	private EditText activityName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_activity);
		saveButton = (Button) findViewById(R.id.saveButton);
		categorySpinner = (Spinner) findViewById(R.id.categorySelect);
		List<Category> categories;
		try {
			categories = getHelper().getCategoryDao().queryForAll();
			String[] categoryNames = new String[categories.size()];
			int i = 0;
			for (Category category : categories) {
				categoryNames[i++] = category.getName();
			}
			// categorySpinner.setPrompt(categoryNames);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		activityName = (EditText) findViewById(R.id.activityNameEditText);
	}
}
