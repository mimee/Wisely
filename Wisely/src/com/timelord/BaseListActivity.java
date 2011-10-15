package com.timelord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import com.timelord.dao.DatabaseHelper;

public abstract class BaseListActivity extends
		OrmLiteBaseListActivity<DatabaseHelper> implements OnClickListener {

	protected abstract int getContentView();

	protected abstract Button getButton();

	protected abstract void fillData();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());

		fillData();

		getButton().setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent i = new Intent(this, NewCategory.class);
		startActivity(i);
	}
}