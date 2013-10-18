package com.barchart.tester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListView;

/**
 * Demo application showing how to mimic "Gallery" like behavior.
 * 
 * @author m-ehrenberg
 * 
 */
public class MainActivity extends Activity {

	private ExpandableListView symbol_list;

	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> symbolCollection = new HashMap<String, List<String>>();
	ExpandableListView expListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		symbol_list = (ExpandableListView) findViewById(R.id.symbol_list);

		groupList = new ArrayList<String>();

		groupList.add("MSFT");
		groupList.add("YHOO");
		groupList.add("GOOG");
		groupList.add("NEM");
		groupList.add("EXC");
		groupList.add("IBM");

		childList = new ArrayList<String>();
		childList.add("single_row_tabber");

		symbolCollection.put("MSFT", childList);
		symbolCollection.put("YHOO", childList);
		symbolCollection.put("GOOG", childList);
		symbolCollection.put("NEM", childList);
		symbolCollection.put("EXC", childList);
		symbolCollection.put("IBM", childList);

		final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
				this, groupList, symbolCollection);

		symbol_list.setAdapter(expListAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
