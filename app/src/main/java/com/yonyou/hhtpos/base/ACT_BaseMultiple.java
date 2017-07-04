package com.yonyou.hhtpos.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yonyou.framework.library.base.BaseActivity;
import com.yonyou.hhtpos.R;
import com.yonyou.hhtpos.bean.NavigationEntity;
import com.yonyou.hhtpos.widgets.LeftNavigationView;

import butterknife.Bind;

/**
 * 多重布局基类
 * 作者：liushuofei on 2017/6/23 09:29
 */
public abstract class ACT_BaseMultiple extends BaseActivity {

    @Bind(R.id.fl_left)
    protected FrameLayout flLeft;
    @Bind(R.id.fl_right)
    protected FrameLayout flRight;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_base_multiple;
    }

    @Override
    protected void initViewsAndEvents() {
        // 初始化
        initView();

        // 左侧的权重动态设置
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.FILL_PARENT);
        params.weight = getLeftWeight();
        flLeft.setLayoutParams(params);

        // 替换left fragment
        FragmentTransaction leftTrans = getSupportFragmentManager().beginTransaction();
        leftTrans.add(R.id.fl_left, getLeftContent());
        leftTrans.commitAllowingStateLoss();

        // 替换right fragment
        FragmentTransaction rightTrans = getSupportFragmentManager().beginTransaction();
        rightTrans.add(R.id.fl_right, getRightContent());
        rightTrans.commitAllowingStateLoss();
    }

    protected abstract void initView();

    protected abstract float getLeftWeight();

    protected abstract Fragment getLeftContent();

    protected abstract Fragment getRightContent();
}
