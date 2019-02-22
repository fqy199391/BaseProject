package base.wujiang.com.baseproject.util;

import android.os.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import base.wujiang.com.baseproject.MyApplication;


/**
 * 概要说明 : 全局常量类.  <br>
 */
public class Constants
{
    /**
     * 是否调试模式
     */
    public static boolean IS_DEBUG = false;

    /**
     * SharedPreferences缓存空间name值  // 新建项目时务必更改
     */
    public static String SHA_KEY = "test";

    /**
     * SharedPreferences缓存空间中用户数据的key值，用户存取用户json数据  // 新建项目时务必更改
     */
    public static String USER_KEY = "test_json";

    /**
     * app文件存放根目录名称  // 新建项目时务必更改
     */
    public static String APP_ROOT_FOLDER_NAME = "test";

    /**
     * app数据库  新建项目时务必更改
     */
    public static final String DATABASE_NAME = "test.db";

    /**
     * app语音目录
     */
    public static String APP_SOUND_PATH = Environment.getExternalStorageDirectory() + "/" + APP_ROOT_FOLDER_NAME + "/sounds/";

    /**
     * app图片目录
     */
    public static String APP_IMAGE_PATH = Environment.getExternalStorageDirectory() + "/" + APP_ROOT_FOLDER_NAME + "/images/";

    /**
     * app视频目录
     */
    public static String APP_VIDEO_PATH = Environment.getExternalStorageDirectory() + "/" + APP_ROOT_FOLDER_NAME + "/videos/";



    /**
     * 服务器地址
     */
    public static String SERVER_IP = "";

    /**
     * 服务器端口
     */
    public static String SERVER_PORT = "";

    /**
     * 系统名称
     */
    public static String SERVER_CTX_NAME = "";

    /**
     * 服务器SOCKET端口
     */
    public static String SERVER_SOCKET_PORT = "";

    /**
     * 服务端链接地址
     */
    public static String SERVER_CTX_ROOT = "http://" + SERVER_IP + ":" + SERVER_PORT + "/";

    /**
     * 服务端链接ip
     */
    public static String SERVER_CTX_PATH_IP = "http://" + SERVER_IP + ":" + SERVER_PORT + "/";

    /**
     * 服务端链接地址
     */
    public static String SERVER_CTX_PATH = "http://" + SERVER_IP + ":" + SERVER_PORT + "/" + SERVER_CTX_NAME + "/";

    /**
     * SpringMVC获取数据地址
     */
    public static String BASE_URL_MVC_GETDATA = "http://" + SERVER_IP + ":" + SERVER_PORT + "/" + SERVER_CTX_NAME + "/" + "MAPI/";

    /**
     * 调用服务器接口的基本基础地址
     */
    public static String BASE_URL = SERVER_CTX_PATH + "accept.do?";

    /**
     * 上传文件至服务器的请求链接
     */
    public static String UPLOAD_URL = SERVER_CTX_PATH + "uploadAction.do";

    /**
     * 独立的文件服务器IP地址
     */
    public static String FILE_SERVER_IP = "";

    /**
     * 独立的文件服务器端口号
     */
    public static String FILE_SERVER_PORT = "";

    /**
     * 独立的文件服务器系统名称
     */
    public static String FILE_SERVER_CTX_NAME = "";

    /**
     * 独立的文件服务器系统地址
     */
    public static String FILE_SERVER_CTX_PATH = "http://" + FILE_SERVER_IP + ":" + FILE_SERVER_PORT + "/" + FILE_SERVER_CTX_NAME + "/";

    /**
     * 独立的文件服务器系统上传文件接口
     */
    public static String FILE_UPLOAD_URL = FILE_SERVER_CTX_PATH + "imAction!uploadPic.action";

    /**
     * 文件服务器查看图片地址
     */
    public static String FILE_IMG_LOAD_URL = FILE_SERVER_CTX_PATH + "upload/images/";

    /**
     * 文件服务器查看语音地址
     */
    public static String FILE_WAV_LOAD_URL = FILE_SERVER_CTX_PATH + "upload/waves/";

    /**
     * 文件服务器查看视频地址
     */
    public static String FILE_VIDEO_LOAD_URL = FILE_SERVER_CTX_PATH + "upload/videos/";

    /**
     * 位置地址
     */
    public static String FILE_POSITION_LOAD_URL = FILE_SERVER_CTX_PATH + "upload/positions/";


