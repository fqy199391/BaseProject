package base.wujiang.com.baseproject.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import base.wujiang.com.baseproject.base.ReObject;
import base.wujiang.com.baseproject.base.ReturnMap;


/**
 * json转换工具类
 */
public class JsonUtil
{
    public static <T> List<T> getListFromJson(ReObject ro, Class<T> clazz)
    {
        List<T> list = new ArrayList<T>();
        JSONArray ja = (JSONArray) ro.getContent();
        if (null != ja && ja.size() > 0)
        {
            for (int i = 0; i < ja.size(); i++)
            {
                JSONObject jo = (JSONObject) ja.get(i);
                T bean = JSON.toJavaObject(jo, clazz);
                list.add(bean);
            }
        }
        return list;
    }

    public static <T> List<T> getListFromJson(JSONArray ja, Class<T> clazz)
    {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < ja.size(); i++)
        {
            JSONObject jo = (JSONObject) ja.get(i);
            T bean = JSON.toJavaObject(jo, clazz);
            list.add(bean);
        }
        return list;
    }

    public static <T> List<T> getObjectFromJson(ReObject ro, Class<T> clazz)
    {
        JSONObject jo = (JSONObject) ro.getContent();
        T bean = JSON.toJavaObject(jo, clazz);
        List<T> list = new ArrayList<T>();
        list.add(bean);
        return list;
    }

    public static <T> void getRoFromJson(ReObject ro, Class<T> clazz)
    {
        ro.setContent(getObjectFromJson(ro, clazz));
    }

    public static <T> T getSingleObjectFromJson(ReObject ro, Class<T> clazz)
    {
        JSONObject jo = (JSONObject) ro.getContent();
        T bean = JSON.toJavaObject(jo, clazz);
        return bean;
    }

    /**
     * json转实体
     * @param ro
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getSingleObjectFromJson(ReturnMap ro, Class<T> clazz)
    {
        JSONObject jo = (JSONObject) ro.getData();
        T bean = JSON.toJavaObject(jo, clazz);
        return bean;
    }

    public static <T> List<ReObject> getListRoFromJson(ReObject ro, Class<T> clazz)
    {
        ro.setContent(getListFromJson(ro, clazz));
        List<ReObject> list = new ArrayList<ReObject>();
        list.add(ro);
        return list;
    }

    /**
     * 数据转换
     * @param ro
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getListFromJson(ReturnMap ro, Class<T> clazz)
    {
        List<T> list = new ArrayList<T>();
        JSONArray ja = (JSONArray) ro.getData();
        if (null != ja && ja.size() > 0)
        {
            for (int i = 0; i < ja.size(); i++)
            {
                JSONObject jo = (JSONObject) ja.get(i);
                T bean = JSON.toJavaObject(jo, clazz);
                list.add(bean);
            }
        }
        return list;
    }

}
