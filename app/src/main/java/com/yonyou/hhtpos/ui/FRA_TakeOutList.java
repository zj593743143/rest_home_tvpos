package com.yonyou.hhtpos.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yonyou.framework.library.base.BaseFragment;
import com.yonyou.framework.library.bean.ErrorBean;
import com.yonyou.framework.library.eventbus.EventCenter;
import com.yonyou.hhtpos.R;

import butterknife.Bind;

/**
 * 外带列表
 * 作者：liushuofei on 2017/7/4 16:45
 */
public class FRA_TakeOutList extends BaseFragment {

    @Bind(R.id.ll_root)
    LinearLayout mRoot;
    @Bind(R.id.lv_take_out)
    ListView mListView;

    /**传入数据 */
    public static final String TYPE = "type";
    private int type;

    public static final FRA_TakeOutList newInstance(int type)
    {
        FRA_TakeOutList f = new FRA_TakeOutList();
        Bundle bdl = new Bundle(1);
        bdl.putInt(TYPE, type);
        f.setArguments(bdl);
        return f;
    }

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
        return mListView;
    }

    @Override
    protected void initViewsAndEvents() {
        // 无数据页面
         showEmpty(R.drawable.default_no_order, mContext.getString(R.string.order_no_data));
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fra_take_out_list;
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
}
