package base.wujiang.com.baseproject.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 概要说明 : 字符串工具类.  <br>
 */
public class StringUtil
{
    /**
     * String 类型返回值说明
     * @param s
     * @param len
     * @return
     */
    public static String genString(String s, int len)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++)
        {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 字符串转int
     * @param str
     * @return
     */
    public static int parseInt(String str)
    {
        try
        {
            return Integer.parseInt(str);
        }
        catch (Exception e)
        {
        }
        return 0;
    }

    /**
     * 字符串转长整型
     * @param str
     * @return
     */
    public static long parseLong(String str)
    {
        try
        {
            return Long.parseLong(str);
        }
        catch (Exception e)
        {
        }
        return 0;
    }

    /**
     * 数组转string
     * @param ary
     * @return
     */
    public static String join(String[] ary)
    {
        if (ary.length == 0)
        {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String si : ary)
        {
            sb.append(si);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 是否是图片文件判断
     * @param fileExt
     * @return
     */
    public static boolean isImageExt(String fileExt)
    {
        String types = "jpg,png,bmp,gif,jpeg,tga,raw";
        if (types.indexOf(fileExt) > -1)
        {
            return true;
        }
        return false;
    }

    /**
     * 数字转换
     * @param s
     * @return
     */
    public static int intFromString(String s)
    {
        int n = 0;
        try
        {
            n = Integer.parseInt(s);
        }
        catch (Exception e)
        {
        }
        return n;
    }

    /**
     * 对象判空
     * @param o
     * @return
     */
    public static boolean empty(Object o)
    {
        return o == null || "".equals(o.toString().trim()) || "null".equalsIgnoreCase(o.toString().trim()) || "undefined".equalsIgnoreCase(o.toString().trim());
    }

    /**
     * 通过标签提取字符串
     * @param xml
     * @param tag
     * @return
     */
    public static String getStringByTag(String xml, String tag)
    {
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";
        if (!xml.contains(startTag) || !xml.contains(endTag))
        {
            return "";
        }
        return xml.split(startTag)[1].split(endTag)[0];
    }

    /**
     * url地址编码
     * @param str
     * @return
     */
    public static String urlEncode(String str)
    {
        String rs = "";
        try
        {
            rs = URLEncoder.encode(str, "GBK");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 字符串判空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str)
    {
        if (str == null)
        {
            return true;
        }
        if (str.trim().equals(""))
        {
            return true;
        }
        return false;
    }

}
