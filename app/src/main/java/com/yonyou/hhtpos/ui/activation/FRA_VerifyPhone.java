package com.yonyou.hhtpos.ui.activation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yonyou.framework.library.base.BaseFragment;
import com.yonyou.framework.library.bean.ErrorBean;
import com.yonyou.framework.library.eventbus.EventCenter;
import com.yonyou.hhtpos.R;
import com.yonyou.hhtpos.ui.login.ACT_ResetNewPwd;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 验证手机号fragment
 * 作者：liushuofei on 2017/6/27 10:21
 */
public class FRA_VerifyPhone extends BaseFragment {

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fra_verify_phone;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void showBusinessError(ErrorBean error) {

    }

    @OnClick(R.id.rb_submit)
    public void onClick() {
        readyGoThenKill(ACT_ResetNewPwd.class);
    }
}
