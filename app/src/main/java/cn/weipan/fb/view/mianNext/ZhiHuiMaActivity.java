package cn.weipan.fb.view.mianNext;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.weipan.fb.MyApplication;
import cn.weipan.fb.R;
import cn.weipan.fb.model.GetQCodeUrl;
import cn.weipan.fb.model.User;
import cn.weipan.fb.tools.BaseActivity;
import cn.weipan.fb.tools.GlobalConfig;
import cn.weipan.fb.tools.newGsonRequest;


public class ZhiHuiMaActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    QMUITopBar toolbar;
    @BindView(R.id.iv_image_ewm)
    ImageView ivImageEwm;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    private Bitmap ewm, bg, hecheng;
    private String ALBUM_PATH = Environment.getExternalStorageDirectory() + "/FB_ZHM/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihuima);
        ButterKnife.bind(this);
        View back = LayoutInflater.from(this).inflate(R.layout.back_view, toolbar, false);
        toolbar.addLeftView(back, R.id.qmui_topbar_item_left_back);
        back.setOnClickListener(v -> {
            finish();
        });
        toolbar.setTitle("智慧码");
//        toolbar.addLeftBackImageButton(R.drawable.baocun)
        toolbar.addRightImageButton(R.drawable.gdan,R.id.qmui_view_offset_helper).setOnClickListener(v -> {
            bottom_show();
        });
        getdata();
    }

    private void getdata() {
        String randomString = getRandomString(8);
        String content = getContent(randomString).trim();
        String miyaoKey = getMiyaoKey(randomString).trim();
        newGsonRequest request = new newGsonRequest<>(this,
                "/api/FuBaAppIndex/GetQCodeUrl?content=" + content + "&key=" + miyaoKey, GetQCodeUrl.class, null, response -> {
            if (response.getResult()==0){
                saveBitmap(response.getData().getQcodeurlshow(), response.getData().getBgqcodeurl());
            }
            else dialogShow(response.getError());
        }, error -> {
            dialogShow("系统异常");
        });
        MyApplication.volleyQueue.add(request);
    }


    private void saveBitmap(String qcodeurlshow, String Bgqcodeurl) {
        //保存图片到本地
        Glide.with(this).load(qcodeurlshow).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                ewm = scaleImg(resource, 496, 496);
                ivImageEwm.setImageBitmap(ewm);
                if (ewm != null && bg != null) {
                    hecheng = mergeBitmap(bg, ewm);
//                    saveFile(hecheng,"c00x001.png");
                }

            }
        });
        Glide.with(this).load(Bgqcodeurl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                bg = resource;
                if (ewm != null && bg != null) {
                    hecheng = mergeBitmap(bg, ewm);
//                    saveFile(hecheng,"c00x001.png");
                }
            }
        });
    }


    protected Bitmap scaleImg(Bitmap bm, int newWidth, int newHeight) {
        // 图片源
        // Bitmap bm = BitmapFactory.decodeStream(getResources()
        // .openRawResource(id));
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 设置想要的大小
        int newWidth1 = newWidth;
        int newHeight1 = newHeight;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth1) / width;
        float scaleHeight = ((float) newHeight1) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }


    //图片合成
    private Bitmap mergeBitmap(Bitmap firstBitmap, Bitmap secondBitmap) {
        Bitmap bitmap3 = Bitmap.createBitmap(firstBitmap.getWidth(), firstBitmap.getHeight(), firstBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap3);
        canvas.drawBitmap(firstBitmap, new Matrix(), null);
        canvas.drawBitmap(secondBitmap, 343, 430, null);  //120、350为bitmap2写入点的x、y坐标
        return bitmap3;
    }



    // 指定保存的路径：
    public void saveFile(Bitmap bm, String fileName) {
        try {
            File dirFile = new File(ALBUM_PATH);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            File myCaptureFile = new File(ALBUM_PATH + fileName);
            BufferedOutputStream bos = null;
            bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            dialogShow("保存成功");
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(myCaptureFile);
            intent.setData(uri);
            ZhiHuiMaActivity.this.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            dialogShow("保存失败");
        }
    }


    private void bottom_show(){

        QMUIBottomSheet.BottomGridSheetBuilder sheet=new QMUIBottomSheet.BottomGridSheetBuilder(this)
                .addItem(R.drawable.wiexin,"发送微信好友",1, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.qq,"发送QQ好友",2, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.pengyouquan,"分享到朋友圈",3, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.qqkongjian,"分享到QQ空间",4, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.baocun,"保存",5, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .setOnSheetItemClickListener((dialog,itemView)->{
                    dialog.dismiss();

                    int tag= (int) itemView.getTag();

                    switch (tag){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            User user =new Gson().fromJson( GlobalConfig.getLogin_User_info(),User.class);
                            saveFile(hecheng,user.getDeviceid()+"_bigzhm.jpg");
                            break;
                    }
                });
        sheet.build().show();
    }

}
