package com.yonyou.hhtpos.ui.activation;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yonyou.framework.library.base.BaseFragment;
import com.yonyou.framework.library.bean.ErrorBean;
import com.yonyou.framework.library.eventbus.EventCenter;
import com.yonyou.hhtpos.R;
import com.yonyou.hhtpos.ui.login.ACT_ResetNewPwd;
import com.yonyou.hhtpos.util.TimeUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 验证手机号fragment
 * 作者：liushuofei on 2017/6/27 10:21
 */
public class FRA_VerifyPhone extends BaseFragment {

    @Bind(R.id.tv_get_code)
    TextView tvGetCode;
    @Bind(R.id.rb_submit)
    RadioButton rbSubmit;

    /**倒计时工具类 */
    private TimeUtil timer;
    /**验证码倒计时总时间 */
    private final int msgTime = 60 * 1000;
    /**倒计时间隔时间 */
    private final int countDownInterval = 1000;

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
        timer = new TimeUtil(msgTime, countDownInterval, mContext);
        timer.setView(tvGetCode);
        timer.setTxt(mContext.getString(R.string.verify_phone_get_code));
        timer.setColor(ContextCompat.getColor(mContext, R.color.color_999999));
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

    @OnClick({R.id.tv_get_code, R.id.rb_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                timer.start();
                break;

            case R.id.rb_submit:
                readyGoThenKill(ACT_ResetNewPwd.class);
                break;
        }
    }
}
