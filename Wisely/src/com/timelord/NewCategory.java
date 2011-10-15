package com.timelord;

import java.sql.SQLException;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.timelord.dao.DatabaseHelper;
import com.timelord.pojo.Category;

public class NewCategory extends OrmLiteBaseActivity<DatabaseHelper> implements
		OnClickListener {
	private Button mSaveButton;
	private EditText categoryNameEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_category);
		categoryNameEditText = (EditText) findViewById(R.id.categoryNameEditText);
		mSaveButton = (Button) findViewById(R.id.saveButton);
		mSaveButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		Category category = new Category();
		category.setName(categoryNameEditText.getText().toString());
		try {
			getHelper().getCategoryDao().create(category);
		} catch (SQLException e) {
			Log.e(NewCategory.class.getName(), e.getMessage(), e);
		}
	}

}
