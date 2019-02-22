package base.wujiang.com.baseproject.base;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import base.wujiang.com.baseproject.MyApplication;
import base.wujiang.com.baseproject.util.JsonUtil;


/**
 * 基础任务类
 * @param <T>
 * @param <P>
 * @param <Q>
 */
public abstract class BaseTask<T, P, Q> extends AsyncTask<T, P, Q>
{
    /**
     * 
     */
    protected Context ctx;

    /**
     * 
     */
    protected boolean isShowProgress = true;

    /**
     * 
     */
    protected String destination;

    /**
     * 
     */
    protected String method;

    public static String buildApiUrl(String destination, String method)
    {
        String url = "destination=" + destination + "&method=" + method;
        url += "&rnd=" + Math.random();
        return url;
    }

    protected String addParamToUrl(String destination, String method)
    {
        String url = "destination=" + destination + "&method=" + method;
        return url;
    }

    @Override
    protected void onPreExecute()
    {
        if (isShowProgress && ctx != null)
        {
//            SVProgressHUD.showWithStatus(ctx, "加载中...", SVProgressHUDMaskType.BlackCancel);
        }
    }

    /**
     * 返回结果
     * @param ro
     * @return
     */
    protected int isSuccess(ReturnMap ro)
    {
        if (ctx == null)
        {
            ctx = MyApplication.instance;
        }
        if (isShowProgress && ctx != null)
        {
//            SVProgressHUD.dismiss(ctx);
        }
        return ro.getCode();
    }

    /**
     * 返回结果
     * @param ro
     * @return
     */
    protected boolean isSuccess(ReObject ro)
    {
        if (isShowProgress && ctx != null)
        {
//            SVProgressHUD.dismiss(ctx);
        }
        else
        {
            if (ctx == null)
            {
                ctx = MyApplication.instance;
            }
        }
        if (!ro.getSuccess())
        {
            String reason = ro.getContent().toString();
            Toast.makeText(ctx, reason, Toast.LENGTH_LONG).show();
        }
        return ro.getSuccess();
    }

    @SuppressWarnings("hiding")
    protected <T> List<ReObject> reGentRoObjectList(ReObject ro, Class<T> clazz)
    {
        List<T> list = JsonUtil.getListFromJson(ro, clazz);
        ro.setContent(list);
        List<ReObject> roList = new ArrayList<ReObject>();
        roList.add(ro);
        return roList;
    }
}
