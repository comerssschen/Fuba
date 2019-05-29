package cn.weipan.fb.view.Login_Regist;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.weipan.fb.MainActivity;
import cn.weipan.fb.R;
import cn.weipan.fb.model.User;
import cn.weipan.fb.tools.DeviceUtil;
import cn.weipan.fb.tools.GlobalConfig;
import cn.weipan.fb.tools.NetworkRequest;
import cn.weipan.fb.view.launch.WelcomeActivity;


public class LoginActivity extends Activity implements NetworkRequest.ReponseListener{


    @BindView(R.id.loginnew_edit_phone)
    EditText loginnewEditPhone;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.loginnew_edit_messageverify)
    EditText loginnewEditMessageverify;
    @BindView(R.id.login_nologin)
    TextView loginNologin;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.login_regist)
    TextView loginRegist;
    public QMUITipDialog tipDialog;
    public static QMUITipDialog BasetipDialog = null;
    String imei="";
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }





    @OnClick({R.id.login_nologin, R.id.tv_login, R.id.login_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_nologin:

                break;
            case R.id.tv_login:
//                Intent intent=new Intent(this,MainActivity.class);
//                startActivity(intent);

                login();
                break;
            case R.id.login_regist:
                break;
        }
    }


    private void login(){
        String phone = loginnewEditPhone.getText().toString().trim();
        String pass = loginnewEditMessageverify.getText().toString().trim();

        if (TextUtils.isEmpty(phone)){
            dialogShow("账号不能为空");
            return;
        }

        if (TextUtils.isEmpty(pass)){
            dialogShow("请输入密码");
            return;
        }

        Acp.getInstance(this).request(new AcpOptions.Builder().setPermissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .setDeniedMessage("手机权限已关闭,如需开启,请点击\"立即开启\"")
                        .setDeniedSettingBtn("使用此功能需要手机权限!")
                        .setDeniedCloseBtn("关闭")
                        .setRationalBtn("确定")
                        .setRationalMessage("使用此功能需要手机权限!")
                        .build(), new AcpListener() {
                    @Override
                    public void onGranted() {
                        imei= DeviceUtil.getIMEI(LoginActivity.this);
                        if (TextUtils.isEmpty(imei)){
                            dialogShow("手机信息未获取！");
                        }
                        waitdoalog(LoginActivity.this);
                        String sendData = "{app|" + "0" + "|" + "0" + "|" + "17" + "|"
                                + loginnewEditPhone.getText().toString() + "|"
                                + loginnewEditMessageverify.getText().toString() + "|"
                                + imei + "|tag_login" + "}";
                        NetworkRequest mLoginRequest = new NetworkRequest(sendData);
                        new Thread(mLoginRequest).start();
                        mLoginRequest.setListener(LoginActivity.this);
                    }

                    @Override
                    public void onDenied(List<String> permissions) {

                    }
                });

    }
    /**
     * 提示框
     *
     * @param title 提示文字
     */
    public void dialogShow(String title) {
        tipDialog = new QMUITipDialog.Builder(this).setTipWord(title).create();
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


    public  void waitdoalog(Context context){
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
    }

    public void waitdoalogdiss(){
        if (BasetipDialog!=null){
            BasetipDialog.dismiss();
            BasetipDialog=null;
        }

    }






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
                waitdoalogdiss();
//        Log.i("test", "result = " + result);
        String[] arr = null;
        if (result != null) {
            arr = result.replace("{", "").replace("}", "").split("\\|");
            if (arr[0].equals("0")) {
                flag = true;
                User user=new User();
                user.setPhone(loginnewEditPhone.getText().toString());
                user.setPass(loginnewEditMessageverify.getText().toString());
                user.setDeviceid(arr[3]);
                user.setRealname(arr[2]);
                user.setCashid(arr[4]);
                user.setSiteid(arr[5]);
                user.setKey(arr[6]);
                user.setCashtype(arr[9]);
                GlobalConfig.setLogin_User_info(new Gson().toJson(user));
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                String login_user_info = GlobalConfig.getLogin_User_info();
                Log.d("aaa",login_user_info);
                startActivity(intent);
                finish();
            }
            else {
                    if (arr.length>1){
                        dialogShow(arr[1]);
                    }
                    else dialogShow("登录失败");
            }
        }
        else {
            dialogShow("登录失败,请检查网络！");
        }
    }

}
