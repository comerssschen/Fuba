package cn.weipan.fb;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.weipan.fb.bean.AllMessagBean;
import cn.weipan.fb.common.FirstEvent;
import cn.weipan.fb.tools.BaseActivity;
import cn.weipan.fb.tools.myPagerAdapter;
import cn.weipan.fb.view.mainView.BossFragment;
import cn.weipan.fb.view.mainView.HomeFragment;
import cn.weipan.fb.view.mainView.MessageFragment;
import cn.weipan.fb.view.mainView.TongjiFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabs)
    QMUITabSegment tabs;
    @BindView(R.id.iv_personal)
    ImageView ivPersonal;
    @BindView(R.id.tv_nicheng)
    TextView tvNicheng;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.ll_personal)
    LinearLayout llPersonal;
    @BindView(R.id.iv_cotent)
    ImageView ivCotent;
    @BindView(R.id.tv_network)
    TextView tvNetwork;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;
    @BindView(R.id.rl_updata)
    RelativeLayout rlUpdata;
    @BindView(R.id.tv_login_out)
    TextView tvLoginOut;
    @BindView(R.id.rl_login_out)
    RelativeLayout rlLoginOut;
    @BindView(R.id.rl_drawerlayout)
    RelativeLayout rlDrawerlayout;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private List<Fragment> fragmentList;
    Queue<String> queue = new LinkedList<String>();
    private boolean isFirst = true;
    public SpeechSynthesizer mTts;
    private AudioManager audioManager;
    private int currentVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(MainActivity.this);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //注册声音
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=57c80d8f");
        mTts = SpeechSynthesizer.createSynthesizer(this, null);
        initview();
    }


    private void initview() {
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        tabs.setDefaultNormalColor(getResources().getColor(R.color.text_hui));
        tabs.setDefaultSelectedColor(getResources().getColor(R.color.mycolor));
        QMUITabSegment.Tab main = new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.drawable.tab1_2), ContextCompat.getDrawable(this, R.drawable.tab1_1), "收银主页", false);
        QMUITabSegment.Tab tongji = new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.drawable.tab2_2), ContextCompat.getDrawable(this, R.drawable.tab2_1), "账单统计", false);
        QMUITabSegment.Tab boss = new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.drawable.tab3_2), ContextCompat.getDrawable(this, R.drawable.tab3_1), "老板赚钱", false);
        QMUITabSegment.Tab message = new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.drawable.tab4_2), ContextCompat.getDrawable(this, R.drawable.tab4_1), "通知消息", false);
        message.showSignCountView(MainActivity.this, 2);
        tabs.addTab(main).addTab(tongji).addTab(boss).addTab(message);
        fragmentList = new ArrayList<>();
        Fragment mainFragment = new HomeFragment();
        fragmentList.add(mainFragment);
        Fragment tongjiFragment = new TongjiFragment();
        fragmentList.add(tongjiFragment);
        Fragment bossFragment = new BossFragment();
        fragmentList.add(bossFragment);
        Fragment messageFragment = new MessageFragment();
        fragmentList.add(messageFragment);
        FragmentManager fm = getSupportFragmentManager();
        myPagerAdapter adapter = new myPagerAdapter(fm, fragmentList);
        viewpager.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager, false);

    }


    @Subscribe
    public void onMsgsEvent(FirstEvent events) {
        AllMessagBean allMessagBean = events.getMsg();

        boolean isvoice = SPUtils.getInstance().getBoolean("isvoice", true);
        boolean ismove = SPUtils.getInstance().getBoolean("ismove", true);
        //是否开启震动
        if (ismove) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {100, 400, 100, 400};   // 停止 开启 停止 开启
            vibrator.vibrate(pattern, -1);
        }

        //是否开启声音
        if (isvoice) {

            queue.offer(allMessagBean.getTitle() + allMessagBean.getMaoney() + "元");
            //此处需要将语音添加到队列，保证一条播放完再播放下一条
            //必须第一次收到消息才能在这播放，以后每次收到消息将消息添加到队列，每次播放完后去队列里面取；
            if (isFirst) {
                playVoice(queue.poll());
                isFirst = !isFirst;
            }

        }

    }


    /**
     * 参数设置
     *
     * @param
     * @return
     */
    public void playVoice(String msg) {
        // 设置合成
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflyket.pcm");    //声音文件地址
        // 设置发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        // 设置语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        // 设置音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        // 设置音量
        mTts.setParameter(SpeechConstant.VOLUME, "100");
        // 设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        mTts.startSpeaking(msg, mTtsListener);
    }

    /**
     * 合成回调监听。
     */
    public SynthesizerListener mTtsListener = new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {
            Log.i(TAG, "onSpeakBegin");
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND);
        }

        @Override
        public void onSpeakPaused() {
            Log.i(TAG, "onSpeakPaused");
        }

        @Override
        public void onSpeakResumed() {
            Log.i(TAG, "onSpeakResumed");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
            Log.i(TAG, "onBufferProgress");
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            Log.i(TAG, "onBufferProgress");
        }

        @Override
        public void onCompleted(SpeechError error) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, AudioManager.FLAG_PLAY_SOUND);
            Log.i(TAG, "onCompleted" + error);
            if (error == null) {
                if (!queue.isEmpty()) {
                    playVoice(queue.poll());
                } else {
                    isFirst = true;
                }
            } else if (error != null) {
                Log.i(TAG, error.getPlainDescription(true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            Log.i(TAG, "onEvent");
        }
    };

}
