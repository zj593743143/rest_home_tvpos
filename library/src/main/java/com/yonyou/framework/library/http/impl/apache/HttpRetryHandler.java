package com.yonyou.framework.library.http.impl.apache;

import android.content.Context;

import com.yonyou.framework.library.http.exception.HttpNetException;
import com.yonyou.framework.library.http.exception.NetException;
import com.yonyou.framework.library.http.network.Network;
import com.yonyou.framework.library.common.log.Elog;

import org.apache.http.NoHttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HttpContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashSet;

import javax.net.ssl.SSLException;

/**
 * 作者：addison on 15/12/15 18:50
 * 邮箱：lsf@yonyou.com
 * determine whether to send request try again.
 * 试验发现，当url非法时，HttpURLConnection 报MalformedURLException异常，
 * 而apache httpclient会报IllegalStateException异常。
 * 当url非法，和ssl错误时，不用重试。 当有可用网络但连接不稳定时，一般会报IO异常，此种情况尝试重试，以提高成功率。
 * 继承StandardHttpRequestRetryHandler因用到其判断请求方式是否幂等和连接是否取消等方法。

 */
public class HttpRetryHandler extends StandardHttpRequestRetryHandler {
    public static final String TAG = HttpRetryHandler.class.getSimpleName();
    private HashSet<Class<?>> exceptionWhitelist = new HashSet<Class<?>>();
    private HashSet<Class<?>> exceptionBlacklist = new HashSet<Class<?>>();

    public final int retrySleepTimeMS;

    /**
     * @param retrySleepTimeMS        if the network is unstable, wait retrySleepTimeMS then start retry.
     * @param requestSentRetryEnabled true if it's OK to retry requests that have been sent
     */
    public HttpRetryHandler(int retrySleepTimeMS, boolean requestSentRetryEnabled) {
        super(0, requestSentRetryEnabled);
        this.retrySleepTimeMS = retrySleepTimeMS;
        exceptionWhitelist.add(NoHttpResponseException.class);
        exceptionWhitelist.add(SocketException.class);
        exceptionWhitelist.add(SocketTimeoutException.class);
        exceptionWhitelist.add(ConnectTimeoutException.class);

        exceptionBlacklist.add(UnknownHostException.class);
        exceptionBlacklist.add(FileNotFoundException.class);
        exceptionBlacklist.add(SSLException.class);
        exceptionBlacklist.add(ConnectException.class);
    }

    public boolean retryRequest(IOException exception, int retryCount, int maxRetries, HttpContext context,
                                Context appContext) throws HttpNetException, InterruptedException {
        boolean retry = true;
        if (retryCount > maxRetries) {
                Elog.w(TAG, "retry count > max retry times..");
            throw new HttpNetException(exception);
        } else if (isInList(exceptionBlacklist, exception)) {
                Elog.w(TAG, "exception in blacklist..");
            retry = false;
        } else if (isInList(exceptionWhitelist, exception)) {
                Elog.w(TAG, "exception in whitelist..");
            retry = true;
        }
        if (retry) {
            // 判断连接是否取消，非否幂等请求是否重试
            retry = retryRequest(context);
        }
        if (retry) {
            if (appContext != null) {
                if (Network.isConnected(appContext)) {
                    Elog.d(TAG, "Network isConnected, retry now");
                } else if (Network.isConnectedOrConnecting(appContext)) {
                    Elog.v(TAG, "Network is Connected Or Connecting, wait for retey : "
                                + retrySleepTimeMS + " ms");
                    Thread.sleep(retrySleepTimeMS);
                } else {
                    Elog.d(TAG, "Without any Network , immediately cancel retry");
                    throw new HttpNetException(NetException.NetworkNotAvilable);
                }
            } else {
                    Elog.v(TAG, "app context is null..");
                Elog.v(TAG, "wait for retry : " + retrySleepTimeMS + " ms");
                Thread.sleep(retrySleepTimeMS);
            }
        }
        Elog.i(TAG, "retry: " + retry + " , retryCount: " + retryCount + " , exception: " + exception);
        return retry;
    }

    protected boolean isInList(HashSet<Class<?>> list, Throwable error) {
        for (Class<?> aList : list) {
            if (aList.isInstance(error)) {
                return true;
            }
        }
        return false;
    }
}

