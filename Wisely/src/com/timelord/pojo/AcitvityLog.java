package com.timelord.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DatabaseFieldForeign;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "activityLogs")
public class AcitvityLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(canBeNull = false)
	@DatabaseFieldForeign(foreign = true)
	private Activity activity;

	@DatabaseField(dataType = DataType.DATE, canBeNull = false)
	private Timestamp start;

	@DatabaseField(dataType = DataType.DATE, canBeNull = true)
	private Timestamp end;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}
}
