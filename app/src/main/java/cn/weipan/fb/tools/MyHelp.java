package cn.weipan.fb.tools;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;

import cn.weipan.fb.R;

public class MyHelp {

    private static String BaseUrl = "";

    public static String getBaseUrl(Context context) {
        if (TextUtils.isEmpty(BaseUrl))
            BaseUrl = context.getString(R.string.baseurl);
        return BaseUrl;
    }

    public static int post_type = Request.Method.POST;
    public static int get_type = Request.Method.GET;

}
