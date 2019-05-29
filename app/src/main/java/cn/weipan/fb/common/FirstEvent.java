package cn.weipan.fb.common;

import cn.weipan.fb.bean.AllMessagBean;

/**
 * Created by Administrator on 2016/8/8.
 * eventbus传递消息
 */
public class FirstEvent {
    private AllMessagBean mMsg;

    public FirstEvent(AllMessagBean msg) {
        mMsg = msg;
    }

    public AllMessagBean getMsg() {
        return mMsg;
    }
}

