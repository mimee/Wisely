package com.timelord;

import java.sql.SQLException;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.timelord.pojo.Category;

public class NewCategory extends BaseEntry {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void onClick(View v) {
		Category category = new Category();
		category.setName(getNameEntried());
		Log.i(NewCategory.class.getName(), getNameEntried());
		try {
			getHelper().getCategoryDao().create(category);
		} catch (SQLException e) {
			Log.e(NewCategory.class.getName(), e.getMessage(), e);
		}
		finish();
	}

	@Override
	protected int getContentView() {
		return R.layout.new_category;
	}

	@Override
	protected int getSaveButtonId() {
		return R.id.categorySaveButton;
	}

	@Override
	protected int getNameEditId() {
		return R.id.categoryNameEdit;
	}

}
