package base.wujiang.com.baseproject;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import base.wujiang.com.baseproject.greenDao.DaoMaster;
import base.wujiang.com.baseproject.greenDao.DaoSession;
import base.wujiang.com.baseproject.util.Constants;
import base.wujiang.com.baseproject.vo.Person;


public class MyApplication extends Application {

    /**
     * 全局实例
     */
    public static MyApplication instance;
    /*** QQ,微信第三方登录APPID和secret **/
    public static String pluginQQAppid = "1107519005";//QQ登录
    public static String pluginQQSecret = "VTCbPkY1DMlvBYz1";
    public static String pluginWeixinAppid = "wx9d304d4e9b419ce9";//bhsd开发者平台
    public static String pluginWeixinSecret = "83724ee21d2a8cb64bbac1b9dcbc9be2";


    /**
     * 当前登录用户
     */
    public static Person person;

    public static List<?> images=new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Constants.reGenConstants();
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(true);
        Fresco.initialize(this);
        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数

        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, "5c3fccf2b465f5e5790011cc", "BaseProject", UMConfigure.DEVICE_TYPE_PHONE, null);
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setWeixin(pluginWeixinAppid, pluginWeixinSecret);
        initGreenDao();
    }
    private static volatile DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    //初始化
    private void initGreenDao() {
        helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }
}

