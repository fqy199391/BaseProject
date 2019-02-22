package base.wujiang.com.baseproject.base;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import base.wujiang.com.baseproject.util.Constants;


/**
 * 基础通用activity
 */
public class BaseActivity extends FragmentActivity
{
    public void goBack(View v)
    {
        finish();
    }


    public void loadUrl(WebView webview, String url)
    {
        webview.loadUrl(url);
    }

    public void loadUrl(WebView webview, String url, String paramString)
    {
        url = url + paramString;
        webview.loadUrl(url);
    }

    @JavascriptInterface
    public String loadIpPortFromLocal()
    {
        return Constants.SERVER_IP + ":" + Constants.SERVER_PORT;
    }

    public void openUrl(String url)
    {
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        startActivity(it);
    }

}
