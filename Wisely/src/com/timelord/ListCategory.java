package com.timelord;

import java.sql.SQLException;
import java.util.List;

import android.view.View;
import android.widget.Button;

public class ListCategory extends BaseList {

	@Override
	protected int getContentView() {
		return R.layout.categories;
	}

	@Override
	protected Button getButton() {
		return (Button) findViewById(R.id.newCategoryButton);
	}

	public void editCategoryHandler(View view) {

	}

	public void deleteCategoryHandler(View view) {

	}

	@Override
	protected Class<?> getEntryClass() {
		return NewCategory.class;
	}

	@Override
	protected int getRowLayout() {
		return R.layout.category_row;
	}

	@Override
	protected List<?> getRowObjects() throws SQLException {
		return getHelper().getCategoryDao().queryForAll();
	}

	@Override
	protected int[] getRowId() {
		return new int[] { R.id.categoryRow };
	}
}
