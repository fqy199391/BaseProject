package base.wujiang.com.baseproject.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Map;

import base.wujiang.com.baseproject.MyApplication;
import base.wujiang.com.baseproject.R;
import base.wujiang.com.baseproject.activity.HomeActivity;
import base.wujiang.com.baseproject.activity.IpPortActivity;
import base.wujiang.com.baseproject.base.ReturnMap;
import base.wujiang.com.baseproject.callback.StringDialogCallback;
import base.wujiang.com.baseproject.util.Constants;
import base.wujiang.com.baseproject.util.EncodeString;
import base.wujiang.com.baseproject.util.JsonUtilMVC;
import base.wujiang.com.baseproject.util.Util;
import base.wujiang.com.baseproject.view.LoadingProgressBar;
import base.wujiang.com.baseproject.vo.Person;


/**
 * 主fragment
 */
@ContentView(R.layout.f_login)
public class LoginFragment extends Fragment {



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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //        return super.onCreateView(inflater, container, savedInstanceState);
        fragment = inflater.inflate(R.layout.f_login, container, false);
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(),mPermissionList,123);
        }
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);      //每次都拉取授权信息
        mShareAPI = UMShareAPI.get(getActivity());//获得友盟社会化分析授权
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
        if (!mShareAPI.isInstall(getActivity(), platform)) {
            Toast.makeText(getActivity(),"请先安装微信!",Toast.LENGTH_LONG);
            return;
        }
        //获取用户信息
        mShareAPI.getPlatformInfo(getActivity(), platform, umAuthListener);
    }


    private UMAuthListener umAuthListener = new UMAuthListener() {//友盟授权回调监听器
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Util.showToast(getActivity(),"授权开始");
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            thirdLoginWithMap(map);           //授权成功,获取用户信息
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Util.showToast(getActivity(),"授权失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Util.showToast(getActivity(),"取消授权");
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
        String savePassword = Util.readFromSHA(getActivity(), "savePassword", "0");
        String userName = Util.readFromSHA(getActivity(), "userName", "tom");
        String password = Util.readFromSHA(getActivity(), "password", "123");
        String autoSignIn = Util.readFromSHA(getActivity(), "autoSignIn", "0");
        if ("1".equals(savePassword)) {
            edtUcode.setText(userName);
            edtPassword.setText(password);
            cbRemeberPassword.setChecked(true);
        }
        if ("1".equals(autoSignIn)) {
            cbAutoSignIn.setChecked(true);
            doLogin();
        } else {
            ((HomeActivity) getActivity()).removeSplashScreen();
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
        intent.setClass(getActivity(), IpPortActivity.class);
        getActivity().startActivity(intent);
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
        String userName = edtUcode.getText().toString();
        String password = edtPassword.getText().toString();
        if (Constants.IS_DEBUG) {
            return;
        }
        if (null == userName || "".equals(userName) || null == password || "".equals(password)) {
            Toast.makeText(this.getActivity(), "用户名密码不能为空！", Toast.LENGTH_LONG).show();
            return;
        }
        String autoSignIn = cbAutoSignIn.isChecked() ? "1" : "0";
        String savePassword = cbRemeberPassword.isChecked() ? "1" : "0";
        if ("1".equals(autoSignIn)) {
            savePassword = "1";
        }
        Util.writeToSHA(getActivity(), new String[]{"savePassword", "autoSignIn"}, new String[]{savePassword, autoSignIn});
        if (autoSignIn.equals("1") || savePassword.equals("1")) {
            Util.writeToSHA(getActivity(), new String[]{"userName", "password"}, new String[]{userName, password});
        }
        //        LoginTask task = new LoginTask(getActivity());
        //        String[] param = { userName, password };
        //        task.execute(param);
        String url = Constants.BASE_URL_MVC_GETDATA + "hon/loginHon.action";
        MyApplication.person = new Person();
//        ((HomeActivity) getActivity()).showMainUI();

//
//        OkGo.<String>head(url)//
//                .tag(this)//
//                .params("user", userName)//
//                .params("jmpwd", EncodeString.encodeByMD5(password))//
//                .execute(new StringDialogCallback(this) {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        handleResponse(response);
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        handleError(response);
//                    }
//                });
//
















        showLoadingPb(getActivity());
        OkGo.<String>post(url)//
                .tag(this)//
                //                .headers("header1", "headerValue1")
                .params("user", userName)//
                .params("jmpwd", EncodeString.encodeByMD5(password))//
                .execute(new StringDialogCallback(getActivity()) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        hideLoadingPb();
                        String string = response.body().toString();
                        Toast.makeText(context, "服务器", Toast.LENGTH_SHORT).show();
                        ReturnMap returnMap = JSONObject.parseObject(string, ReturnMap.class);
                        JSONObject jobj = (JSONObject) returnMap.getData();
                        JSONObject per = jobj.getJSONObject("person");
                        JSONObject key = jobj.getJSONObject("keyunit");
                        if (per != null)
                        {
                            Person person = JsonUtilMVC.getSingleObjectFromJson(per, Person.class);
                            MyApplication.person = person;// 记录登录用户
                        }
                        ((HomeActivity) getActivity()).showMainUI();
                    }

                    @Override
                    public void onError(Response<String> response) {

                        if(response.body() == null)
                        {
                            return;
                        }
                        String string = response.body().toString();
                        ReturnMap returnMap = JSONObject.parseObject(string, ReturnMap.class);
                        Person person = JsonUtilMVC.getSingleObjectFromJson(returnMap, Person.class);
                        if (returnMap != null) {
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "服务器异常", Toast.LENGTH_SHORT).show();
                        }
                        if (person != null) {
                            Toast.makeText(context, person.getName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "服务器异常111", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private LoadingProgressBar mLoadingPb=null;
    public void showLoadingPb(Context context) {

    }

    public void hideLoadingPb() {

    }

}
