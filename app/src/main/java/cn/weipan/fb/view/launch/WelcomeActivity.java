package cn.weipan.fb.view.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.weipan.fb.R;
import cn.weipan.fb.tools.GlideImageWelcomeLoader;
import cn.weipan.fb.tools.GlobalConfig;
import cn.weipan.fb.view.Login_Regist.LoginActivity;


public class WelcomeActivity extends Activity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.welcome_skip)
    ImageView welcomeSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GlobalConfig.setIs_first();
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.isAutoPlay(false);
//        banner.setDelayTime(5000);
        banner.setImageLoader(new GlideImageWelcomeLoader());
        List<String> image = new ArrayList<>();
        image.add("qdya");
        image.add("qdyb");
        image.add("qdyc");
        banner.setImages(image);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 2) {

                    welcomeSkip.setVisibility(View.VISIBLE);
                    welcomeSkip.setOnClickListener(v -> {
                        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                        startActivityForResult(intent, 99);
                        finish();
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
//        banner.setS
        banner.start();

    }
}
