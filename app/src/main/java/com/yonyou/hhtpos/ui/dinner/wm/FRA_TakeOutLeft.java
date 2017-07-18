package com.yonyou.hhtpos.ui.dinner.wm;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.yonyou.framework.library.base.BaseFragment;
import com.yonyou.framework.library.bean.ErrorBean;
import com.yonyou.framework.library.eventbus.EventCenter;
import com.yonyou.hhtpos.R;
import com.yonyou.hhtpos.adapter.TakeOutFragmentAdapter;
import com.yonyou.hhtpos.bean.WMOpenOrderEntity;
import com.yonyou.hhtpos.presenter.ITakeOutPresenter;
import com.yonyou.hhtpos.presenter.Impl.TakeOutPresenterImpl;
import com.yonyou.hhtpos.view.ITakeOutView;
import com.yonyou.hhtpos.widgets.PagerSlidingTabStrip;

import butterknife.Bind;

/**
 * 外卖左侧fragment
 * 作者：liushuofei on 2017/7/6 10:46
 */
public class FRA_TakeOutLeft extends BaseFragment implements ITakeOutView{

    @Bind(R.id.psts_tab)
    PagerSlidingTabStrip mTab;
    @Bind(R.id.vp_take_out)
    ViewPager mViewPager;

    /**当前Fragment */
    private FRA_TakeOutList mCurrentFramgent;
    /**记录前一个被选中的tab的位置 */
    private int prePosition;

    private TakeOutFragmentAdapter mFragmentAdapter;

    public static final int RB_ALL = 0;
    public static final int RB_NO_ORDER = 1;
    public static final int RB_PLACE_ORDER = 2;
    public static final int RB_CHECKED_OUT = 3;
    public static final int RB_REFUNDED = 4;

    /**中间者 */
    private ITakeOutPresenter mTakeOutPresenter;

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
        setVpAdapter();

        initSlidingTab();

        mTakeOutPresenter = new TakeOutPresenterImpl(mContext, this);

        WMOpenOrderEntity bean = new WMOpenOrderEntity();
        bean.setTakeOutCompanyId("C4BECEC6040000008000000000296000");
        bean.setName("小点的王");
        bean.setPhone("13466668888");
        bean.setAddress("海淀区苏州街维亚大厦");
        bean.setPersonNum("3");
        bean.setReserveTime("2017-7-18 15:30:23");
        bean.setSendNow("Y");
        bean.setShopId("C482CE78AC000000AA8000000003A000");
        mTakeOutPresenter.openOrder(bean);
    }

    private void setVpAdapter() {
        mFragmentAdapter = new TakeOutFragmentAdapter(getSupportFragmentManager(), mContext);
        mViewPager.setAdapter(mFragmentAdapter);
    }

    private void initSlidingTab(){
        mTab.setViewPager(mViewPager);
        mTab.setIndicatorColor(mContext.getResources().getColor(R.color.color_eb6247));
        TextView tabTextView = (TextView) mTab.getTabsContainer().getChildAt(prePosition); //设置默认选中第一个时为红色
        tabTextView.setTextColor(mContext.getResources().getColor(R.color.color_eb6247));

        mTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TextView tabTextView = (TextView) mTab.getTabsContainer().getChildAt(position);
                tabTextView.setTextColor(mContext.getResources().getColor(R.color.color_eb6247));
                TextView preTabTextView = (TextView) mTab.getTabsContainer().getChildAt(prePosition);
                preTabTextView.setTextColor(mContext.getResources().getColor(R.color.color_222222));
                prePosition = position;
                //获取当前显示的Fragment
                mCurrentFramgent = (FRA_TakeOutList) mFragmentAdapter.instantiateItem(mViewPager, mViewPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fra_take_out_left;
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

    @Override
    public void openOrder() {

    }
}