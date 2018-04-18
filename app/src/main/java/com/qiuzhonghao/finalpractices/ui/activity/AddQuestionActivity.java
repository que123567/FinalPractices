package com.qiuzhonghao.finalpractices.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;
import com.qiuzhonghao.finalpractices.ui.custom.ClearWriteEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class AddQuestionActivity extends BaseActivity {

    @BindView(R.id.btn_add_question_next_step)
    Button btnNextSetp;
    @BindView(R.id.et_question_title)
    ClearWriteEditText mEtQuestionTitle;
    @BindView(R.id.et_question_content)
    EditText mEtQuestionContent;

    @Override
    public int getLayout() {
        return R.layout.activity_add_question;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_add_question_next_step)
    public void doNextStep() {
        Intent intent = new Intent();
        intent.setClass(this, AddFlagActivity.class);
        intent.putExtra("TITLE", mEtQuestionTitle.getText().toString().trim());
        intent.putExtra("CONTENT", mEtQuestionContent.getText().toString().trim());
        finish();
        startActivity(intent);

    }
}

