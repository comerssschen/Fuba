package cn.weipan.fb.tools;

import android.content.Context;
import android.view.Gravity;

import com.blankj.utilcode.util.ToastUtils;

import cn.weipan.fb.R;

/**
 * 文字简单提示工具
 */

public class ToastUtil {

    public static void showToast(Context context, String str) {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        ToastUtils.setMsgColor(context.getResources().getColor(R.color.white));
        ToastUtils.setBgColor(context.getResources().getColor(R.color.toastcolor));
        ToastUtils.showShort(str);
    }

    public static void showLongToast(Context context, String str) {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        ToastUtils.setMsgColor(context.getResources().getColor(R.color.white));
        ToastUtils.setBgColor(context.getResources().getColor(R.color.toastcolor));
        ToastUtils.showLong(str);
    }

}
