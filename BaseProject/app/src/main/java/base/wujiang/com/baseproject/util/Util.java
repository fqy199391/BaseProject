/*
 * @author max V 1.0.0 Create at 2015-10-11 上午9:39:10
 */
package base.wujiang.com.baseproject.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import base.wujiang.com.baseproject.MyApplication;
import base.wujiang.com.baseproject.R;

/**
 * 概要说明 : 常用工具类.  <br>
 * 详细说明 : 常用工具类.  <br>
 * 创建时间 : 2017-7-12 下午2:38:27 <br>
 * @author by luyonglong
 */
public class Util
{

    public static void showToast(Context context, String message) {
        if (context != null) {
            View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast, null);                //加载Toast布局
            TextView mTextView = (TextView) toastRoot.findViewById(R.id.message);
            mTextView.setText(message);                                                                 //为控件设置属性
            Toast toastStart = new Toast(context);                                                      //Toast的初始化
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);        //获取屏幕高度
            int height = wm.getDefaultDisplay().getHeight();
            toastStart.setGravity(Gravity.BOTTOM, 0, height / 6);                                       //Toast的Y坐标是屏幕高度的1/3,不会出现不适配的问题
            toastStart.setDuration(Toast.LENGTH_SHORT);
            toastStart.setView(toastRoot);
            toastStart.show();
        }
    }
    public static SoundPool sp ;
    public static Map<Integer, Integer> suondMap;
    public static Context context;
    
    //init sound pool
    public static void initSoundPool(Context context){
//        Util.context = context;
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        suondMap = new HashMap<Integer, Integer>();
        suondMap.put(1, sp.load(context, R.raw.msg, 1));
    }
    
    //play sound
    public static  void play(int sound, int number){
        AudioManager am = (AudioManager) MyApplication.instance.getSystemService(MyApplication.instance.AUDIO_SERVICE);
           //return AlarmManager The largest volume at present
        float audioMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            
           //return AlarmManager The largest volume at present
            float audioCurrentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
            float volumnRatio = audioCurrentVolume/audioMaxVolume;
            sp.play(
                    suondMap.get(sound), //get sound id 
                    audioCurrentVolume, //left volume
                    audioCurrentVolume, //ringht volume
                    1, //priority 
                    number, //cycles
                    1);//0.5-2.0 speed 
        }
    
    
    
    /**
     * 概要说明 : 退出. <br>
     * 详细说明 : 退出. <br>
     *
     * @param ctx  void 类型返回值说明
     * @author by luyonglong @ 2017-7-12下午2:39:00
     */
    public static void exit(Context ctx)
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(intent);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /**
     * 概要说明 : 判断网络是否连接. <br>
     * 详细说明 : 判断网络是否连接. <br>
     *
     * @param context 
     * @return  boolean 类型返回值说明
     * @author by luyonglong @ 2017-7-12下午2:39:45
     */
    public static boolean isNetworkConnected(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
            if (info != null)
            {
                return info.isAvailable();
            }
        }
        return false;
    }

    /**
     * 概要说明 : 判断wifi是否开启. <br>
     * 详细说明 : 判断wifi是否开启. <br>
     *
     * @param context 
     * @return  boolean 类型返回值说明
     * @author by luyonglong @ 2017-7-12下午2:40:06
     */
    public static boolean isWifiAvailable(Context context)
    {
        boolean isWifiAvailable = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI)
        {
            isWifiAvailable = true;
        }
        return isWifiAvailable;
    }

    /**
     * 概要说明 : 判断wifi是否可用. <br>
     * 详细说明 : 判断wifi是否可用. <br>
     *
     * @param context 
     * @return  boolean 类型返回值说明
     * @author by luyonglong @ 2017-7-12下午2:41:03
     */
    public static boolean isWifiConnected(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null)
            {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 概要说明 : 打开浏览器浏览网址. <br>
     * 详细说明 : 打开浏览器浏览网址. <br>
     *
     * @param context 上下文
     * @param url 需要浏览的网址
     */
    public static void openBrowser(Context context, String url)
    {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri contentUrl = Uri.parse(url);
        intent.setData(contentUrl);
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");// 安卓自带浏览器
        context.startActivity(intent);
    }

    /**
     * 概要说明 : 将数据写入缓存SharedPreferences. <br>
     * 详细说明 : fields为key数组，values为值数组. <br>
     *
     * @param ctx 
     * @param fields 
     * @param values  void 类型返回值说明
     */
    public static void writeToSHA(Context ctx, String[] fields, String[] values)
    {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.SHA_KEY, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        int i = 0;
        for (String fieldItem : fields)
        {
            editor.putString(fieldItem, values[i++]);
        }
        editor.commit();
    }

    /**
     * 概要说明 : 清除缓存. <br>
     * 详细说明 : 清除缓存. <br>
     *
     * @param ctx  void 类型返回值说明
     */
    public static void clearSHA(Context ctx)
    {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.SHA_KEY, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 概要说明 : 读取SharedPreferences中存储的缓存数据. <br>
     * 详细说明 : 若缓存中不存在则换回defaultString. <br>
     *
     * @param ctx 
     * @param key 
     * @param defaultString 
     * @return  String 类型返回值说明
     */
    public static String readFromSHA(Context ctx, String key, String defaultString)
    {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.SHA_KEY, Context.MODE_PRIVATE);
        String value = sp.getString(key, defaultString);
        return value;
    }

    /**
     * 概要说明 : 将对象的值转为json存入缓存. <br>
     * 详细说明 : 将对象的值转为json存入缓存. <br>
     *
     * @param ctx 
     * @param key 
     * @param obj  void 类型返回值说明
     */
    public static void writeObjectToSHA(Context ctx, String key, Object obj)
    {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        SharedPreferences sp = ctx.getSharedPreferences(Constants.SHA_KEY, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, json);
        editor.commit();
    }

    /**
     * 概要说明 : 读取缓存中的json数据并转为对应的对象数据. <br>
     * 详细说明 : 读取缓存中的json数据并转为对应的对象数据. <br>
     *
     * @param ctx 
     * @param key 
     * @param <T> 
     * @param clazz 
     * @return  Object 类型返回值说明
     */
    public static <T> Object readObjectToSHA(Context ctx, String key, Class<T> clazz)
    {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.SHA_KEY, Context.MODE_PRIVATE);
        String json = sp.getString(key, "");
        if (json == null || "".equals(json))
        {
            return null;
        }
        Gson gson = new Gson();
        Object object = gson.fromJson(json, clazz);
        return object;
    }

    /**
     * 概要说明 : 清除缓存中的用户数据. <br>
     * 详细说明 : 清除缓存中的用户数据. <br>
     *
     * @param ctx  void 类型返回值说明
     */
    public static void clearUserSHA(Context ctx)
    {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.SHA_KEY, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(Constants.USER_KEY, null);
        editor.commit();
    }

    /**
     * 概要说明 : . <br>
     * 详细说明 : . <br>
     *
     * @return  int 类型返回值说明
     */
    public static int getSdkInt()
    {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 概要说明 : . <br>
     * 详细说明 : . <br>
     *
     * @return  boolean 类型返回值说明
     */
    public static boolean isSdk40Up()
    {
        if (Build.VERSION.SDK_INT >= 14)
        {
            return true;
        }
        return false;
    }

    /**
     * 概要说明 : . <br>
     * 详细说明 : . <br>
     *
     * @return  String 类型返回值说明
     */
    public static String genUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 概要说明 : . <br>
     * 详细说明 : . <br>
     *
     * @param ctx  void 类型返回值说明
     */
    public static void hideSoftKeyboard(Activity ctx)
    {
        ctx.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * 概要说明 : 检测Sdcard是否存在. <br>
     * 详细说明 : 检测Sdcard是否存在. <br>
     *
     * @return  boolean 类型返回值说明
     */
    public static boolean isExitsSdcard()
    {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 概要说明 : 改变对应字符使图片能够显示. <br>
     * 详细说明 : 改变对应字符使图片能够显示. <br>
     *
     * @param result String
     * @return  String 类型返回值说明
     */
    public static String changeImgUrl(String result)
    {
        if (result != null)
        {
            if (result.length() == 0)
            {
                return "";
            }
            String s = result.replaceAll("img src=\"", "img src=\"" + Constants.SERVER_CTX_PATH);
            return s;
        }
        return null;
    }
    /**
     * 概要说明 : 获取long型数据. <br>
     * 详细说明 : 获取long型数据. <br>
     *
     * @param ctx 
     * @param key  
     * @param defaultLong
     * @return  long 类型返回值说明
     */
    public static long readFromSHA(Context ctx, String key, long defaultLong)
    {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.SHA_KEY, Context.MODE_PRIVATE);
        long value = sp.getLong(key, defaultLong);
        return value;
    }

    /**
     * 概要说明 : 存储long型数据. <br>
     * 详细说明 : 存储long型数据. <br>
     *
     * @param ctx 
     * @param key 
     * @param value  void 类型返回值说明
     */
    public static void writeToSHA(Context ctx, String key, long value)
    {
        SharedPreferences sp = ctx.getSharedPreferences(Constants.SHA_KEY, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

}
