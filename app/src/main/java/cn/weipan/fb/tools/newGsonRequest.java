package cn.weipan.fb.tools;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class newGsonRequest<T> extends Request<T> {
    public static QMUITipDialog BasetipDialog = null;
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Response.Listener<T> listener;
    private Map<String, String> params;
    private Context context;
    private boolean is_show = false;

    //    private String url;
    public newGsonRequest(Context context, String url, Class<T> clazz, Map<String, String> params,
                          Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(MyHelp.get_type, MyHelp.getBaseUrl(context) + url, errorListener);
        Log.d("httpUrls", MyHelp.getBaseUrl(context) + url);
        if (params != null) {
            Log.d("请求参数：", params.toString());
        }
        this.context = context;
        this.clazz = clazz;
        this.listener = listener;
        this.params = params;
        try {
            if (BasetipDialog == null) {
                BasetipDialog = new QMUITipDialog.Builder(context).
                        setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord("正在加载")
                        .create();
            }
            if (BasetipDialog.isShowing()) {
                BasetipDialog.dismiss();
            }
            BasetipDialog.show();

        } catch (Exception e) {

        }

    }


    public newGsonRequest(Context context, int type, String url, Class<T> clazz, Map<String, String> params,
                          Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(type, MyHelp.getBaseUrl(context) + url, errorListener);
        Log.d("httpUrls", MyHelp.getBaseUrl(context) + url);
        if (params != null) {
            Log.d("请求参数：", params.toString());
        }
        this.context = context;
        this.clazz = clazz;
        this.listener = listener;
        this.params = params;

        try {
            if (BasetipDialog == null) {
                BasetipDialog = new QMUITipDialog.Builder(context).
                        setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord("正在加载")
                        .create();
            }
            if (BasetipDialog.isShowing()) {
                BasetipDialog.dismiss();
            }
            BasetipDialog.show();

        } catch (Exception e) {

        }

    }


    public newGsonRequest(Context context, String url, Class<T> clazz, Map<String, String> params, String titleMeassage,
                          Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(MyHelp.post_type, MyHelp.getBaseUrl(context) + url, errorListener);
        Log.d("httpUrls", MyHelp.getBaseUrl(context) + url);
        if (params != null) {
            Log.d("请求参数：", params.toString());
        }
        this.context = context;
        this.clazz = clazz;
        this.listener = listener;
        this.params = params;
        try {
            if (BasetipDialog == null) {
                BasetipDialog = new QMUITipDialog.Builder(context).
                        setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord(titleMeassage)
                        .create();
            }
            if (BasetipDialog.isShowing()) {
                BasetipDialog.dismiss();
            }
            BasetipDialog.show();

        } catch (Exception e) {

        }

    }

    public newGsonRequest(Context context, String url, boolean is_show, Class<T> clazz, Map<String, String> params,
                          Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(MyHelp.post_type, MyHelp.getBaseUrl(context) + url, errorListener);
        Log.d("httpUrls", MyHelp.getBaseUrl(context) + url);
        if (params != null) {
            Log.d("请求参数：", params.toString());
        }
        this.context = context;
        this.clazz = clazz;
        this.listener = listener;
        this.params = params;
        this.is_show = is_show;
        if (is_show) {
            try {
                if (BasetipDialog == null) {
                    BasetipDialog = new QMUITipDialog.Builder(context).
                            setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                            .setTipWord("正在加载")
                            .create();
                }
                if (BasetipDialog.isShowing()) {
                    BasetipDialog.dismiss();
                }
                BasetipDialog.show();

            } catch (Exception e) {

            }

        }

    }

    public newGsonRequest(Context context, String baseurl, String url, Class<T> clazz, Map<String, String> params,
                          Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(MyHelp.post_type, baseurl + url, errorListener);
        Log.d("httpUrls", baseurl + url);
        if (params != null) {
            Log.d("请求参数：", params.toString());
        }
        this.context = context;
        this.clazz = clazz;
        this.listener = listener;
        this.params = params;
        try {
            if (BasetipDialog == null) {
                BasetipDialog = new QMUITipDialog.Builder(context).
                        setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord("正在加载")
                        .create();
            }
            if (BasetipDialog.isShowing()) {
                BasetipDialog.dismiss();
            }
            BasetipDialog.show();

        } catch (Exception e) {

        }

    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }

    //    @Override
//    public String getUrl() {
//        return MyHelp.BaseUrl+;
//    }

//    //请求头设置：重写getHeaders方法
//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Accept", "*/*");
//        headers.put("Charset", "UTF-8");
//        headers.put("Content-Type", "application/x-www-form-urlencoded");
//        headers.put("Accept-Encoding", "application/json");
////        headers.put("app-name", "Android");
////        headers.put("app-version", MyHelp.getAppVersion(MyApplication.context));
////        headers.put("appid", MyHelp.getPkName(MyApplication.context));
//////        Log.d("aaa",headers.toString());
////        return headers;
//        return headers != null ? headers : super.getHeaders();
////        return headers!=null?headers:super.getHeaders();
//    }
//


    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {

            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
            volleyError = error;
        }
        if (BasetipDialog != null) {
            BasetipDialog.dismiss();
            BasetipDialog.cancel();
            BasetipDialog = null;
        }
//        volleyError.getMessage()

        return volleyError;
    }


    //    超时设置：重写getRetryPolicy方法

    @Override
    public RetryPolicy getRetryPolicy() {//第一个参数为连接超时时长第二个为最大连接超时次数默认为1
        RetryPolicy retryPolicy = new DefaultRetryPolicy(10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return retryPolicy;
    }

    public Context getContext() {
        return context;
    }

    //请求参数组装：重写getBody方法
    @Override
    public byte[] getBody() throws AuthFailureError {
        return super.getBody();
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

//        "is_check": false, 审核中
//        "is_pass": false,  是否通过

        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.d("返回参数：", getUrl() + "0000000000" + json);
            if (BasetipDialog != null) {
                BasetipDialog.dismiss();
                BasetipDialog.cancel();
                BasetipDialog = null;
            }

            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            if (BasetipDialog != null) {
                BasetipDialog.dismiss();
                BasetipDialog.cancel();
                BasetipDialog = null;
            }
        }
        if (BasetipDialog != null) {
            BasetipDialog.dismiss();
            BasetipDialog.cancel();
            BasetipDialog = null;
        }
        return null;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
