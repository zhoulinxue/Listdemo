package com.zhx.listdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class SignChooseActivity extends Activity {
	private ListView mListView;
	private TextAdapter mAdapter;
	private List<String> mList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);
		mListView = (ListView) findViewById(R.id.text_listview);
		for (int i = 0; i < 20; i++) {
			mList.add("去约车" + i);
		}
		mAdapter = new TextAdapter(mList, this);
		mListView.setAdapter(mAdapter);

	}
}
