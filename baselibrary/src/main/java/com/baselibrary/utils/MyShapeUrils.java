package com.baselibrary.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * 设置背景shape
 * Created by limb on 2017/8/3.
 */

public class MyShapeUrils {
    /**
     * 获取圆角圆弧矩形背景shape
     * @param context 上下文
     * @param colorId 背景颜色
     * @param strokeColorId 边框颜色
     * @param strokeWidth 边框宽度
     * @param radius 圆角弧度
     * @return
     */
    public static Drawable shapeShow(Context context, int colorId, int strokeColorId, int strokeWidth, int radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        // 形状-圆角矩形
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        // 圆角
        int r = (int)DisplayUtil.dip2px(context,radius);
        gradientDrawable.setCornerRadius(r);
        // 填充颜色
        gradientDrawable.setColor(colorId);
        //边框颜色，边框宽度
        gradientDrawable.setStroke(strokeWidth,strokeColorId);
        //宽，高
        int w = (int)DisplayUtil.dip2px(context,10);
        int h = (int) DisplayUtil.dip2px(context,20);
        gradientDrawable.setSize(w, h);
        return gradientDrawable;
    }

    /**
     * 获取Selector选中背景替换
     * @param normalDraw 未选中drawable
     * @param pressedDraw 选中的drawable
     * @return
     */
    public static StateListDrawable getSelector(Drawable normalDraw, Drawable pressedDraw) {
        StateListDrawable stateListDrawable  = new StateListDrawable();
        stateListDrawable.addState(new int[]{ android.R.attr.state_checked }, pressedDraw);
        stateListDrawable.addState(new int[]{ }, normalDraw);
        return stateListDrawable ;
    }

    /**
     * 设置不同状态时其文字颜色。（RadioButton）
     * @param checkedColorId 选中状态
     * @param unCheckedColorId 未选中状态
     * @return
     */
    public static ColorStateList createColorStateList(int checkedColorId, int unCheckedColorId) {
        int[] colors = new int[] { checkedColorId,unCheckedColorId};
        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_checked};
        states[1] = new int[] { };
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }
}
