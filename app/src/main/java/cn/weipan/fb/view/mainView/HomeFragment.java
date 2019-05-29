package cn.weipan.fb.view.mainView;


import android.content.Intent;
import android.media.Image;
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
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.weipan.fb.MainActivity;
import cn.weipan.fb.MyApplication;
import cn.weipan.fb.R;
import cn.weipan.fb.db_base;
import cn.weipan.fb.model.GetBanner;
import cn.weipan.fb.model.TodayCount;
import cn.weipan.fb.model.User;
import cn.weipan.fb.model.home_modle1;
import cn.weipan.fb.tools.BaseAdapter;
import cn.weipan.fb.tools.BaseFragment;
import cn.weipan.fb.tools.BaseHolder;
import cn.weipan.fb.tools.GlideImageHomeLoader;
import cn.weipan.fb.tools.GlideImageLoader;
import cn.weipan.fb.tools.GlobalConfig;
import cn.weipan.fb.tools.MyHelp;
import cn.weipan.fb.tools.newGsonRequest;
import cn.weipan.fb.view.mianNext.ZhiHuiMaActivity;

import static cn.weipan.fb.MyApplication.URLZHM;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.toolbar)
    QMUITopBar toolbar;
    @BindView(R.id.myrecy)
    RecyclerView myrecy;
    Unbinder unbinder;
    private Banner banner;
    private TextView tvDesc,tvRatio,tvTotal;
    private VirtualLayoutManager layoutManager;
    TodayCount.AllTotalBean allTotal;
    private ImageView imageView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<String> head_list ;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle("收银主页");
        toolbar.setBackgroundDividerEnabled(false);
        layoutManager = new VirtualLayoutManager(getActivity());
        myrecy.setLayoutManager(layoutManager);
        // 设置组件复用回收池
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        myrecy.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        initView();
        return view;
    }


    private void initView(){
        head_list = new ArrayList<>();
        head_list.add("今日收款");
        head_list.add("本月收款");
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        GridLayoutHelper gridLayoutHelper=new GridLayoutHelper(3);
        gridLayoutHelper.setAutoExpand(false);
        gridLayoutHelper.setPadding(0, 0, 0, 0);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        List<home_modle1> list=new ArrayList<>();
        Type type = new TypeToken<List<home_modle1>>(){}.getType();
        list=new Gson().fromJson(db_base.home1,type);
        List<home_modle1> finalList = list;
        adapters.add(new BaseAdapter(getActivity(),gridLayoutHelper,R.layout.home_view1, finalList.size()){
            @Override
            public void onBindViewHolder(BaseHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.home_text, finalList.get(position).getName());
                holder.setImageBitmap(R.id.home_img,getBitmapByName(finalList.get(position).getImg()));
                holder.setOnClickListener(R.id.home_view,v -> {

                    switch (position){
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;

                    }


                });


            }
        });
        List<home_modle1> view2=new ArrayList<>();
        view2=new Gson().fromJson(db_base.home2,type);
        gridLayoutHelper=new GridLayoutHelper(3);
        gridLayoutHelper.setAutoExpand(false);
        gridLayoutHelper.setPadding(0, 0, 0, 5);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        List<home_modle1> finalView = view2;
        adapters.add(new BaseAdapter(getActivity(),gridLayoutHelper,R.layout.home_view2, finalView.size()){
            @Override
            public void onBindViewHolder(BaseHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.home_text, finalView.get(position).getName());
                holder.setImageBitmap(R.id.home_img,getBitmapByName(finalView.get(position).getImg()));
                holder.setOnClickListener(R.id.ll_kaquanshoukuan,v -> {

                    switch (position){
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            Intent intent=new Intent(getActivity(),ZhiHuiMaActivity.class);
                            startActivityForResult(intent,1);
                            break;
                        case 5:
                            break;
                    }
                });


            }
        });
        LinearLayoutHelper layoutHelper=null;
        layoutHelper=new LinearLayoutHelper();
        adapters.add(new BaseAdapter(getActivity(),layoutHelper,R.layout.home_view3,1){
            @Override
            public void onBindViewHolder(BaseHolder holder, int position) {
                super.onBindViewHolder(holder, position);

                banner= (Banner) holder.getView(R.id.banner);
                tvDesc= (TextView) holder.getView(R.id.tv_desc);
                tvRatio= (TextView) holder.getView(R.id.tv_ratio);
                tvTotal= (TextView) holder.getView(R.id.tv_total);
                getTodayCount();
                // tvDesc.setText(head_list.get(i));
            }


        });
        layoutHelper=new LinearLayoutHelper();
        adapters.add(new BaseAdapter(getActivity(),layoutHelper,R.layout.home_view4,1){
//            getBanner();

            @Override
            public void onBindViewHolder(BaseHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                imageView= (ImageView) holder.getView(R.id.iv_image_banner);
                getBanner();
            }
        });

        //       绑定delegateAdapter
        DelegateAdapter delegateAdapter=new DelegateAdapter(layoutManager);
        delegateAdapter.setAdapters(adapters);
        myrecy.setAdapter(delegateAdapter);

    }




    private void getTodayCount() {
        String randomString = getRandomString(8);
        String content = getContent(randomString);
        String miyaoKey = getMiyaoKey(randomString);
        String login_user_info = GlobalConfig.getLogin_User_info();
        User user = new Gson().fromJson(login_user_info, User.class);
        newGsonRequest request = new newGsonRequest<>(getActivity(), "/api/FuBaAppIndex/TodayCount?content=" + content + "&key=" + miyaoKey + "&CashType=" + user.getCashtype(), TodayCount.class, null, response -> {

            if (response.getResult().equals("0")){
                allTotal = response.getAllTotal();
                if (banner!=null)
                    initBanner();
            }else {
                dialogShow(response.getError());
            }


        }, error -> {
        });
        MyApplication.volleyQueue.add(request);
    }
//GetBanner
    private void getBanner() {
        String randomString = getRandomString(8);
        String content = getContent(randomString);
        String miyaoKey = getMiyaoKey(randomString);
        String login_user_info = GlobalConfig.getLogin_User_info();
        User user = new Gson().fromJson(login_user_info, User.class);
        newGsonRequest request = new newGsonRequest<>(getActivity(), "/api/FuBaAppIndex/GetBanner?content=" + content + "&key=" + miyaoKey + "&CashType=" + user.getCashtype(), GetBanner.class, null, response -> {

            if (response.getResult().equals("0")){
                if (imageView!=null)
                {
                    String imgurl = URLZHM+response.getData().getImgurl();
                    Log.d("aaa",imgurl);
                    Glide.with(getActivity()).load(URLZHM+response.getData().getImgurl()).into(imageView);
                }
            }
            else dialogShow(response.getError());

        }, error -> {
            dialogShow("系统异常！");
        });
        MyApplication.volleyQueue.add(request);
    }
    private void initBanner(){
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        banner.setImageLoader(new GlideImageHomeLoader());
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
                            tvRatio.setText(allTotal.getTotalDayMoney()+"");
                            tvTotal.setText("（合计"+allTotal.getTotalDayCount()+"笔）");
                            break;
                        case 1:
                            tvRatio.setText(allTotal.getTotalMonthMoney()+"");
                            tvTotal.setText("（合计"+allTotal.getTotalMonthCount()+"笔）");
                            break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                Log.d("aaa",i+"");
            }
        });
        banner.setImages(head_list);
        banner.start();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
