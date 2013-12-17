package com.kysoprod.autoparcel;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.kysoprod.autoparcel.entities.DumbClass;

public class OtherActivity extends ListActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Try to retreive a list of items sent as a parcel ArrayList
		ArrayList<DumbClass> items = getIntent().getParcelableArrayListExtra("items");
		if( items == null ){
			items = new ArrayList<DumbClass>();
		}
		
		// Try to retreive an item sent as a parcel 
		DumbClass item = getIntent().getParcelableExtra("item");
		if( item != null ){
			items.add(item);
		}
		
		setListAdapter(new ArrayAdapter<DumbClass>(this, android.R.layout.simple_list_item_1, items));
	}

	



}
