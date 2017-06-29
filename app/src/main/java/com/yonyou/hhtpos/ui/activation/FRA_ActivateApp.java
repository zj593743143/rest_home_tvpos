package com.yonyou.hhtpos.ui.activation;

import android.view.View;
import android.widget.RadioButton;

import com.yonyou.framework.library.base.BaseFragment;
import com.yonyou.framework.library.bean.ErrorBean;
import com.yonyou.framework.library.common.utils.ToastUtil;
import com.yonyou.framework.library.eventbus.EventCenter;
import com.yonyou.hhtpos.R;
import com.yonyou.hhtpos.dialog.DIA_Navigation;
import com.yonyou.hhtpos.manager.ReqCallBack;
import com.yonyou.hhtpos.manager.RequestManager;
import com.yonyou.hhtpos.ui.store.ACT_BindStore;
import com.yonyou.hhtpos.view.IActivateAppView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 激活当前应用fragment
 * 作者：liushuofei on 2017/6/26 18:02
 */
public class FRA_ActivateApp extends BaseFragment implements IActivateAppView{

    @Bind(R.id.rb_next_step)
    RadioButton rbNextStep;

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
//        HashMap<String,String> hashMap = new HashMap<>();
//        RequestManager.getInstance().requestGetByAsyn("https://kyfw.12306.cn/otn/", hashMap, new ReqCallBack<String>() {
//
//            @Override
//            public void onReqSuccess(String bean) {
//                ToastUtil.makeText(mContext, "request success", false);
//            }
//
//            @Override
//            public void onFailure(String result) {
//                ToastUtil.makeText(mContext, "failure", false);
//            }
//
//            @Override
//            public void onReqFailed(ErrorBean errorBean) {
//                ToastUtil.makeText(mContext, "request failed", false);
//            }
//        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fra_activate_application;
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


    @OnClick(R.id.rb_next_step)
    public void onClick() {
//        DIA_Navigation mDialog = new DIA_Navigation(mContext);
//        mDialog.getDialog().show();

        readyGoThenKill(ACT_BindStore.class);
    }

    @Override
    public void requestActivateCode() {

    }

    @Override
    public void doActivate() {

    }
}
