package com.kysoprod.autoparcel;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kysoprod.autoparcel.demo.R;
import com.kysoprod.autoparcel.entities.DumbClass;

public class MainActivity extends Activity {

	private ArrayList<DumbClass> items;

	private CheckBox chkMainBoolean;
	private EditText edtMainNumeric;
	private EditText edtMainDecimal;
	private EditText edtMainText;
	private ImageButton ibtMainSendItem;
	private TextView txtMainItems;
	private ImageButton ibtMainAddItem;
	private ImageButton ibtMainSendItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		items = new ArrayList<DumbClass>();

		chkMainBoolean = (CheckBox) findViewById(R.id.chkMainBoolean);
		edtMainNumeric = (EditText) findViewById(R.id.edtMainNumeric);
		edtMainDecimal = (EditText) findViewById(R.id.edtMainDecimal);
		edtMainText = (EditText) findViewById(R.id.edtMainText);

		ibtMainSendItem = (ImageButton) findViewById(R.id.ibtMainSendItem);

		txtMainItems = (TextView) findViewById(R.id.txtMainItems);
		ibtMainAddItem = (ImageButton) findViewById(R.id.ibtMainAddItem);
		ibtMainSendItems = (ImageButton) findViewById(R.id.ibtMainSendItems);

		ibtMainSendItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
				intent.putExtra("item", getItem());
				startActivity(intent);
			}
		});

		ibtMainAddItem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				items.add(getItem());
				updateUI();
			}
		});

		ibtMainSendItems.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
				intent.putParcelableArrayListExtra("items", items);
				startActivity(intent);
			}
		});

	}

	private void updateUI() {
		if (items.size() == 0) {
			txtMainItems.setText("You have nothing to send other activity");
			ibtMainSendItems.setEnabled(false);
		} else {
			ibtMainSendItems.setEnabled(true);
			if (items.size() == 1) {
				txtMainItems.setText("You have 1 item to send other activity");
			} else {
				txtMainItems.setText("You have " + items.size() + " items to send other activity");
			}
		}
	}

	private DumbClass getItem() {
		DumbClass item = new DumbClass();
		item.setVarBool(chkMainBoolean.isChecked());
		try {
			item.setVarInteger(Integer.parseInt(edtMainNumeric.getText().toString()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			item.setVarLong(Long.parseLong(edtMainNumeric.getText().toString()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			item.setVarDouble(Double.parseDouble(edtMainDecimal.getText().toString()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		item.setVarString(edtMainText.getText().toString());
		return item;
	}

}
