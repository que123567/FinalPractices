package com.qiuzhonghao.finalpractices.ui.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.constant.API;
import com.qiuzhonghao.finalpractices.network.FileUploadService;
import com.qiuzhonghao.finalpractices.network.RxService;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.btn_more_edit)
    Button btnEdit;
    @BindView(R.id.iv_more_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_question_nickname)
    TextView mTvNickname;
    @BindView(R.id.tv_question_hint)
    TextView mTvQuestionHint;
    @BindView(R.id.aim_question_hint)
    LottieAnimationView mAimQuestionHint;
    @BindView(R.id.swipe_more)
    SwipeRefreshLayout mSwipeLayout;


    Unbinder unbinder;
    String imagePath;
    String headURL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        unbinder = ButterKnife.bind(this, view);
        initState();
        initSwipeRefreshLayout(mSwipeLayout);
        return view;
    }


    /**
     * 下拉刷新
     *
     * @param swipeLayout
     */
    private void initSwipeRefreshLayout(SwipeRefreshLayout swipeLayout) {
        swipeLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
        mSwipeLayout.setDistanceToTriggerSync(200);
        mSwipeLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mSwipeLayout.setOnRefreshListener(this);
    }

    private void initState() {
        SharedPreferences share = getActivity().getSharedPreferences("TOKEN", MODE_PRIVATE);
        String author = share.getString("UserNickName", "null");
        mTvNickname.setText(author);//用户名
        headURL = API.BASE_URL + "final/file/tmpFile/" + author + ".png";
        if (author != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(getContext()).clearDiskCache();
                }
            }).start();
            Glide.with(this).load(headURL).into(ivHead);
        } else {
            headURL = API.BASE_URL + "final/file/tmpFile/" + "defalut.png";
            Glide.with(this).load(headURL).into(ivHead);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void editHeadImage() {

    }

    /**
     * 编辑个人资料
     */
    @OnClick(R.id.btn_more_edit)
    public void onEditClick() {
//        editHeadImage();
        showImagePopup();
    }


    private void doFileUpload(String imagePath) {
        Retrofit retrofit = RxService.getRetrofitInstance(API.FILEUPLOAD);
        FileUploadService service = retrofit.create(FileUploadService.class);
        final File file = new File(imagePath);

        if (!file.exists()) {
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                Toast.makeText(getActivity(), "上传文件不存在", Toast.LENGTH_SHORT).show();
            }
        }
        SharedPreferences share = getActivity().getSharedPreferences("TOKEN", MODE_PRIVATE);
        String author = share.getString("UserNickName", "smagu");
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("uploaded_file", author + ".png", requestFile);

        String descriptionString = "This is a description";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        Call<ResponseBody> call = service.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> responseBody) {
                Toast.makeText(getActivity(), "头像上传成功,审核中.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "头像上传失败,请重试.", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    /**
     * Showing Image Picker
     */
    public void showImagePopup() {
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);

        final Intent chooserIntent = Intent.createChooser(galleryIntent, "upload");
        startActivityForResult(chooserIntent, 1010);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK && requestCode == 1010) {//选择头像回调
            if (data == null) {
                Toast.makeText(getActivity(), "图像地址为空", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                doFileUpload(imagePath);
//                Glide.with(this).load(new File(imagePath)).into();

            }


        }
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        mSwipeLayout.setRefreshing(false);
    }

//    private void initHeadImage() {
//        LayoutInflater inflater = getLayoutInflater();
//        Context mContext = getActivity();
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ScreenUtil.dip2px(mContext, 80), ScreenUtil.dip2px(mContext, 80));
//        params.topMargin = ScreenUtil.dip2px(mContext, 10);
//        params.leftMargin = ScreenUtil.getScreenWidth(mContext) / 2 - ScreenUtil.dip2px(mContext, 80) / 2;
//
//        mIvList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            //            String headURL = API.BASE_URL + "/final/file/tmpFile/" + "1";
//            //            File file = new File(headURL);
//            //            Glide.with(this).load("").into(circleImageView);
//
//            circleImageView = new AnimateImageView(mContext);
//            circleImageView.setImageResource(R.drawable.author_head);
//            circleImageView.setBorderWidth(ScreenUtil.dip2px(mContext, 1));
//            circleImageView.setBorderColor(Color.WHITE);
//            circleImageView.setLayoutParams(params);
//            addView(circleImageView);
//            if (i == 4) {
//                this.circleImageView = circleImageView;
//            } else {
//                circleImageView.setAlpha(0.3f);
//            }
//            mIvList.add(circleImageView);
//        }
//
//        mViewTrackController.init(mIvList);
//    }
}
