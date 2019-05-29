package cn.weipan.fb.tools;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import cn.weipan.fb.model.User;

public abstract class BaseFragment extends Fragment {
    public QMUITipDialog tipDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void dialogShow(String title) {
        tipDialog = new QMUITipDialog.Builder(getActivity()).setTipWord(title).create();
        tipDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    tipDialog.dismiss();
                } catch (Exception e) {

                }
            }
        }, 1500);
    }


    public Bitmap getBitmapByName(String name) {
//        ApplicationInfo appInfo = getActivity().getApplicationInfo();
        int resID = getActivity().getResources().getIdentifier(name, "drawable", getActivity().getPackageName());
        return BitmapFactory.decodeResource(getActivity().getResources(), resID);
    }


    //获取随机数
    public String getRandomString(int length) {
        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    //获取key
    public String getMiyaoKey(String randomStr) {
        return randomStr + getMiyao(randomStr);
    }

    //获取秘钥
    public String getMiyao(String randomStr) {

        String a = "";
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");

            String login_user_info = GlobalConfig.getLogin_User_info();
            User user = new Gson().fromJson(login_user_info, User.class);

            String workKey = user.getKey();
            DESKeySpec desKeySpec = new DESKeySpec(user.getKey().getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] buf = cipher.doFinal(randomStr.getBytes());
            a = toHexString(buf).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }

    //获取content
    public String getContent(String randomStr) {

        String a = "";
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            DESKeySpec desKeySpec = new DESKeySpec(randomStr.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //加密的内容
//            Log.i("test", "desKeySpec=====" + appContext.getWorkKey());
            byte[] buf = cipher.doFinal(getuserInfo().getBytes());
            a = toHexString(buf).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    private String getuserInfo() {


        String login_user_info = GlobalConfig.getLogin_User_info();
        User user = new Gson().fromJson(login_user_info, User.class);

        String id = user.getDeviceid();
        if (user.getDeviceid().length() < 10) {
            for (int i = 0; i < 10 - user.getDeviceid().length(); i++) {
                id = "0" + id;
            }
        }
        String number = user.getCashid();
        if (user.getCashid().length() < 10) {
            for (int i = 0; i < 10 - user.getCashid().length(); i++) {
                number = "0" + number;
            }
        }
        String randomStr = this.getRandomString(8);
        String userInfo = randomStr + id + number + "0000";
        return userInfo;
    }

}
