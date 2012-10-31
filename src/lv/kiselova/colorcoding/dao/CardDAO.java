package lv.kiselova.colorcoding.dao;

import java.util.ArrayList;

import lv.kiselova.colorcoding.db.DatabaseContract;
import lv.kiselova.colorcoding.db.DatabaseHelper;
import lv.kiselova.colorcoding.model.Card;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CardDAO {
	private static String LOG_TAG = "cardDAO";
	
	private DatabaseHelper dbHelper;

	public CardDAO(DatabaseHelper helper) {
		super();
		this.dbHelper = helper;
	}

	public long createCard (String name, int sizeX, int sizeY){
		SQLiteDatabase db  = this.dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();		
		values.put(DatabaseContract.CardEntity.COLUMN_NAME_CARD_NAME, name);
		values.put(DatabaseContract.CardEntity.COLUMN_NAME_CARD_SIZE_X, sizeX);
		values.put(DatabaseContract.CardEntity.COLUMN_NAME_CARD_SIZE_Y, sizeY);
		
		long id = db.insert(
				DatabaseContract.CardEntity.TABLE_NAME, 
				DatabaseContract.CardEntity.COLUMN_NAME_NULLABLE, 
				values);
		Log.v(LOG_TAG, "created new card id:" + id);
		return id;
	}
	
	public void deleteCard (int id){
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		
		String selection = DatabaseContract.CardEntity.COLUMN_NAME_ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(id)};
		
		db.delete(DatabaseContract.CardEntity.TABLE_NAME, selection, selectionArgs);
		Log.v(LOG_TAG, "Card with id:" + id + " deleted");
	}
	
	public ArrayList<Card> listCards (){
		SQLiteDatabase db  = this.dbHelper.getReadableDatabase();
		
		String[] projection = {
			DatabaseContract.CardEntity.COLUMN_NAME_ID,
			DatabaseContract.CardEntity.COLUMN_NAME_CARD_NAME,
			DatabaseContract.CardEntity.COLUMN_NAME_CARD_SIZE_X,
			DatabaseContract.CardEntity.COLUMN_NAME_CARD_SIZE_Y
		};
		
		String sortOrder = DatabaseContract.CardEntity.COLUMN_NAME_CARD_NAME + " DESC ";
		Cursor cursor = db.query(
				DatabaseContract.CardEntity.TABLE_NAME,
				projection,
				null,
				null,
				null,
				null,
				sortOrder);
		
		Log.v(LOG_TAG, "Loaded cards count: " + cursor.getCount());
		
		ArrayList<Card> result = new ArrayList<Card>();
		
		// iterate and create card objects list
		if (cursor != null) {

	        //more to the first row
	        cursor.moveToFirst();

	        //iterate over rows
	        for (int i = 0; i < cursor.getCount(); i++) {
	        	Card card = new Card();
	        	
	        	int index = cursor.getColumnIndexOrThrow(DatabaseContract.CardEntity.COLUMN_NAME_ID);
	        	card.setId(cursor.getInt(index));
	        	
	        	index = cursor.getColumnIndexOrThrow(DatabaseContract.CardEntity.COLUMN_NAME_CARD_NAME);
	        	card.setName(cursor.getString(index));
	        	
	        	index = cursor.getColumnIndexOrThrow(DatabaseContract.CardEntity.COLUMN_NAME_CARD_SIZE_X);
	        	card.setSizeX(cursor.getInt(index));
	        	
	        	index = cursor.getColumnIndexOrThrow(DatabaseContract.CardEntity.COLUMN_NAME_CARD_SIZE_Y);
	        	card.setSizeY(cursor.getInt(index));
	        	
	        	result.add(card);	
	        	Log.v(LOG_TAG, "Loaded card -> "+card);
	            //move to the next row
	            cursor.moveToNext();
	        }
	        //close the cursor
	        cursor.close();
	    }
		return result;		
	}

}
