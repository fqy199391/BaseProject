package base.wujiang.com.baseproject.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import base.wujiang.com.baseproject.MyApplication;
import base.wujiang.com.baseproject.R;
import base.wujiang.com.baseproject.base.BaseActivity;
import base.wujiang.com.baseproject.fragment.FragmentUtil;
import base.wujiang.com.baseproject.fragment.LoginFragment;
import base.wujiang.com.baseproject.fragment.MainHomeFragment;
import base.wujiang.com.baseproject.fragment.MainTaskFragment;
import base.wujiang.com.baseproject.fragment.MeFragment;
import base.wujiang.com.baseproject.recycleview.RecyclerAct;
import base.wujiang.com.baseproject.util.Util;
import base.wujiang.com.baseproject.view.SplashScreen;

@ContentView(R.layout.act_home)
public class HomeActivity extends BaseActivity {
    /**
     * 底部布局
     */
    @ViewInject(R.id.panelBottom)
    private LinearLayout panelBottom;


    /**
     * 记录2次退出的时间间隔
     */
    private long exitTime = 0;

    /**
     * 过渡页
     */
    private SplashScreen splashScreen;

    /**
     *
     */
    private Handler handler = new Handler();

    /**
     * 登录页面
     */
    public static LoginFragment loginFrag;

    /**
     *
     */
    private FragmentUtil fragmentUtil;

    /**
     * 主页
     */
    private MainHomeFragment mainHomeFragment;

    /**
     * 任务页
     */
    private MainTaskFragment mainTaskFragment;

    /**
     * 我的
     */
    private MeFragment meFragment;

    /**
     * 登陆页
     */
    // private LoginFragment loginFragment;

    /**
     * 菜单
     */
    private int[] menuImages = {R.drawable.ic_home,
            R.drawable.ic_home_task,
            R.drawable.ic_home_me};

    /**
     * 菜单
     */
    private int[] menuSelImages = {R.drawable.ic_home_sel,
            R.drawable.ic_home_task_sel,
            R.drawable.ic_home_me_sel};

    /**
     *
     */
    private Intent onHomeIntent;

    /**
     * 主页
     */
    @ViewInject(R.id.btHome)
    private LinearLayout btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        x.view().inject(this);
        splashScreen = new SplashScreen(this);
        splashScreen.show(R.drawable.splash, SplashScreen.SLIDE_LEFT);
        getTestDeviceInfo(this);
        checkUpdate();
        showLogin();
        removeSplashScreen();
    }

    /**
     * 获取设备信息
     */
    public static String[] getTestDeviceInfo(Context context) {
        String[] deviceInfo = new String[2];
        try {
            if (context != null) {
                deviceInfo[0] = DeviceConfig.getDeviceIdForGeneral(context);
                deviceInfo[1] = DeviceConfig.getMac(context);
            }
        } catch (Exception e) {
        }
        return deviceInfo;
    }

    public void removeSplashScreen() {
        handler.postDelayed(new Thread(new Runnable() {
            @Override
            public void run() {
                splashScreen.removeSplashScreen();
            }
        }), 1000);
    }

    /**
     * 展示登陆页
     */
    public void showLogin() {
        panelBottom.setVisibility(View.GONE);
        loginFrag = new LoginFragment();
        FragmentUtil.addFragment(HomeActivity.this, R.id.frame_main, loginFrag);
    }

    /**
     * 检查更新版本
     */
    private void checkUpdate() {
        //        UpdateTask task = new UpdateTask(this);
        //        task.execute();

//        String url = Constants.SERVER_CTX_PATH + "version.txt";
//        OkGo.<LzyResponse<Version>>get(url)//
//                .tag(this)//
//                .headers("header1", "headerValue1")
//                .params("param1", "paramValue1")//
//                .execute(new DialogCallback<LzyResponse<Version>>(this) {
//                    @Override
//                    public void onSuccess(Response<LzyResponse<Version>> response) {
//                        Log.e("tag", response.body().toString());
//                    }
//
//                    @Override
//                    public void onError(Response<LzyResponse<Version>> response) {
//                        Toast.makeText(HomeActivity.this, response.message(), Toast.LENGTH_SHORT).show();
//                    }
//                });
    }


    public void showMainUI() {
        removeSplashScreen();
        Util.writeToSHA(MyApplication.instance, "lastWarnTime", 0);
        FragmentUtil.removeFragment(this, loginFrag);
        mainHomeFragment = new MainHomeFragment();
        mainTaskFragment = new MainTaskFragment();
        meFragment = new MeFragment();
        fragmentUtil = new FragmentUtil(R.id.frame_main, new Fragment[]{mainHomeFragment, mainTaskFragment, meFragment});
        fragmentUtil.showFragment(this, 0);
        setTabStates(btHome);
        panelBottom.setVisibility(View.VISIBLE);
        //        PollingUtils.startPollingService(this, 30, PollingService.class, PollingService.ACTION);
//        Intent intent = new Intent();
//        intent.setClass(this, PollingService.class);
//        startService(intent);
    }


    /**
     * 切换菜单样式
     */
    private void setTabStates(View view) {
        LinearLayout parentView = (LinearLayout) view.getParent();
        int counts = parentView.getChildCount();
        for (int i = 0; i < counts; i++) {
            View menuItem = parentView.getChildAt(i);
            setMenuItemState(menuItem, i, menuItem == view);
        }
    }

    /**
     * 菜单样式
     */
    private void setMenuItemState(View view, int pos, boolean selected) {
        FrameLayout frameLayout = (FrameLayout) ((LinearLayout) view).getChildAt(0);
        ImageView menuImg = (ImageView) frameLayout.getChildAt(0);
        TextView menuText = (TextView) ((LinearLayout) view).getChildAt(1);
        if (selected) {
            menuImg.setImageResource(menuSelImages[pos]);
            menuText.setTextColor(Color.parseColor("#ff0000"));
        } else {
            menuImg.setImageResource(menuImages[pos]);
            menuText.setTextColor(Color.parseColor("#999999"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    /**
     * 主页
     */
    @Event(value = {R.id.btHome, R.id.btTask, R.id.btMe})
    private void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.btHome:
                setTabStates(view);
                fragmentUtil.showFragment(this, 0);
                break;
            case R.id.btTask:
                setTabStates(view);
//                fragmentUtil.showFragment(this, 1);
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, RecyclerAct.class));
                break;
            case R.id.btMe:
                setTabStates(view);
//                fragmentUtil.showFragment(this, 2);
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                break;
        }
    }

    /**
     * 概要说明 : 退出程序. <br>
     */
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            MyApplication.instance.onTerminate();
            System.exit(0);
        }
    }
}
