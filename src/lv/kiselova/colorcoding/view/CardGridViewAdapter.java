package lv.kiselova.colorcoding.view;

import java.util.List;

import lv.kiselova.colorcoding.R;
import lv.kiselova.colorcoding.model.Cell;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

public class CardGridViewAdapter extends BaseAdapter {
	private Context context;
	private final List<Cell> values;
 
	public CardGridViewAdapter(Context context, List<Cell> values) {
		this.context = context;
		this.values = values;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) { 
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from item.xml
			gridView = inflater.inflate(R.layout.item, null);
 
			// set value into textview
			EditText textView = (EditText) gridView.findViewById(R.id.cellText);
			
			textView.setText(values.get(position).getValue());			
			textView.setBackgroundColor(Color.parseColor(values.get(position).getColor()));
			textView.setTextColor(Color.BLACK);
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	
	public int getCount() {
		return values.size();
	}
 
	
	public Object getItem(int position) {
		return null;
	}
 
	
	public long getItemId(int position) {
		return 0;
	}
 
}
