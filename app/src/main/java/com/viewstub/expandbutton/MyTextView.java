package com.viewstub.expandbutton;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Lan Long on 2017/91.
 * email: 5789492@qq.com
 */

public class MyTextView extends AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setWidth(float width) {

        int w = (int) width;
        super.setWidth(w);

        if(getLayoutParams()!=null){
            getLayoutParams().width =w;
        }
    }

    public void setPaddingLeft(float left) {
        setPadding((int) left, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    public void setPaddingRight(float right) {
        setPadding(getPaddingLeft(), getPaddingTop(), (int) right, getPaddingBottom());
    }

}
