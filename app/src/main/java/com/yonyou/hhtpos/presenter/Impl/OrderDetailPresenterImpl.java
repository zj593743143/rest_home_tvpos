package com.yonyou.hhtpos.presenter.Impl;

import android.content.Context;

import com.yonyou.framework.library.bean.ErrorBean;
import com.yonyou.framework.library.common.CommonUtils;
import com.yonyou.hhtpos.R;
import com.yonyou.hhtpos.base.BaseLoadedListener;
import com.yonyou.hhtpos.bean.wd.OrderDetailEntity;
import com.yonyou.hhtpos.interactor.IOrderDetailInteractor;
import com.yonyou.hhtpos.interactor.Impl.OrderDetailInteractorImpl;
import com.yonyou.hhtpos.presenter.IOrderDetailPresenter;
import com.yonyou.hhtpos.view.IOrderDetailView;


/**
 * Created by zj on 2017/7/17.
 * 邮箱：zjuan@yonyou.com
 * 描述：外带订单详情
 */
public class OrderDetailPresenterImpl implements IOrderDetailPresenter {

    private Context mContext;
    private IOrderDetailView mOrderDetailView;
    private IOrderDetailInteractor mOrderDetailInteractor;

    public OrderDetailPresenterImpl(Context mContext, IOrderDetailView orderDetailView) {
        this.mContext = mContext;
        this.mOrderDetailView = orderDetailView;
        mOrderDetailInteractor = new OrderDetailInteractorImpl(new OrderDetailListener());
    }


    @Override
    public void requestOrderDetail(String tableBillId) {
        mOrderDetailView.showDialogLoading(mContext.getString(R.string.network_loading));
        mOrderDetailInteractor.requestOrderDetail(tableBillId);
    }

    private class OrderDetailListener implements BaseLoadedListener<OrderDetailEntity> {


        @Override
        public void onSuccess(int event_tag, OrderDetailEntity data) {
            mOrderDetailView.dismissDialogLoading();
            mOrderDetailView.requestOrderDetail(data);
        }

        @Override
        public void onError(String msg) {
            mOrderDetailView.dismissDialogLoading();
            CommonUtils.makeEventToast(mContext, msg, false);
        }

        @Override
        public void onException(String msg) {
            mOrderDetailView.dismissDialogLoading();
            mOrderDetailView.showException(msg);
            //CommonUtils.makeEventToast(mContext, msg, false);
        }

        @Override
        public void onBusinessError(ErrorBean error) {
            mOrderDetailView.dismissDialogLoading();
            mOrderDetailView.showBusinessError(error);
            //CommonUtils.makeEventToast(mContext, error.getMsg(), false);
        }
    }
}