    /**
     * 本地图片
     */
    public static final int LOAD_PIC_FROM_LOCAL = 100;

    /**
     * 拍照
     */
    public static final int REQUEST_CODE_CAMERA = 101;

    /**
     * 上传图片 任务
     */
    public static final String ALARM_IMG = "4";

    /**
     * SpringMVC上传数据地址（通用处理器）
     */
    public static String BASE_URL_MVC_UPLOADDATA = "http://" + SERVER_IP + ":" + SERVER_PORT + "/" + SERVER_CTX_NAME + "/" + "CAPI/";

    /**
     * 摄像
     */
    public static final int REQUEST_CODE_VIDEO = 102;

    /**
     * 本地影像
     */
    public static final int LOAD_VIDEO_FROM_LOCAL = 103;

    /**
     * 上传图片 任务
     */
    public static final String TASK_IMG = "5";

    /**
     * 上传视频 任务
     */
    public static final String TASK_VEDIO = "7";

    /**
     * 上传语音 任务
     */
    public static final String TASK_VOICE = "6";

    /**
     * 任务完成广播
     */
    public static final String BROADCAST_INSPECT_SUCCESS = "broadcast_inspect_success";

    /**
     * 重新配置系统相关参数
     */
    public static void reGenConstants()
    {
        SERVER_IP = Util.readFromSHA(MyApplication.instance, "ip", "");
        SERVER_PORT = Util.readFromSHA(MyApplication.instance, "port", "");
        SERVER_SOCKET_PORT = Util.readFromSHA(MyApplication.instance, "serverSocketPort", "");
        // 离线文件配置
        FILE_SERVER_IP = Util.readFromSHA(MyApplication.instance, "fileServerIp", "");
        FILE_SERVER_PORT = Util.readFromSHA(MyApplication.instance, "fileServerPort", "");
        
//        InputStream is = Constants.class.getResourceAsStream("config.properties");
        try
        {
            InputStream is = MyApplication.instance.getAssets().open("config");;
            Properties properties = new Properties();
            properties.load(is);
            if ("".equals(SERVER_IP))
            {
                SERVER_IP = (String) properties.get("ip");
                SERVER_PORT = (String) properties.get("port");
                SERVER_SOCKET_PORT = (String) properties.get("server_socket_port");
            }
            SERVER_CTX_NAME = (String) properties.get("server_app_ctx");
            if ("".equals(FILE_SERVER_IP))
            {
                FILE_SERVER_IP = (String) properties.get("file_server_ip");
                FILE_SERVER_PORT = (String) properties.get("file_server_port");
            }
            FILE_SERVER_CTX_NAME = (String) properties.get("server_alarm_ctx");
            FILE_SERVER_CTX_NAME = (String) properties.get("file_server_ctx");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        reBuildConstants();
    }

    /**
     * 重新生成相关地址链接
     */
    public static void reBuildConstants()
    {
        SERVER_CTX_ROOT = "http://" + SERVER_IP + ":" + SERVER_PORT+"/";
        SERVER_CTX_PATH = "http://" + SERVER_IP + ":" + SERVER_PORT + "/" + SERVER_CTX_NAME + "/";
        SERVER_CTX_PATH_IP = "http://" + SERVER_IP + ":" + SERVER_PORT + "/";
        BASE_URL_MVC_GETDATA = "http://" + SERVER_IP + ":" + SERVER_PORT + "/" + SERVER_CTX_NAME + "/" + "MAPI/";
        BASE_URL_MVC_UPLOADDATA = "http://" + SERVER_IP + ":" + SERVER_PORT + "/" + SERVER_CTX_NAME + "/" + "CAPI/";
        UPLOAD_URL = SERVER_CTX_PATH + "uploadAction.do";
        BASE_URL = SERVER_CTX_PATH + "accept.do?";
        FILE_SERVER_CTX_PATH = "http://" + FILE_SERVER_IP + ":" + FILE_SERVER_PORT + "/" + FILE_SERVER_CTX_NAME + "/";
        FILE_UPLOAD_URL = FILE_SERVER_CTX_PATH + "imAction!uploadPic.action";
        FILE_IMG_LOAD_URL = FILE_SERVER_CTX_PATH + "upload/images/";
        FILE_WAV_LOAD_URL = FILE_SERVER_CTX_PATH + "upload/waves/";
        FILE_VIDEO_LOAD_URL = FILE_SERVER_CTX_PATH + "upload/videos/";
    }

}
