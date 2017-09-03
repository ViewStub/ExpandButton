package com.viewstub.expandbutton;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Lan Long on 2017/91.
 * email: 5789492@qq.com
 */

public class MyLinearLayout extends LinearLayout {

    private ViewGroup.MarginLayoutParams marginVglp;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void setWidth(int width) {
        ViewGroup.LayoutParams lp = getLayoutParams();
        if (lp != null) {
            lp.width = width;
            setLayoutParams(lp);
        }
    }

    public void setPaddingLeft(int left) {
        setPadding(left, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    public void setPaddingRight(int right) {
        setPadding(getPaddingLeft(), getPaddingTop(), right, getPaddingBottom());
    }

    public void setMarginLeft(int left) {
        checkMarginLayoutParams();
        if (marginVglp != null) {
            marginVglp.leftMargin = left;
        }
    }

    public void setMarginRight(int right) {
        checkMarginLayoutParams();
        if (marginVglp != null) {
            marginVglp.rightMargin = right;
        }
    }

    private void checkMarginLayoutParams() {
        if (marginVglp == null) {
            ViewGroup.LayoutParams vglp = getLayoutParams();
            if (vglp instanceof ViewGroup.MarginLayoutParams) {
                marginVglp = (ViewGroup.MarginLayoutParams) vglp;
            }
        }
    }
}
