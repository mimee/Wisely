package com.timelord;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.util.Log;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.timelord.pojo.Activity;

public class ListActivity extends BaseListActivity {

	@Override
	protected int getContentView() {
		return R.layout.activities;
	}

	@Override
	protected void fillData() {
		String[] titles = new String[] { "activityName" };
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		List<Activity> activities;
		try {
			activities = getHelper().getActivityDao().queryForAll();
			HashMap<String, String> map = new HashMap<String, String>();
			for (Activity activity : activities) {
				map.put("activityName", activity.getName());
			}
			data.add(map);
		} catch (SQLException e) {
			Log.e(ListActivity.class.getName(), e.getMessage(), e);
		}

		int[] to = new int[] { R.id.activityRow };
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, data,
				R.layout.activity_row, titles, to);
		setListAdapter(simpleAdapter);

	}

	@Override
	protected Button getButton() {
		return (Button) findViewById(R.id.newActivityButton);
	}

}
