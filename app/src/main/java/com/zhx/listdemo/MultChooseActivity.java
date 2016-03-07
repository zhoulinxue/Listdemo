package com.zhx.listdemo;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MultChooseActivity extends Activity {
	private Button mBack;
	private Button mAll;
	private GridView mDisplay;
	private TextView mCount;
	private AlbumAdapter mAdapter;

	private List<String> mSelect = new ArrayList<String>();// 存放当前用户选择的图片的编号
	private int imagesId[] = { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,R.drawable.ic_launcher, R.drawable.ic_launcher };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.album_activity);
		findViewById();
		setListener();
		init();

	}

	private void findViewById() {
		mBack = (Button) findViewById(R.id.album_back);
		mAll = (Button) findViewById(R.id.album_all);
		mDisplay = (GridView) findViewById(R.id.album_display);
		mCount = (TextView) findViewById(R.id.album_count);
	}

	private void setListener() {
		mBack.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 关闭当前界面
				finish();
			}
		});
		mAll.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (mSelect.size() == imagesId.length) {
					// 全部选中时,点击为反选,所以清空
					mSelect.clear();
				} else if (mSelect.size() > 0) {
					// 当已选中时,点击为全选,添加没选择中的
					for (int i = 0; i < imagesId.length; i++) {
						if (mSelect.contains(String.valueOf(i))) {
							continue;
						}
						mSelect.add(String.valueOf(i));
					}
				} else {
					// 没选中,点击为全选,添加所有
					for (int i = 0; i < imagesId.length; i++) {
						mSelect.add(String.valueOf(i));
					}
				}
				// 如果全部选中则显示反选,否则显示全选
				if (mSelect.size() == imagesId.length) {
					mAll.setText("反选");
				} else {
					mAll.setText("全选");
				}
				// 更新界面显示数量内容
				initCount(mSelect.size());
				// 刷新界面
				mAdapter.notifyDataSetChanged();
			}
		});
		mDisplay.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// 查看当前点击的条目是否已经选中,选中则移出,没选中则删除
				if (mSelect.contains(String.valueOf(arg2))) {
					mSelect.remove(String.valueOf(arg2));
				} else {
					mSelect.add(String.valueOf(arg2));
				}
				// 如果全部选中则显示反选,否则显示全选
				if (mSelect.size() == imagesId.length) {
					mAll.setText("反选");
				} else {
					mAll.setText("全选");
				}
				// 更新界面显示数量内容
				initCount(mSelect.size());
				// 刷新界面
				mAdapter.notifyDataSetChanged();
			}
		});
	}

	private void init() {
		// 更新界面显示数量内容
		initCount(mSelect.size());
		// 初始化适配器
		mAdapter = new AlbumAdapter();
		// 添加适配器
		mDisplay.setAdapter(mAdapter);
	}

	/**
	 * 更新界面显示数量内容
	 * 
	 * @param count
	 *            选中的图片数量
	 */
	private void initCount(int count) {
		// 更新选择的图片数量,如果数量大于0,设置确定按钮为可用,反之不可用并修改字体颜色
		if (count > 0) {
			mCount.setText("已选择" + count + "张");
			mCount.setTextColor(0xFFFFFFFF);
		} else {
			mCount.setText("已选择0张");
			mCount.setTextColor(0xFF999999);
		}
	}

	private class AlbumAdapter extends BaseAdapter {

		public int getCount() {
			return imagesId.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(MultChooseActivity.this).inflate(R.layout.album_activity_item, null);
				holder = new ViewHolder();
				holder.photo = (ImageView) convertView.findViewById(R.id.album_item_photo);
				holder.select = (ImageView) convertView.findViewById(R.id.album_item_select);
				// padding大小为40dip,计算px值
				int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, MultChooseActivity.this
						.getResources().getDisplayMetrics());
				// 设置显示的图片大小为屏幕宽度1/4大小
				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.width = (480 - padding) / 4;
				params.height = (480 - padding) / 4;
				holder.photo.setLayoutParams(params);
				holder.select.setLayoutParams(params);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			// 添加图片
			holder.photo.setImageBitmap(BitmapFactory.decodeResource(getResources(), imagesId[position]));
			// 查看是否选中,选中则显示选中效果
			if (mSelect.contains(String.valueOf(position))) {
				holder.select.setVisibility(View.VISIBLE);
			} else {
				holder.select.setVisibility(View.GONE);
			}
			return convertView;
		}

		class ViewHolder {
			ImageView photo;
			ImageView select;
		}
	}
}

