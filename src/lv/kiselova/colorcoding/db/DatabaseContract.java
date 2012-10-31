package lv.kiselova.colorcoding.db;

import android.provider.BaseColumns;
/**
 * 
 * @author marykiselova
 *
 */
public class DatabaseContract {
	
	public static abstract class CardEntity implements BaseColumns {
		public static final String TABLE_NAME = "card";		
		public static final String COLUMN_NAME_ID = TABLE_NAME + _ID;
		public static final String COLUMN_NAME_CARD_NAME = "name";
		public static final String COLUMN_NAME_CREATED_ON = "createdOn";
		public static final String COLUMN_NAME_CARD_SIZE_X = "sizeX";
		public static final String COLUMN_NAME_CARD_SIZE_Y = "sizeY";	
		public static final String COLUMN_NAME_NULLABLE = COLUMN_NAME_CREATED_ON;
	}
	
	public static abstract class CellEntity implements BaseColumns {
		public static final String TABLE_NAME = "cell";	
		public static final String COLUMN_NAME_ID = TABLE_NAME + _ID;
		public static final String COLUMN_NAME_CELL_COLOR = "color";
		public static final String COLUMN_NAME_CELL_X = "x";
		public static final String COLUMN_NAME_CELL_Y = "y";
		public static final String COLUMN_NAME_CELL_VALUE = "value";	
		public static final String COLUMN_NAME_CELL_CARD_ID = "cardId";
		public static final String COLUMN_NAME_NULLABLE = COLUMN_NAME_CELL_VALUE;
	}

	private DatabaseContract() {
		super();		
	}
	
}
