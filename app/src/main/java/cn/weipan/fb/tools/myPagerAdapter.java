package cn.weipan.fb.tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class myPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private List<Fragment> list;


    public myPagerAdapter(FragmentManager fm, List<Fragment> Fragmentlist) {
        super(fm);
        this.fragmentManager=fm;
        this.list=Fragmentlist;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
      return list.size();
    }


}
