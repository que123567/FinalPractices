package com.qiuzhonghao.finalpractices.ui.custom;

/**
 * 2018/1/24 11:38,
 * Created by QiuZhongHao.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import com.qiuzhonghao.finalpractices.R;


public class SearchEditText extends android.support.v7.widget.AppCompatEditText {


    private Drawable mSearchDrawable;
    private Drawable mAskDrawable;

    public SearchEditText(Context context) {
        this(context, null);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        //获得XML里面自定义的属性
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mSearchDrawable = getResources().getDrawable(R.drawable.search_main);
        mSearchDrawable.setBounds(0, 0, mSearchDrawable.getIntrinsicWidth() / 2, mSearchDrawable.getIntrinsicWidth() / 2);
        mAskDrawable = getResources().getDrawable(R.drawable.ask_main);
        mAskDrawable.setBounds(0, 0, mAskDrawable.getIntrinsicHeight() / 2, mAskDrawable.getIntrinsicHeight() / 2);
        setCompoundDrawables(mSearchDrawable, getCompoundDrawables()[1]
                , mAskDrawable, getCompoundDrawables()[3]);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[0] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean touchable = event.getX() > (getPaddingLeft()) && (event.getX() < ((getPaddingLeft() + mSearchDrawable.getIntrinsicWidth())));
                if (touchable) {
                    //TODO
                    Toast.makeText(getContext(), "搜索", Toast.LENGTH_SHORT).show();
                }
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onEditorAction(int actionCode) {
        super.onEditorAction(actionCode);
        
    }

    public Drawable getSearchDrawable() {
        return mSearchDrawable;
    }

    public void setSearchDrawable(Drawable mClearDrawable) {
        this.mSearchDrawable = mClearDrawable;
    }
}
