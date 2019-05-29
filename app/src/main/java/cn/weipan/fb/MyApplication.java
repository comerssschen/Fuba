package cn.weipan.fb;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lzy.okgo.OkGo;
import com.tencent.bugly.crashreport.CrashReport;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.jpush.android.api.JPushInterface;
import cn.weipan.fb.tools.GlobalConfig;

public class MyApplication extends Application {
    public static Context context;
    public static RequestQueue volleyQueue;
    public static final String URLZHM = "http://manager.payweipan.com";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        volleyQueue = Volley.newRequestQueue(getApplicationContext());
        GlobalConfig.setContext(getApplicationContext());
        OkGo.getInstance().init(this);
        CrashReport.initCrashReport(getApplicationContext(), "9641d6c525", false);//上线时改成false
        ZXingLibrary.initDisplayOpinion(this);
        JPushInterface.setDebugMode(true);// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);// 初始化 JPush
    }
}
