package com.libaoming.demoexce.demoexce.utils.customview;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.libaoming.demoexce.demoexce.R;

import java.util.Map;

/**
 * Created by Libaoming on 19/12/2017.
 * 16 hour 51 minute
 * project_name : DemoExce
 */

public class RichTextView extends ScrollView {
    private LinearLayout parentLinearLayout;
    private LayoutInflater inflater;

    public RichTextView(Context context) {
        super(context);
        init(context);
    }

    public RichTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RichTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflater = LayoutInflater.from(context);
        parentLinearLayout = new LinearLayout(context);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams pLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parentLinearLayout.addView(createEdit());
        addView(parentLinearLayout, pLayoutParams);
    }

    public void insertImage(Context context, int resId) {
        ImageView imageView = createImage();
        imageView.setImageResource(resId);
        parentLinearLayout.addView(imageView);
        parentLinearLayout.addView(createEdit());
    }

    public EditText createEdit() {
        View edit = inflater.inflate(R.layout.rich_edit_layout, null);
        EditText editText = edit.findViewById(R.id.rich_edit);
        return editText;
    }

    public ImageView createImage() {
        View image = inflater.inflate(R.layout.rich_image_layout, null);
        ImageView imageView = image.findViewById(R.id.iv_rich_image);
        return imageView;
    }
}
