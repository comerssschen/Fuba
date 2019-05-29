package cn.weipan.fb.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import cn.weipan.fb.R;


public class GlideImageWelcomeLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法

//        Glide.with(context).load(R.drawable.qdya).into(imageView);


//        Glide.with(context).load(getBitmapByName((String)path)).into(imageView);

        imageView.setImageBitmap(getBitmapByName(context, (String) path));
    }

    public Bitmap getBitmapByName(Context context,String name) {
//        ApplicationInfo appInfo = getActivity().getApplicationInfo();
        int resID = context.getResources().getIdentifier(name, "drawable",context.getPackageName());
        return BitmapFactory.decodeResource(context.getResources(), resID);
    }

}
