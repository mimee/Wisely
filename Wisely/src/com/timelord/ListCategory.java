package com.timelord;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.timelord.pojo.Category;

public class ListCategory extends BaseListActivity {

	@Override
	protected int getContentView() {
		return R.layout.categories;
	}

	@Override
	protected void fillData() {
		String[] titles = new String[] { "categoryName" };
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		List<Category> categories;
		try {
			categories = getHelper().getCategoryDao().queryForAll();
			HashMap<String, String> map = new HashMap<String, String>();
			for (Category category : categories) {
				map.put("categoryName", category.getName());
			}
			data.add(map);
		} catch (SQLException e) {
			Log.e(ListActivity.class.getName(), e.getMessage(), e);
		}

		int[] to = new int[] { R.id.categoryRow };
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, data,
				R.layout.category_row, titles, to);
		setListAdapter(simpleAdapter);

	}

	@Override
	protected Button getButton() {
		return (Button) findViewById(R.id.newCategoryButton);
	}

}
