package cn.weipan.fb.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;

import java.io.File;

/**
 * Created by yangfaming on 2018/3/10.
 */

public class DeviceUtil {
    /**
     * @return android系统版本
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * @return 终端最终名称
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * @return 设备品牌
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机IMEI号
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();

        return imei;
    }

    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionCode + "";
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    /**
     * 获取系统内存大小
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static String getInternalMemorySize() {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        long size = blockCountLong * blockSizeLong;
//        Log.d("1212",size / 1000 / 1000 / 1000+"");
        int i = (int) (size / 1024 / 1024 / 1024);
        if (i > 0 && i < 8) {
            return "8GB";
        } else if (i >= 8 && i < 16) {
            return "16GB";
        } else if (i >= 16 && i < 32) {
            return "32GB";
        } else if (i >= 32 && i < 64) {
            return "64GB";
        } else if (i >= 64 && i < 128) {
            return "128GB";
        } else if (i >= 128 && i < 256) {
            return "256GB";
        } else if (i >= 256 && i < 512) {
            return "512GB";
        } else {
            return null;
        }
    }



}
