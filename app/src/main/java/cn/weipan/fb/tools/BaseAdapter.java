package cn.weipan.fb.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

/**
 * Created by Administrator on 2018/8/14.
 */

public class BaseAdapter extends DelegateAdapter.Adapter<BaseHolder> {


    private LayoutHelper mLayoutHelper;
    private int mCount = -1;
    private int mLayoutId = -1;
    private Context mContext;
//    private int mViewTypeItem = -1;

    public BaseAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int count) {
        this.mContext = context;
        this.mCount = count;
        this.mLayoutHelper = layoutHelper;
        this.mLayoutId = layoutId;
//        this.mViewTypeItem = viewTypeItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BaseHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false));
            //(LayoutInflater.from(context).inflate(R.layout.zixun_content_item,parent,false));
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {

    }

    /**
     * 必须重写不然会出现滑动不流畅的情况
     */
//    @Override
//    public int getItemViewType(int position) {
//        return mViewTypeItem;
//    }

    //条目数量
    @Override
    public int getItemCount() {
        return mCount;
    }


}
