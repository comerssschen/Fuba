package cn.weipan.fb.view.launch;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.weipan.fb.MainActivity;
import cn.weipan.fb.R;
import cn.weipan.fb.model.User;
import cn.weipan.fb.tools.DeviceUtil;
import cn.weipan.fb.tools.GlobalConfig;
import cn.weipan.fb.tools.NetworkRequest;
import cn.weipan.fb.view.Login_Regist.LoginActivity;


public class LaunchActivity extends Activity implements NetworkRequest.ReponseListener {
    CountDownTimer countDownTimer;
    @BindView(R.id.daojishi)
    TextView daojishi;

    private String imei="";
    private User user;
    private boolean doAction=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);

        if (GlobalConfig.isIs_first()) {
            Intent intent = new Intent(LaunchActivity.this, WelcomeActivity.class);
            startActivity(intent);
            LaunchActivity.this.overridePendingTransition(0, 0);
            finish();
        }
        else {
            countDownTimer = new CountDownTimer(4000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    daojishi.setText("跳过" + millisUntilFinished / 1000);
                }
                @Override
                public void onFinish() {
//                if (!GlobalConfig.isIs_first()){
                    if (!doAction){
                        Intent intent=new Intent(LaunchActivity.this,LoginActivity.class);
                        startActivityForResult(intent,99);
                    }
                }
            }.start();

            daojishi.setOnClickListener(v -> {
                if (countDownTimer != null) {
//                countDownTimer.onFinish();
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
                if (!doAction){
                    Intent intent=new Intent(LaunchActivity.this,LoginActivity.class);
                    startActivityForResult(intent,99);
                }

            });

            autoLogin();
        }



    }


    @Override
    protected void onPause() {
        super.onPause();
//        Log.d("aa","aa");
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
    private void autoLogin(){
        String login_user_info = GlobalConfig.getLogin_User_info();
        if (login_user_info!=null&&!login_user_info.equals("")){
            doAction=true;
            user=new Gson().fromJson(login_user_info,User.class);
            login(user.getPhone(),user.getPass());
        }
    }

    private void login(String phone,String pass){

        Acp.getInstance(this).request(new AcpOptions.Builder().setPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setDeniedMessage("手机权限已关闭,如需开启,请点击\"立即开启\"")
                .setDeniedSettingBtn("使用此功能需要手机权限!")
                .setDeniedCloseBtn("关闭")
                .setRationalBtn("确定")
                .setRationalMessage("使用此功能需要手机权限!")
                .build(), new AcpListener() {
            @Override
            public void onGranted() {
                imei= DeviceUtil.getIMEI(LaunchActivity.this);
            }

            @Override
            public void onDenied(List<String> permissions) {

            }
        });
//        if (TextUtils.isEmpty(imei)){
////            dialogShow("手机信息未获取！");
//        }
//        waitdoalog(this);
        String sendData = "{app|" + "0" + "|" + "0" + "|" + "17" + "|"
                + phone + "|"
                + pass + "|"
                + imei + "|tag_login" + "}";
        NetworkRequest mLoginRequest = new NetworkRequest(sendData);
        new Thread(mLoginRequest).start();
        mLoginRequest.setListener(LaunchActivity.this);
    }
    /**
     * 提示框
     *
     * @param title 提示文字
     */





    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            loginAction((String) msg.obj);
        }
    };


    @Override
    public void onResult(String result) {

        Message message = new Message();
        message.obj=result;
        handler.sendMessage(message);


    }

    private void loginAction(String result){

//        Log.i("test", "result = " + result);
        String[] arr = null;
        if (result != null) {
            arr = result.replace("{", "").replace("}", "").split("\\|");
            if (arr[0].equals("0")) {
//                User users=new User();
//                users.setPhone(loginnewEditPhone.getText().toString());
//                users.setPass(loginnewEditMessageverify.getText().toString());
                user.setDeviceid(arr[3]);
                user.setRealname(arr[2]);
                user.setCashid(arr[4]);
                user.setSiteid(arr[5]);
                user.setKey(arr[6]);
                user.setCashtype(arr[9]);
                GlobalConfig.setLogin_User_info(new Gson().toJson(user));
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent=new Intent(LaunchActivity.this,LoginActivity.class);
                startActivityForResult(intent,99);
            }
        }
        else {
//            dialogShow("登录失败,请检查网络！");

            Intent intent=new Intent(LaunchActivity.this,LoginActivity.class);
            startActivityForResult(intent,99);


        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.d("cc","cc");
    }
}
