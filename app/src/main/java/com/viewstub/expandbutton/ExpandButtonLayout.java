package com.viewstub.expandbutton;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Lan Long on 2017/9/1.
 * email: 5789492@qq.com
 */

public class ExpandButtonLayout extends RelativeLayout implements Animation.AnimationListener {

    private RelativeLayout mRootView;
    private ImageView imageView;
    private MyTextView textView;
    private MyLinearLayout mLinearLayout;

    public ExpandButtonLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ExpandButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpandButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_button_expand, this, true);
        mRootView = (RelativeLayout) findViewById(R.id.mRootView);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (MyTextView) findViewById(R.id.textView);
        mLinearLayout = (MyLinearLayout) mRootView.findViewById(R.id.mLinearLayout);

        ViewTreeObserver vto2 = mLinearLayout.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                textViewWidth = mLinearLayout.getWidth();
                Log.e("DEBUG", "Width=" + textViewWidth);
                savePaddingLeft = mLinearLayout.getPaddingLeft();
                savePaddingRight = mLinearLayout.getPaddingRight();
                ViewGroup.LayoutParams vglp = mLinearLayout.getLayoutParams();
                if (vglp instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginVglp = (ViewGroup.MarginLayoutParams) vglp;
                    saveMarginLeft = marginVglp.leftMargin;
                    saveMarginRight = marginVglp.rightMargin;

                    Log.e("DEBUG", "vglp saveMarginLeft=" + saveMarginLeft + " saveMarginRight=" + saveMarginRight);
                }
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private int duration = 1000;
    private boolean isExpand = true;
    private boolean isAnimation = false;
    private int savePaddingLeft = 0;
    private int savePaddingRight = 0;
    private int saveMarginLeft = 0;
    private int saveMarginRight = 0;
    private int textViewWidth = 0;


    public void close() {

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(duration);
        animationSet.setAnimationListener(this);
        animationSet.setFillAfter(true);

        RotateAnimation rotateAnimation = new RotateAnimation(360, 270,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(rotateAnimation);
        Animation scaleAnimation = new ScaleAnimation(1f, 1.25f, 1f, 1.25f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        imageView.startAnimation(animationSet);

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mLinearLayout, "width", textViewWidth, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mLinearLayout, "paddingLeft", savePaddingLeft, 0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mLinearLayout, "paddingRight", savePaddingRight, 0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mLinearLayout, "marginLeft", saveMarginLeft, 0f);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(mLinearLayout, "marginRight", saveMarginRight, 0f);
        animatorSet.playTogether(animator1, animator2, animator3, animator4, animator5);
        animatorSet.setDuration(duration).start();

    }


    public void open() {

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(duration);
        animationSet.setAnimationListener(this);
        animationSet.setFillAfter(true);

        RotateAnimation rotateAnimation = new RotateAnimation(270, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(rotateAnimation);
        Animation scaleAnimation = new ScaleAnimation(1.25f, 1f, 1.25f, 1f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        imageView.startAnimation(animationSet);


        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mLinearLayout, "width", 0f, textViewWidth);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mLinearLayout, "paddingLeft", 0f, savePaddingLeft);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mLinearLayout, "paddingRight", 0f, savePaddingRight);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mLinearLayout, "marginLeft", 0f, saveMarginLeft);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(mLinearLayout, "marginRight", 0f, saveMarginRight);
        animatorSet.playTogether(animator1, animator2, animator3, animator4, animator5);
        animatorSet.setDuration(duration).start();

    }

    public void toggle() {
        if (!isAnimation) {
            if (isExpand) {
                close();
            } else {
                open();
            }
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {
        isAnimation = true;
//        if(!isExpand){
//            mRootView.setBackgroundResource(R.drawable.color_primary_rect);
//        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        isAnimation = false;
        isExpand = !isExpand;
//        if(isExpand){
//            mRootView.setBackgroundResource(R.drawable.color_primary_rect);
//        }else{
//            mRootView.setBackground(null);
//        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
