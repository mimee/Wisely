package com.timelord;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.timelord.dao.DatabaseHelper;

public abstract class BaseEntry extends OrmLiteBaseActivity<DatabaseHelper>
		implements OnClickListener {

	private Button saveButton;

	private EditText nameEdit;

	protected abstract int getSaveButtonId();

	protected abstract int getNameEditId();

	protected abstract int getContentView();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());
		saveButton = (Button) findViewById(getSaveButtonId());
		saveButton.setOnClickListener(this);
		nameEdit = (EditText) findViewById(getNameEditId());
	}

	protected String getNameEntried() {
		return nameEdit.getText().toString();
	}

}