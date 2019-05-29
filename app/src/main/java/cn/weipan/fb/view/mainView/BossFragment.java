package cn.weipan.fb.view.mainView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.weipan.fb.MyApplication;
import cn.weipan.fb.R;
import cn.weipan.fb.model.GetTool;
import cn.weipan.fb.model.User;
import cn.weipan.fb.tools.BaseAdapter;
import cn.weipan.fb.tools.BaseFragment;
import cn.weipan.fb.tools.BaseHolder;
import cn.weipan.fb.tools.GlobalConfig;
import cn.weipan.fb.tools.MyHelp;
import cn.weipan.fb.tools.newGsonRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BossFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BossFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.toolbar)
    QMUITopBar toolbar;
    @BindView(R.id.myrecy)
    RecyclerView myrecy;
    Unbinder unbinder;
    private String cashType;
    List<List<GetTool.DataBean>> data;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private VirtualLayoutManager layoutManager;

    public BossFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BossFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BossFragment newInstance(String param1, String param2) {
        BossFragment fragment = new BossFragment();
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

        String login_user_info = GlobalConfig.getLogin_User_info();

        User user= new Gson().fromJson(login_user_info,User.class);
        cashType=user.getCashtype();
        toolbar.setBackgroundDividerEnabled(false);
        layoutManager = new VirtualLayoutManager(getActivity());
        myrecy.setLayoutManager(layoutManager);
        // 设置组件复用回收池
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        myrecy.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        if (TextUtils.equals(cashType,"6")){
            getdata();
        }
        else {
            initView();
        }


        return view;
    }


    private void getdata(){
        String randomString = getRandomString(8);
        String content = getContent(randomString).trim();
        String miyaoKey = getMiyaoKey(randomString).trim();
        newGsonRequest request=new newGsonRequest<>(getActivity(),
                "/api/FuBaAppIndex/GetTool?content="+ content+ "&key=" + miyaoKey,GetTool.class,null, response -> {
            if (response.getResult().equals("0")){
                data = response.getData();
                initView();
            }
            else dialogShow(response.getError());

        },error -> {
            dialogShow("系统异常");
        });
        MyApplication.volleyQueue.add(request);
    }

    private void initView(){

        if (TextUtils.equals(cashType,"6")){
            List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
            GridLayoutHelper gridLayoutHelper=new GridLayoutHelper(3);
            gridLayoutHelper.setAutoExpand(false);
            gridLayoutHelper.setPadding(0, 0, 0, 0);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
            List<GetTool.DataBean> modle1=data.get(0);
            adapters.add(new BaseAdapter(getActivity(),gridLayoutHelper,R.layout.boss_view1, modle1.size()){
                @Override
                public void onBindViewHolder(BaseHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    holder.setText(R.id.tv_shishifenxi, modle1.get(position).getTitle());
                    ImageView view= (ImageView) holder.getView(R.id.iv_shishifenxi);
                    Glide.with(getActivity()).load(MyHelp.getBaseUrl(getActivity())+"/"+modle1.get(position).getImgsrc()).into(view);

                    holder.setOnClickListener(R.id.ll_shishifenxi,v -> {
//                        dialogShow(modle1.get(position).get);
                    });

                }
            });
            LinearLayoutHelper layoutHelper=null;
            layoutHelper=new LinearLayoutHelper();
            List<GetTool.DataBean> modle2=data.get(1);
            adapters.add(new BaseAdapter(getActivity(),layoutHelper,R.layout.tongji_view2,modle2.size()){
                @Override
                public void onBindViewHolder(BaseHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    holder.setText(R.id.tv_shoukuanzhangdan, modle2.get(position).getTitle());
                    ImageView view= (ImageView) holder.getView(R.id.iv_shoukuanzhangdan);
                    Glide.with(getActivity()).load(MyHelp.getBaseUrl(getActivity())+"/"+modle2.get(position).getImgsrc()).into(view);
                    holder.setOnClickListener(R.id.rl_shoukuanzhangdan,v -> {

                    });

                }
            });
            List<GetTool.DataBean> modle3=data.get(2);
            adapters.add(new BaseAdapter(getActivity(),layoutHelper,R.layout.boss_view2,modle3.size()){

                @Override
                public void onBindViewHolder(BaseHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    //tv_faquan
                    holder.setText(R.id.tv_faquan, modle3.get(position).getTitle());
                    holder.setOnClickListener(R.id.rl_faquan,v -> {

                    });
                }
            });
            //       绑定delegateAdapter
            DelegateAdapter delegateAdapter=new DelegateAdapter(layoutManager);
            delegateAdapter.setAdapters(adapters);
            myrecy.setAdapter(delegateAdapter);
        }
        else {
            List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
            DelegateAdapter delegateAdapter=new DelegateAdapter(layoutManager);
            delegateAdapter.setAdapters(adapters);

            EmptyWrapper wrapper = new EmptyWrapper(delegateAdapter);
            wrapper.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_null_boss, myrecy, false));
            myrecy.setAdapter(wrapper);

        }



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
