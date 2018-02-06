package com.qiuzhonghao.finalpractices.ui.fragment;


import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.qiuzhonghao.finalpractices.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {
    LottieAnimationView mLottieAnimationView;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        mLottieAnimationView = view.findViewById(R.id.lottie);

        btn = view.findViewById(R.id.btn_click);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLottieAnimationView.setAnimation("data.json");
                mLottieAnimationView.setRepeatCount(0);
                mLottieAnimationView.playAnimation();
            }
        });

        mLottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mLottieAnimationView.pauseAnimation();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return view;
    }

}
