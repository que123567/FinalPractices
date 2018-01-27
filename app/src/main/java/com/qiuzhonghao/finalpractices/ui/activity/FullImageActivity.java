package com.qiuzhonghao.finalpractices.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.qiuzhonghao.finalpractices.R;
import com.qiuzhonghao.finalpractices.base.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;


public class FullImageActivity extends BaseActivity {
    @BindView(R.id.iv_full_image)
    ImageView fullImageView;

    String image_url;

    @Override
    public int getLayout() {
        return R.layout.activity_full_image;
    }

    @Override
    protected void getIntentParams(Bundle bundle) {
        image_url = bundle.getString("Image_Url");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @OnClick(R.id.iv_full_image)
    public void onItemClick() {
        fullImageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = fullImageView.getDrawingCache();
        if (bitmap == null)
            Toast.makeText(FullImageActivity.this, "无图片", Toast.LENGTH_SHORT).show();
        if (ImgUtils.saveImageToGallery(FullImageActivity.this, bitmap)) {//保存图片到本地
            Toast.makeText(FullImageActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(FullImageActivity.this, "保存失败，无权限", Toast.LENGTH_SHORT).show();

        fullImageView.setDrawingCacheEnabled(false);
    }


    public static class ImgUtils {
        //保存文件到指定路径
        public static boolean saveImageToGallery(Context context, Bitmap bmp) {
            // 首先保存图片
            String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                    "dearxy";
            File appDir = new File(storePath);
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpg";
            Log.d("ImgUtils", fileName);
            File file = new File(appDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                //通过io流的方式来压缩保存图片
                boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
                fos.flush();
                fos.close();

                //把文件插入到系统图库
                //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(),
                // fileName, null);

                //保存图片后发送广播通知更新数据库
                Uri uri = Uri.fromFile(file);
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                if (isSuccess) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }


}
