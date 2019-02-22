/**
 * 金鸽公司源代码，版权归金鸽公司所有。<br>
 * 项目名称 : FireFighterP
 */

package base.wujiang.com.baseproject.task;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import base.wujiang.com.baseproject.base.ReObject;
import base.wujiang.com.baseproject.base.ReturnMap;
import base.wujiang.com.baseproject.callback.StringDialogCallback;
import base.wujiang.com.baseproject.fragment.MainHomeFragment;

/**
 * 概要说明 : 通用查询.  <br>
 * 详细说明 : 通用查询.  <br>
 * 创建时间 : 2017-8-4 上午9:10:45 <br>
 *
 * @author by fanqingyu
 */
public class GeneralQueryTask {
    /**
     * 方法名枚举
     */
    public static enum GeneralQueryType {
        /**
         * 依次为：获取安全员
         */
        honInfo, geTasklist, queryLDFireManInfos
    }

    /**
     * ctx
     */
    public static Context ctx;

    /**
     * 当前调用的方法定义
     */
    public static GeneralQueryType methodType;

    /**
     * 当前调用的contrller
     */
    public static String controller;

    /**
     * 若调用该方法的是fragment则存放对应的fragment信息
     */
    public static Fragment fragment;

    public GeneralQueryTask(Context ctx, Fragment fragment, String contorller, GeneralQueryType methodType, boolean isShowProgress) {
        super();
        this.ctx = ctx;
        this.fragment = fragment;
        this.methodType = methodType;
        this.controller = contorller;
    }

    /*
     * (non-Javadoc)
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    public static ReturnMap loadData(String... params) {
        String apiUrl = "http://112.16.72.47:10080//accept.do?";
//        String apiUrl = Constants.BASE_URL_MVC_GETDATA + controller + "/" + methodType.toString() + ".action?";
        Map<String, String> mapParams = new HashMap<String, String>();
        HttpParams httpparams = new HttpParams();

        if (GeneralQueryType.honInfo == methodType) {
            httpparams.put("page", params[0]);
            httpparams.put("rows", params[1]);
            httpparams.put("unitId", params[2]);
            httpparams.put("name", params[3]);
        } else if (GeneralQueryType.geTasklist == methodType) {
            httpparams.put("page", params[0]);
            httpparams.put("rows", params[1]);
            httpparams.put("taskType", "2");
            httpparams.put("status", "3");
            httpparams.put("personId", "204");
        } else if (GeneralQueryType.queryLDFireManInfos == methodType) {
            httpparams.put("destination", controller);
            httpparams.put("method", methodType.toString());
            httpparams.put("pageNumber", params[0]);
            httpparams.put("pageSize", params[1]);
        }

        OkGo.getInstance()
                .addCommonParams(httpparams)
                .<String>post(apiUrl)//
                .tag(ctx)//
                .execute(new StringDialogCallback((Activity) ctx) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = response.body().toString();
                        ReturnMap ro = null;
                        if (!result.startsWith("{") && !result.startsWith("[")) {
                            result = result.substring(result.indexOf("{"));
                        }
                        ro = JSONObject.parseObject(result, ReturnMap.class);
                        if (GeneralQueryType.honInfo == methodType) {
                            if (GeneralQueryTask.fragment instanceof MainHomeFragment) {
                                ((MainHomeFragment) GeneralQueryTask.fragment).reloadData(ro);
                            }
                        }
                       else if (GeneralQueryType.queryLDFireManInfos == methodType) {
                         ReObject   ro2 = JSONObject.parseObject(result, ReObject.class);
                            if (GeneralQueryTask.fragment instanceof MainHomeFragment) {
                                ((MainHomeFragment) GeneralQueryTask.fragment).reloadObjData(ro2);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        if (response.body() != null) {
                            makeErrorJsonMVC(response.body().toString());
                        }
                    }
                });
        return null;
    }

    public static ReturnMap makeErrorJsonMVC(String msg) {
        ReturnMap ro = new ReturnMap();
        ro.setCode(-1);
        ro.setMsg(msg);
        return ro;
    }
}
