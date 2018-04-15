package com.libaoming.demoexce.demoexce.view.dragview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.libaoming.demoexce.demoexce.R;

import java.util.List;

public class DragAdapter extends BaseAdapter {

	/** 是否显示底部的ITEM */
	private boolean isItemShow = false;
	private Context context;
	/** 控制的position */
	private int holdPosition;
	/** 是否改变 */
	private boolean isChanged = false;
	/** 是否可见 */
	boolean isVisible = true;
	/** 可以拖动的列表（即用户选择的列表） */
	public List<GridViewItem> gridViewList;
	/** ImageView 图片 */
	private ImageView img_item;
	/** 要删除的position */
	public int remove_position = -1;

	public DragAdapter(Context context, List<GridViewItem> gridViewList) {
		this.context = context;
		this.gridViewList = gridViewList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return gridViewList == null ? 0 : gridViewList.size();
	}

	@Override
	public GridViewItem getItem(int position) {
		// TODO Auto-generated method stub
		if (gridViewList != null && gridViewList.size() != 0) {
			return gridViewList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.grid_item, null);

		GridViewItem gridViewItem = getItem(position);
		img_item = (ImageView) view.findViewById(R.id.img_item);
		img_item.setImageResource(gridViewItem.getIconId());
//		img_item.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(context,"111",Toast.LENGTH_SHORT).show();
//			}
//		});

		if (isChanged && (position == holdPosition) && !isItemShow) {
			isChanged = false;
		}
		return view;
	}

	/** 添加列表 */
	public void addItem(GridViewItem channel) {
		gridViewList.add(channel);
		notifyDataSetChanged();
	}

	/** 拖动变更排序 */
	public void exchange(int dragPostion, int dropPostion) {
		holdPosition = dropPostion;
		GridViewItem dragItem = getItem(dragPostion);

		if (dragPostion < dropPostion) {
			gridViewList.add(dropPostion + 1, dragItem);
			gridViewList.remove(dragPostion);
		} else {
			gridViewList.add(dropPostion, dragItem);
			gridViewList.remove(dragPostion + 1);
		}
		isChanged = true;
		notifyDataSetChanged();
	}

	/** 获取列表 */
	public List<GridViewItem> getChannnelLst() {
		return gridViewList;
	}

	/** 设置删除的position */
	public void setRemove(int position) {
		remove_position = position;
		notifyDataSetChanged();
	}

	/** 删除列表 */
	public void remove() {
		gridViewList.remove(remove_position);
		remove_position = -1;
		notifyDataSetChanged();
	}

	/** 设置列表 */
	public void setListDate(List<GridViewItem> list) {
		gridViewList = list;
	}

	/** 获取是否可见 */
	public boolean isVisible() {
		return isVisible;
	}

	/** 设置是否可见 */
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	/** 显示放下的ITEM */
	public void setShowDropItem(boolean show) {
		isItemShow = show;
	}
}