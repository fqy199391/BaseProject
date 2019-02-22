package base.wujiang.com.baseproject.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeString
{
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
        "e", "f"};

    /**
     * 将一个字节转换为十六进制字符串
     * @param b
     * @return
     */
    public static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param b
     * @return
     */
    public static String byteArrayToHexString(byte[] b)
    {
        StringBuffer resultsb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
        {
            resultsb.append(byteToHexString(b[i]));
        }
        return resultsb.toString();
    }

    /**
     * 将字符串MD5加密
     * @param originString
     * @return
     */
    public static String encodeByMD5(String originString)
    {
        try
        {
            if (null != originString && !"".equals(originString))
            {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(originString.getBytes());
                String resultString = byteArrayToHexString(results);
//                return resultString.toUpperCase();
                return resultString;
            }
            else
            {
                return originString;
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean authenticatePassword(String password, String inputString)
    {
        if (password.equals(encodeByMD5(inputString)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
