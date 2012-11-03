package lv.kiselova.colorcoding.dao;

import java.util.ArrayList;

import lv.kiselova.colorcoding.db.DatabaseContract;
import lv.kiselova.colorcoding.db.DatabaseHelper;
import lv.kiselova.colorcoding.model.Cell;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CellDAO {
	private static String LOG_TAG = "cellDAO";
	
	private DatabaseHelper dbHelper;

	public CellDAO(DatabaseHelper helper) {
		super();
		this.dbHelper = helper;
	}

	public long createCell (String color, String value, int x, int y, int cardId){
		SQLiteDatabase db  = this.dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();		
		values.put(DatabaseContract.CellEntity.COLUMN_NAME_CELL_COLOR, color);
		values.put(DatabaseContract.CellEntity.COLUMN_NAME_CELL_VALUE, value);
		values.put(DatabaseContract.CellEntity.COLUMN_NAME_CELL_X, x);
		values.put(DatabaseContract.CellEntity.COLUMN_NAME_CELL_Y, y);
		values.put(DatabaseContract.CellEntity.COLUMN_NAME_CELL_CARD_ID, cardId);
		
		long id = db.insert(
				DatabaseContract.CellEntity.TABLE_NAME, 
				DatabaseContract.CellEntity.COLUMN_NAME_NULLABLE, 
				values);
		Log.v(LOG_TAG, "created new cell id:" + id);
		return id;
	}
	
	public void deleteCell (int id){
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		
		String selection = DatabaseContract.CellEntity.COLUMN_NAME_ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(id)};
		
		db.delete(DatabaseContract.CellEntity.TABLE_NAME, selection, selectionArgs);
		Log.v(LOG_TAG, "Cell with id:" + id + " deleted");
	}
	
	public void deleteAll (){
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		
		db.delete(DatabaseContract.CellEntity.TABLE_NAME, null, null);		
	}
	
	public ArrayList<Cell> listCells (){
		SQLiteDatabase db  = this.dbHelper.getReadableDatabase();
		
		String[] projection = {
			DatabaseContract.CellEntity.COLUMN_NAME_ID,
			DatabaseContract.CellEntity.COLUMN_NAME_CELL_COLOR,
			DatabaseContract.CellEntity.COLUMN_NAME_CELL_VALUE,
			DatabaseContract.CellEntity.COLUMN_NAME_CELL_X,
			DatabaseContract.CellEntity.COLUMN_NAME_CELL_Y,
			DatabaseContract.CellEntity.COLUMN_NAME_CELL_CARD_ID
		};
		
		String sortOrder = DatabaseContract.CellEntity.COLUMN_NAME_CELL_CARD_ID + " DESC ";
		Cursor cursor = db.query(
				DatabaseContract.CellEntity.TABLE_NAME,
				projection,
				null,
				null,
				null,
				null,
				sortOrder);
		
		Log.v(LOG_TAG, "Loaded cells count: " + cursor.getCount());
		
		ArrayList<Cell> result = new ArrayList<Cell>();
		
		// iterate and create cell objects list
		if (cursor != null) {

	        //more to the first row
	        cursor.moveToFirst();

	        //iterate over rows
	        for (int i = 0; i < cursor.getCount(); i++) {
	        	Cell cell = new Cell();
	        	
	        	int index = cursor.getColumnIndexOrThrow(DatabaseContract.CellEntity.COLUMN_NAME_ID);
	        	cell.setId(cursor.getInt(index));
	        	
	        	index = cursor.getColumnIndexOrThrow(DatabaseContract.CellEntity.COLUMN_NAME_CELL_COLOR);
	        	cell.setColor(cursor.getString(index));
	        	
	        	index = cursor.getColumnIndexOrThrow(DatabaseContract.CellEntity.COLUMN_NAME_CELL_VALUE);
	        	cell.setValue(cursor.getString(index));
	        	
	        	index = cursor.getColumnIndexOrThrow(DatabaseContract.CellEntity.COLUMN_NAME_CELL_X);
	        	cell.setX(cursor.getInt(index));
	        	
	        	index = cursor.getColumnIndexOrThrow(DatabaseContract.CellEntity.COLUMN_NAME_CELL_Y);
	        	cell.setY(cursor.getInt(index));
	        	
	        	index = cursor.getColumnIndexOrThrow(DatabaseContract.CellEntity.COLUMN_NAME_CELL_CARD_ID);
	        	cell.setCardId(cursor.getInt(index));
	        	
	        	result.add(cell);	
	        	Log.v(LOG_TAG, "Loaded cell -> "+cell);
	            //move to the next row
	            cursor.moveToNext();
	        }
	        //close the cursor
	        cursor.close();
	    }
		return result;		
	}

}
