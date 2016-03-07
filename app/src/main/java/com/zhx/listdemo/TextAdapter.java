package com.zhx.listdemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class TextAdapter extends BaseAdapter {
	private List<String> list;
	private Activity activity;
	private Map<String, Boolean> isSelectedMap = new HashMap<String, Boolean>();

	public TextAdapter(List<String> list, Activity activity) {
		super();
		this.list = list;
		this.activity = activity;
		for (int i = 0; i < list.size(); i++) {
			isSelectedMap.put(i + "", false);
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = activity.getLayoutInflater().inflate(
					R.layout.test_item, null);
			holder.tv = (TextView) convertView.findViewById(R.id.test_tv);
			holder.box = (ImageView) convertView
					.findViewById(R.id.test_check_box);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				for (int i = 0; i < list.size(); i++) {
					if (i == position) {
						Log.i("点击项目", position + "");
						isSelectedMap.put(i + "", true);
					} else {
						Log.i("未点击项目", position + "");
						isSelectedMap.put(i + "", false);
					}
				}
				notifyDataSetChanged();
			}
		});
		Log.i("选中项", position + "@" + isSelectedMap.get(position + "") + "");
		if (isSelectedMap.get(position + "")) {
			holder.box.setImageResource(R.drawable.multi_select_flag);
		} else {
			holder.box.setImageResource(R.drawable.ic_launcher);
		}
		holder.tv.setText(list.get(position));
		return convertView;
	}

	class ViewHolder {
		TextView tv;
		ImageView box;
	}

}







