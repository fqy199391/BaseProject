package base.wujiang.com.baseproject.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Map;

import base.wujiang.com.baseproject.R;
import base.wujiang.com.baseproject.base.BaseActivity;
import base.wujiang.com.baseproject.util.Util;

@ContentView(R.layout.f_login)
public class LoginActivity extends BaseActivity {


    /**
     * 按钮
     */
    @ViewInject(R.id.btLogin)
    private TextView btLogin;

    /**
     *
     */
    @ViewInject(R.id.edtUcode)
    private EditText edtUcode;

    /**
     *
     */
    @ViewInject(R.id.edtPassword)
    private EditText edtPassword;

    /**
     *
     */
    @ViewInject(R.id.cb_auto_sign_in)
    private CheckBox cbAutoSignIn;

    /**
     *
     */
    @ViewInject(R.id.cb_remeberpassword)
    private CheckBox cbRemeberPassword;


    /**
     * 上下文
     */
    private Context context;
    /**
     * 当前View
     */
    private View fragment;

    private UMShareAPI mShareAPI;
    private SHARE_MEDIA platform;                      //第三方平台

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        x.view().inject(this);
        initView();
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(LoginActivity.this, mPermissionList, 123);
        }
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);      //每次都拉取授权信息
        mShareAPI = UMShareAPI.get(LoginActivity.this);//获得友盟社会化分析授权
        mShareAPI.setShareConfig(config);
    }


    @Event(value = {R.id.qq_icon_txt, R.id.wx_icon_txt})
    private void onClickListener(View v) {
        switch (v.getId()) {
//            case R.id.qq_icon_txt:
//                loginWithQQ();
//                break;
            case R.id.wx_icon_txt:
                loginWithWeiXin();
                break;
        }
    }

    /**
     * 微信第三方登录
     */
    public void loginWithWeiXin() {
        platform = SHARE_MEDIA.WEIXIN;
        if (!mShareAPI.isInstall(LoginActivity.this, platform)) {
            Toast.makeText(LoginActivity.this, "请先安装微信!", Toast.LENGTH_LONG);
            return;
        }
        //获取用户信息
        mShareAPI.getPlatformInfo(LoginActivity.this, platform, umAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {//友盟授权回调监听器
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Util.showToast(LoginActivity.this, "授权开始");
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            thirdLoginWithMap(map);           //授权成功,获取用户信息
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Util.showToast(LoginActivity.this, "授权失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Util.showToast(LoginActivity.this, "取消授权");
        }
    };

    /**
     * 第三方登录
     */
    public void thirdLoginWithMap(Map<String, String> userMap) {
        final String nickName = userMap.get("screen_name");                           //昵称
        String gender = getGenderByCn(userMap.get("gender"));                   //性别
        final String headPic = userMap.get("profile_image_url");                      //头像
        String openid = userMap.get("openid");                                  //头像
        final String from = (platform == SHARE_MEDIA.WEIXIN ? "wx" : "qq");           //平台
        String unionid = userMap.get("unionid");
//        String pushId = JPushInterface.getRegistrationID(this);
//        showLoadingSmallToast();
//        SPUserRequest.loginWithThirdPart(openid, unionid, from, nickName, headPic, gender, pushId, new SPSuccessListener() {
//            @Override
//            public void onRespone(String msg, Object response) {
//                hideLoadingSmallToast();
//                if (response != null) {
//                    SPUser user = (SPUser) response;
//                    SPMobileApplication.getInstance().setLoginUser(user);
//                    SPLoginActivity.this.sendBroadcast(new Intent(SPMobileConstants.ACTION_LOGIN_CHNAGE));
//                    showSuccessToast("登录成功");
//                    loginSuccess();
//                }
//            }
//        }, new SPFailuredListener(SPLoginActivity.this) {
//            @Override
//            public void onRespone(String msg, int errorCode) {
//                hideLoadingSmallToast();
//                if (errorCode == 100) {
//                    Intent intent = new Intent(SPLoginActivity.this, SPBindAccountActivity.class);
//                    intent.putExtra("activity", fromActivity);
//                    intent.putExtra("from", from);
//                    intent.putExtra("headPic", headPic);
//                    intent.putExtra("nickName", nickName);
//                    startActivity(intent);
//                } else {
//                    showFailedToast(msg);
//                }
//            }
//        });
    }

    public String getGenderByCn(String gender) {
        switch (gender) {
            case "男":
                return "1";
            case "女":
                return "2";
            default:
                return gender;
        }
    }


    /**
     * 初始化界面
     */
    private void initView() {
        String savePassword = Util.readFromSHA(LoginActivity.this, "savePassword", "0");
        String userName = Util.readFromSHA(LoginActivity.this, "userName", "tom");
        String password = Util.readFromSHA(LoginActivity.this, "password", "123");
        String autoSignIn = Util.readFromSHA(LoginActivity.this, "autoSignIn", "0");
        if ("1".equals(savePassword)) {
            edtUcode.setText(userName);
            edtPassword.setText(password);
            cbRemeberPassword.setChecked(true);
        }
        if ("1".equals(autoSignIn)) {
            cbAutoSignIn.setChecked(true);
            doLogin();
        } else {
//            ((HomeActivity) LoginActivity.this).removeSplashScreen();
        }
    }

    /**
     * 修改服务端地址
     *
     * @param v
     */
    @Event(R.id.btChangeIp)
    public void onBtChangeIpClick(View v) {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, IpPortActivity.class);
        LoginActivity.this.startActivity(intent);
    }

    /**
     * 登录点击事件
     *
     * @param view
     */
    @Event(R.id.btLogin)
    private void onBtLoginClick(View view) {
        doLogin();
    }

    /**
     * 登录
     */
    private void doLogin() {
    }
}
