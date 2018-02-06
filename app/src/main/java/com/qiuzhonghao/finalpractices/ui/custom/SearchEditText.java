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
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], mSearchDrawable, getCompoundDrawables()[3]);
    }


    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean touchable = event.getX() > (getWidth()
                        - getPaddingRight() - mSearchDrawable.getIntrinsicWidth())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    //TODO
                    Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                }
            }
        }

        return super.onTouchEvent(event);
    }


    public Drawable getSearchDrawable() {
        return mSearchDrawable;
    }

    public void setSearchDrawable(Drawable mClearDrawable) {
        this.mSearchDrawable = mClearDrawable;
    }
}
