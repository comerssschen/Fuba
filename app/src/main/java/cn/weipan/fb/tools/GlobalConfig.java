package cn.weipan.fb.tools;

import android.content.Context;
import android.content.SharedPreferences;


public class GlobalConfig {
    private static final int SHAREDPREF_OPEN_MODE = Context.MODE_PRIVATE;
    private static final String SHAREDPREF_APP_USER = "user_data";
    private static final String KEY_FIRST = "app_first";
    private static Context sContext;
    private static final String user_login_info="user_login_info";
    public static Context getContext() {
        return sContext;
    }
    public static void setContext(Context context) {
        sContext = context;
    }
    private static SharedPreferences getAppUserSharedpref() {
        return sContext.getSharedPreferences(SHAREDPREF_APP_USER, SHAREDPREF_OPEN_MODE);
    }
    // 是否登录
    public static boolean isIs_first() {
        SharedPreferences editor = getAppUserSharedpref();
        return editor.getBoolean(KEY_FIRST, true);
    }

    public static void setIs_first() {
        SharedPreferences.Editor editor = getAppUserSharedpref().edit();
        editor.putBoolean(KEY_FIRST, false).commit();
    }

    public static String getLogin_User_info() {
        SharedPreferences editor=getAppUserSharedpref();
        String token=editor.getString(user_login_info,"");
        return token;
    }
    public static void setLogin_User_info(String user){
        SharedPreferences.Editor editor = getAppUserSharedpref().edit();
        boolean commit = editor.putString(user_login_info, user).commit();
    }






}
