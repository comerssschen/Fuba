package cn.weipan.fb.tools;

import android.annotation.SuppressLint;

import com.qmuiteam.qmui.arch.QMUIActivity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

@SuppressLint("Registered")
public class QMUIBaseActivity extends QMUIActivity {

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(this, 100);
    }

    @Override
    public void onResume() {
        super.onResume();
//        QDUpgradeManager.getInstance(getContext()).runUpgradeTipTaskIfExist(this);

    }
}
