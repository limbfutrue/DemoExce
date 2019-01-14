package com.baselibrary.utils.tabswitch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.baselibrary.utils.DensityUtils;
import com.baselibrary.utils.MyShapeUrils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Z
 */
public class TabView extends RelativeLayout {
    private RadioGroup bottomRadioGroup;
    private final int fontSize = 14;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private int tabCount;
    private FrameLayout frameLayout;

    public TabView(Context context) {
        super(context);
        init(context);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        initParentLayoutWH();
        initFrameLayout(context);
        initRadioGroup(context);
    }

    /**
     * 设置布局宽高占满全屏
     */
    private void initParentLayoutWH() {
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }
    @SuppressLint("ResourceType")
    private void initFrameLayout(Context context) {
        frameLayout = new FrameLayout(context);
        frameLayout.setId(0x0011);
        LayoutParams frameParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameParams.setMargins(0, 0, 0, DensityUtils.dip2px(50));
        frameLayout.setLayoutParams(frameParams);
        addView(frameLayout);
    }

    /**
     * 初始化radioGroup参数
     *
     * @param context
     */
    private void initRadioGroup(Context context) {
        //实例化radioGroup对象
        bottomRadioGroup = new RadioGroup(context);
        //实例化布局参数
        LayoutParams radioGroupLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, DensityUtils.dip2px(50));
        radioGroupLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomRadioGroup.setLayoutParams(radioGroupLayoutParams);
        //设置为水平布局
        bottomRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
        addView(bottomRadioGroup);
    }

    /**
     * 添加选项卡
     *
     * @param tabCount               选项卡长度
     * @param topDefaultDrawableList 默认顶部图标
     * @param topCheckDrawableList   选中图标
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addTab(Context context, int tabCount, List<Integer> topDefaultDrawableList
            , List<Integer> topCheckDrawableList, String[] textList, int[] textColorList) {
        this.tabCount = tabCount;
        if (topDefaultDrawableList != null && tabCount != topDefaultDrawableList.size()) {
            Log.e("callback", "addTab: 未选中图片数组长度与tab长度不一致");
            return;
        }
        if (topDefaultDrawableList != null && tabCount != topCheckDrawableList.size()) {
            Log.e("callback", "addTab: 选中图片数组长度与tab长度不一致");
            return;
        }
        RadioButton radioButton;
        Drawable defaultDrawable;
        Drawable checkDrawable;
        RadioGroup.LayoutParams rbParams = new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < tabCount; i++) {
            radioButton = new RadioButton(context);
            radioButton.setId(i);
            rbParams.weight = 1;
            radioButton.setLayoutParams(rbParams);
            radioButton.setGravity(Gravity.CENTER);
            //设置选中顶部图标
            defaultDrawable = context.getDrawable(topDefaultDrawableList.get(i));
            checkDrawable = context.getDrawable(topCheckDrawableList.get(i));
            if (i == 0){
                radioButton.setChecked(true);
            }
            radioButton.setCompoundDrawablesWithIntrinsicBounds(null, MyShapeUrils.getSelector(defaultDrawable, checkDrawable), null, null);
            radioButton.setCompoundDrawablePadding(DensityUtils.dip2px(5));
            radioButton.setButtonDrawable(null);
            radioButton.setText(textList[i]);
            radioButton.setTextSize(fontSize);
            //设置选中颜色
            radioButton.setTextColor(MyShapeUrils.createColorStateList(textColorList));
            bottomRadioGroup.addView(radioButton);
        }
    }

    /**
     * 添加选项卡
     * @param context
     * @param tabCount 选项卡长度
     * @param textList tab数据
     * @param textColorList 选中文字颜色 第一个为选中颜色，第二个值为未选中字体颜色
     */
    public void addTab(Context context, int tabCount, String[] textList, int[] textColorList) {
        this.tabCount = tabCount;
        RadioButton radioButton;
        RadioGroup.LayoutParams rbParams = new RadioGroup.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < tabCount; i++) {
            addRadioButton(context, textList[i], textColorList, rbParams, i);
        }
    }

    /**
     *
     * @param context 上下文
     * @param text
     * @param textColorList
     * @param rbParams
     * @param i
     */
    private void addRadioButton(Context context, String text, int[] textColorList, RadioGroup.LayoutParams rbParams, int i) {
        RadioButton radioButton;
        radioButton = new RadioButton(context);
        rbParams.weight = 1;
        radioButton.setLayoutParams(rbParams);
        radioButton.setGravity(Gravity.CENTER);
        radioButton.setId(i);
        if (i == 0){
            radioButton.setChecked(true);
        }
        radioButton.setCompoundDrawablePadding(DensityUtils.dip2px(0));
        radioButton.setButtonDrawable(null);
        radioButton.setText(text);
        radioButton.setTextSize(fontSize);
        //设置选中颜色
        radioButton.setTextColor(MyShapeUrils.createColorStateList(textColorList));
        bottomRadioGroup.addView(radioButton);
    }

    /**
     * 绑定fragment和tab
     * @param act
     * @param fragments
     */
    public void addFragmentAndBindTab(final FragmentActivity act, Fragment... fragments){
        if (tabCount < fragments.length){
            Log.e("log_print_info", "addFragmentAndBindTab: fragment长度和tab长度不一致");
            return;
        }
        final List<Fragment> fList = new ArrayList<>();
        for (int i = 0; i < fragments.length; i++) {
            fList.add(fragments[i]);
        }
        replaceFragment(act, fList,0);
        bottomRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                replaceFragment(act,fList,checkedId);
            }
        });
    }

    /**
     * 替换fragment
     * @param act
     * @param fList
     * @param index
     */
    private void replaceFragment(FragmentActivity act, List<Fragment> fList, int index) {
        fm = act.getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(frameLayout.getId(),fList.get(index));
        ft.commit();
    }


}
