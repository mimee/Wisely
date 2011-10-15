package com.timelord.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.timelord.R;
import com.timelord.pojo.Activity;
import com.timelord.pojo.Category;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "wisely";

	private static final int DATABASE_VERSION = 1;

	private Dao<Activity, Integer> activityDao = null;

	private Dao<Category, Integer> categoryDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Activity.class);
			TableUtils.createTable(connectionSource, Category.class);

			Dao<Category, Integer> categoryDao = getCategoryDao();
			Category category = new Category();
			category.setName("entertainment");
			categoryDao.create(category);
			// here we try inserting data in the on-create as a test
			Dao<Activity, Integer> activityDao = getActivityDao();
			// create some entries in the onCreate
			Activity activity = new Activity();
			activity.setName("watch muvee");
			activity.setCategory(category);
			activityDao.create(activity);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	public Dao<Activity, Integer> getActivityDao() throws SQLException {
		if (activityDao == null) {
			activityDao = getDao(Activity.class);
		}
		return activityDao;
	}

	public Dao<Category, Integer> getCategoryDao() throws SQLException {
		if (categoryDao == null) {
			categoryDao = getDao(Category.class);
		}
		return categoryDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		activityDao = null;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Activity.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

}
