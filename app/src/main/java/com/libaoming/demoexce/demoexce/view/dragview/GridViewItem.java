package com.libaoming.demoexce.demoexce.view.dragview;

import java.io.Serializable;


/**
 * ITEM的对应可序化队列属性
 *  */
public class GridViewItem implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -6465237897027410019L;
	/**
	 * 列表对应ID
	 *  */
	public Integer id;
	/**
	 * 列表对应NAME
	 *  */
	public String name;
	/**
	 * 列表对应iconId
	 *  */
	public Integer iconId;
	/**
	 * 列表在整体中的排序顺序  rank
	 *  */

	public Integer orderId;
	/**
	 * 列表是否选中
	 *  */
	public Integer selected;

	public GridViewItem() {
	}

	public GridViewItem(int id, String name, int iconId, int orderId, int selected) {
		this.id = Integer.valueOf(id);
		this.name = name;
		this.iconId = Integer.valueOf(iconId);
		this.orderId = Integer.valueOf(orderId);
		this.selected = Integer.valueOf(selected);
	}

	public int getId() {
		return this.id.intValue();
	}

	public String getName() {
		return this.name;
	}
	public int getIconId() {
		return iconId.intValue();
	}
	public int getOrderId() {
		return this.orderId.intValue();
	}

	public Integer getSelected() {
		return this.selected;
	}

	public void setId(int paramInt) {
		this.id = Integer.valueOf(paramInt);
	}

	public void setName(String paramString) {
		this.name = paramString;
	}
	public void setIconId(int iconId) {
		this.iconId = Integer.valueOf(iconId);
	}
	public void setOrderId(int paramInt) {
		this.orderId = Integer.valueOf(paramInt);
	}

	public void setSelected(Integer paramInteger) {
		this.selected = paramInteger;
	}

	@Override
	public String toString() {
		return "ChannelItem [id=" + id + ", name=" + name + ", iconId="
				+ iconId + ", orderId=" + orderId + ", selected=" + selected
				+ "]";
	}

}