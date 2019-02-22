package base.wujiang.com.baseproject.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import base.wujiang.com.baseproject.R;
import base.wujiang.com.baseproject.util.Constants;
import base.wujiang.com.baseproject.util.StringUtil;
import base.wujiang.com.baseproject.util.Util;

public class IpPortActivity extends FragmentActivity
{
    @ViewInject(R.id.txtIp)
    private EditText txtIp;
    
    @ViewInject(R.id.txtPort)
    private EditText txtPort;
    @ViewInject(R.id.fileIp)
    private EditText fileIp;
    
    @ViewInject(R.id.filePort)
    private EditText filePort;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipport_setting);
        x.view().inject(this);
        txtIp.setText(Constants.SERVER_IP);
        txtPort.setText(Constants.SERVER_PORT);
        fileIp.setText(Constants.FILE_SERVER_IP);
        filePort.setText(Constants.FILE_SERVER_PORT);
    }
    
    public void changeIp(View v)
    {
        String ip = txtIp.getText().toString();
        String port = txtPort.getText().toString();
        String fileip = fileIp.getText().toString();
        String fileport = filePort.getText().toString();
        if (StringUtil.empty(ip) || StringUtil.empty(port) || StringUtil.empty(fileip) || StringUtil.empty(fileport) )
        {
            Toast.makeText(this, "请将未填写的配置补全！", Toast.LENGTH_LONG).show();
            return;
        }
        if (StringUtil.intFromString(port) <= 0 ||StringUtil.intFromString(fileport) <= 0)
        {
            Toast.makeText(this, "请正确填写各类端口号！", Toast.LENGTH_LONG).show();
            return;
        }
        Constants.SERVER_IP = ip;
        Constants.SERVER_PORT = port;
        Constants.FILE_SERVER_IP = fileip;
        Constants.FILE_SERVER_PORT = fileport;
        Constants.reBuildConstants();
        Util.writeToSHA(this, new String[] {"ip", "port", "fileServerIp", "fileServerPort"}, new String[] {
            Constants.SERVER_IP, Constants.SERVER_PORT, Constants.FILE_SERVER_IP, Constants.FILE_SERVER_PORT});
        finish();
    }
    
    public void goBack(View v)
    {
        finish();
    }
    
}
