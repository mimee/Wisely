package com.timelord;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import com.timelord.dao.DatabaseHelper;
import com.timelord.pojo.Activity;

public class WiselyActivity extends OrmLiteBaseListActivity<DatabaseHelper>
		implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		try {
			fillData();
		} catch (SQLException e) {
			Log.e(WiselyActivity.class.getName(), e.getMessage(), e);
		}
		Button newCategory = (Button) findViewById(R.id.newCategoryButton);
		newCategory.setOnClickListener(this);
	}

	private void fillData() throws SQLException {
		String[] titles = new String[] { "activityName" };
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		List<Activity> activities = getHelper().getActivityDao().queryForAll();
		HashMap<String, String> map = new HashMap<String, String>();
		for (Activity activity : activities) {
			map.put("activityName", activity.getName());
		}
		data.add(map);

		int[] to = new int[] { R.id.tvViewRow };
		SimpleAdapter colours = new SimpleAdapter(this, data,
				R.layout.activity_row, titles, to);
		setListAdapter(colours);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.main_menu, menu);
		return true;
	}

	public void onClick(View v) {
		Intent i = new Intent(this, NewCategory.class);
        startActivity(i);
	}
}