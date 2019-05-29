package cn.weipan.fb.view.mainView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.weipan.fb.MyApplication;
import cn.weipan.fb.R;
import cn.weipan.fb.db_base;
import cn.weipan.fb.model.GetAccount;
import cn.weipan.fb.model.TodayCount;
import cn.weipan.fb.model.User;
import cn.weipan.fb.model.home_modle1;
import cn.weipan.fb.tools.BaseAdapter;
import cn.weipan.fb.tools.BaseFragment;
import cn.weipan.fb.tools.BaseHolder;
import cn.weipan.fb.tools.GlideImageLoader;
import cn.weipan.fb.tools.GlobalConfig;
import cn.weipan.fb.tools.MyHelp;
import cn.weipan.fb.tools.newGsonRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TongjiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TongjiFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.toolbar)
    QMUITopBar toolbar;
    @BindView(R.id.myrecy)
    RecyclerView myrecy;
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_ratio)
    TextView tvRatio;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    private VirtualLayoutManager layoutManager;
    List<List<GetAccount.DataBean>> GetAccountList;
    List<String> head_list ;
    TodayCount.AllTotalBean allTotalBean;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TongjiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TongjiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TongjiFragment newInstance(String param1, String param2) {
        TongjiFragment fragment = new TongjiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tongji, container, false);
        unbinder = ButterKnife.bind(this, view);

        toolbar.setTitle("账单统计");
        head_list = new ArrayList<>();
        head_list.add("今日收款");
        head_list.add("昨日收款");
        head_list.add("本月收款");
        head_list.add("上月收款");
        toolbar.setBackgroundDividerEnabled(false);
        layoutManager = new VirtualLayoutManager(getActivity());
        myrecy.setLayoutManager(layoutManager);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        banner.setImageLoader(new GlideImageLoader());
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                Log.d("ccc",i+" "+v+"  "+i1);
            }

            @Override
            public void onPageSelected(int i) {
                if (tvDesc!=null)
                tvDesc.setText(head_list.get(i));
                if (tvRatio!=null&&tvTotal!=null){
                    switch (i){
                        case 0:
                            tvRatio.setText(allTotalBean.getTotalDayMoney()+"");
                            tvTotal.setText("（合计"+allTotalBean.getTotalDayCount()+"笔）");
                            break;
                        case 1:
                            tvRatio.setText(allTotalBean.getTotalYestCount()+"");
                            tvTotal.setText("（合计"+allTotalBean.getTotalYestMoney()+"笔）");
                            break;
                        case 2:
                            tvRatio.setText(allTotalBean.getTotalMonthMoney()+"");
                            tvTotal.setText("（合计"+allTotalBean.getTotalMonthCount()+"笔）");
                            break;
                        case 3:
                            tvRatio.setText(allTotalBean.getLastmonthMoney()+"");
                            tvTotal.setText("（合计"+allTotalBean.getLastmonthCount()+"笔）");
                            break;
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
//                Log.d("aaa",i+"");
            }
        });

//        banner.setImages()
        // 设置组件复用回收池
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        myrecy.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        GetAccount();
        getTodayCount();
        return view;
    }




    private void getTodayCount() {
        String randomString = getRandomString(8);
        String content = getContent(randomString);
        String miyaoKey = getMiyaoKey(randomString);
        String login_user_info = GlobalConfig.getLogin_User_info();
        User user = new Gson().fromJson(login_user_info, User.class);
        newGsonRequest request = new newGsonRequest<>(getActivity(), "/api/FuBaAppIndex/TodayCount?content=" + content + "&key=" + miyaoKey + "&CashType=" + user.getCashtype(), TodayCount.class, null, response -> {

            if (response.getResult().equals("0")){
                allTotalBean=response.getAllTotal();

                banner.setImages(head_list);
                banner.start();
            }
            else dialogShow(response.getError());

        }, error -> {
        });
        MyApplication.volleyQueue.add(request);
    }

    private void GetAccount() {
        String randomString = getRandomString(8);
        String content = getContent(randomString);
        String miyaoKey = getMiyaoKey(randomString);
        String login_user_info = GlobalConfig.getLogin_User_info();
        User user = new Gson().fromJson(login_user_info, User.class);
        newGsonRequest request = new newGsonRequest<>(getActivity(), "/api/FuBaAppIndex/GetAccount?content=" + content + "&key=" + miyaoKey + "&CashType=" + user.getCashtype(), GetAccount.class, null, response -> {
            if (response.getResult().equals("0")) {
                GetAccountList = response.getData();
                initView();
            } else {
                dialogShow(response.getError());
            }
        }, error -> {
        });
        MyApplication.volleyQueue.add(request);

    }


    private void initView() {
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        LinearLayoutHelper layoutHelper = null;
        layoutHelper = new LinearLayoutHelper();

        for (int i = 0; i < GetAccountList.size(); i++) {
            adapters.add(new BaseAdapter(getActivity(), layoutHelper, R.layout.base_cell_xian, 1) {
                @Override
                public void onBindViewHolder(BaseHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                }
            });

            int finalI = i;
            List<GetAccount.DataBean> dataBean = GetAccountList.get(finalI);
            adapters.add(new BaseAdapter(getActivity(), layoutHelper, R.layout.tongji_view2, dataBean.size()) {
                @Override
                public void onBindViewHolder(BaseHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    holder.setText(R.id.tv_shoukuanzhangdan, dataBean.get(position).getTitle());
                    Glide.with(getActivity()).load(MyHelp.getBaseUrl(getActivity()) + "/" + dataBean.get(position).getImgsrc()).into((ImageView) holder.getView(R.id.iv_shoukuanzhangdan));
                }
            });
        }

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        delegateAdapter.setAdapters(adapters);
        myrecy.setAdapter(delegateAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
//        banner.stop
    }
}
