package lv.kiselova.colorcoding.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 
 * @author marykiselova
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String TEXT_TYPE = " TEXT ";
	private static final String INT_TYPE = " INTEGER ";
	private static final String COMMA_SEP = " , ";
	
	
	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_NAME = "Colorcoding.db";
	
	public static final String SQL_CREATE_CARD_TABLE = 
	"CREATE TABLE "	+ DatabaseContract.CardEntity.TABLE_NAME + " ("	+
		DatabaseContract.CardEntity.TABLE_NAME + DatabaseContract.CardEntity._ID + " INTEGER PRIMARY KEY, " +
		DatabaseContract.CardEntity.COLUMN_NAME_CARD_NAME + TEXT_TYPE + COMMA_SEP +
		DatabaseContract.CardEntity.COLUMN_NAME_CREATED_ON + TEXT_TYPE + COMMA_SEP +
		DatabaseContract.CardEntity.COLUMN_NAME_CARD_SIZE_X + INT_TYPE + COMMA_SEP +
		DatabaseContract.CardEntity.COLUMN_NAME_CARD_SIZE_Y + INT_TYPE + 
	" ) ";
	
    public static final String SQL_CREATE_CELL_TABLE = 
	"CREATE TABLE "	+ DatabaseContract.CellEntity.TABLE_NAME + " ("	+
		DatabaseContract.CellEntity.TABLE_NAME + DatabaseContract.CellEntity._ID + " INTEGER PRIMARY KEY, " +
		DatabaseContract.CellEntity.COLUMN_NAME_CELL_COLOR + TEXT_TYPE + COMMA_SEP +
		DatabaseContract.CellEntity.COLUMN_NAME_CELL_X + INT_TYPE + COMMA_SEP +
		DatabaseContract.CellEntity.COLUMN_NAME_CELL_Y + INT_TYPE + COMMA_SEP +
		DatabaseContract.CellEntity.COLUMN_NAME_CELL_VALUE + TEXT_TYPE + COMMA_SEP +
		DatabaseContract.CellEntity.COLUMN_NAME_CELL_CARD_ID + INT_TYPE + 
	" ) ";
	
	private static final String SQL_DELETE_ENTRIES = 
	"DROP TABLE IF EXISTS "	+ DatabaseContract.CardEntity.TABLE_NAME + ";" +
	"DROP TABLE IF EXISTS "	+ DatabaseContract.CellEntity.TABLE_NAME + ";";	

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_CARD_TABLE);
		db.execSQL(SQL_CREATE_CELL_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
	
	

}
