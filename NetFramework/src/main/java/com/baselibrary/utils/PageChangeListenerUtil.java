package com.baselibrary.utils;

import android.support.v4.view.ViewPager;
import android.util.Log;

/**
 * Created by Libaoming on 2/2/2018.
 * 16 hour 45 minute
 * project_name : MedicalEquipment
 */

public class PageChangeListenerUtil {

    public static void setOnPageChangeListener(ViewPager vp, final IPageChangeListener listener){
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (listener==null){
                    Log.e("callback", "IPageChangeListener == null: ");
                    return;
                }
                listener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public interface IPageChangeListener{
        void onPageSelected(int position);
    }
}
