package base.wujiang.com.baseproject.task;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

import com.alibaba.fastjson.JSONObject;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import base.wujiang.com.baseproject.base.BaseTask;
import base.wujiang.com.baseproject.base.ReUpObject;
import base.wujiang.com.baseproject.util.Constants;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 系统自动更新功能
 * 
 * @author lyl 2016-08-22
 */
public class UpdateTask extends BaseTask<String, Integer, ReUpObject>
{
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();
    public UpdateTask(Context ctx)
    {
        this.isShowProgress = false;
        this.ctx = ctx;
    }

    @Override
    protected ReUpObject doInBackground(String... params)
    {
        Map<String, String> mapParams = new HashMap<String, String>();
        ReUpObject ro = send(mapParams);
        return ro;
    }

    /**
     * 概要说明 : 发送数据. <br>
     * 详细说明 : 发送数据. <br>
     *
     * @param fparams 
     * @return  ReUpObject 类型返回值说明
     */
    private ReUpObject send(Map<String, String> fparams)
    {
        ReUpObject json = null;
        String url = Constants.SERVER_CTX_PATH + "version.txt";// 注意修改与服务器配置的文件一致

        try {
            RequestBody body = RequestBody.create(JSON, "");
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if(response.code() == 200)
            {
                String str = response.body().string();
                json = JSONObject.parseObject(str, ReUpObject.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(ReUpObject ro)
    {

        if (null != ro)
        {
            String currentVersion = getCurrentVersion(ctx);//拿到的是gradle里面的versionName
            String newVersion = ro.getVersion();
            if (Double.parseDouble(newVersion) > Double.parseDouble(currentVersion))
            {
                final NormalDialog dialog = new NormalDialog(ctx);
                dialog.style(NormalDialog.STYLE_TWO);
                dialog.widthScale(0.6f);
                dialog.btnText("确定", "取消");
                dialog.content("存在更新版本，请更新").show();
                dialog.setOnBtnClickL(new OnBtnClickL()
                {
                    @Override
                    public void onBtnClick()
                    {
                        Uri uri = Uri.parse(Constants.SERVER_CTX_PATH + "UnitApp.apk");// 注意修改与服务器配置的APP名称一致
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        ctx.startActivity(intent);
                        dialog.dismiss();
                    }
                }, new OnBtnClickL()
                {
                    @Override
                    public void onBtnClick()
                    {
                        dialog.dismiss();
                    }
                });
            }
        }
    }

    /**
     * 概要说明 : 获取当前版本号. <br>
     * 详细说明 : 获取当前版本号. <br>
     *
     * @param context 
     * @return  String 类型返回值说明
     */
    private String getCurrentVersion(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String currentVersion = packageInfo.versionName;
            return currentVersion;
        }
        catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
