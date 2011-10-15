package com.timelord;

import java.sql.SQLException;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import com.timelord.dao.DatabaseHelper;

public abstract class BaseList extends OrmLiteBaseListActivity<DatabaseHelper>
		implements OnClickListener {

	protected abstract int getContentView();

	protected abstract Button getButton();

	protected abstract Class<?> getEntryClass();

	protected abstract int getRowLayout();

	protected abstract int[] getRowId();

	protected abstract List<?> getRowObjects() throws SQLException;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());
		getButton().setOnClickListener(this);
		refreshData();
	}

	private void refreshData() {
		try {
			setListAdapter(new EntityAdapter().getEntityAdapter(this,
					getRowObjects(), getRowLayout(), getRowId()));
		} catch (SQLException e) {
			Log.e(BaseList.class.getName(), e.getMessage(), e);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		refreshData();
	}

	public void onClick(View v) {
		Intent i = new Intent(this, getEntryClass());
		startActivity(i);
	}
}