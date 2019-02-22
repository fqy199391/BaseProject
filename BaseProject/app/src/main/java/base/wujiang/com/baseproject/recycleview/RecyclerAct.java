package base.wujiang.com.baseproject.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.AccordionTransformer;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base.wujiang.com.baseproject.MyApplication;
import base.wujiang.com.baseproject.R;
import base.wujiang.com.baseproject.adapter.RecyclerAdapter;
import base.wujiang.com.baseproject.util.GlideImageLoader;
import base.wujiang.com.baseproject.util.SuperSwipeRefreshLayout;

@ContentView(R.layout.activity_recycle)
public class RecyclerAct extends Activity  implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, OnBannerListener {

    /**
     * 增加
     */
    @ViewInject(R.id.btnadd)
    private Button btnadd;
    /**
     * 删除
     */
    @ViewInject(R.id.btndelete)
    private Button btndelete;
    /**
     * list
     */
    @ViewInject(R.id.btnlist)
    private Button btnlist;
    /**
     * grid
     */
    @ViewInject(R.id.btngrid)
    private Button btngrid;
    /**
     * btnflow
     */
    @ViewInject(R.id.btnflow)
    private Button btnflow;

    @ViewInject(R.id.recyclerview)
    private RecyclerView recyclerview;

    /**
     * 总布局
     */
    @ViewInject(R.id.llrec)
    private LinearLayout llrec;

    private List<String> datas = new ArrayList<String>();

    /**
     * 适配器
     */
    private RecyclerAdapter adapter;

    static final int REFRESH_COMPLETE = 0X1112;

    /**
     * 下拉刷新基类
     */
    @ViewInject(R.id.swipe)
    SuperSwipeRefreshLayout mSwipeLayout;

    /**
     * 广告栏
     */
    @ViewInject(R.id.banner)
    Banner banner;


    /**
     * 消息处理
     */
    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    String[] urls = getResources().getStringArray(R.array.url4);
                    List list = Arrays.asList(urls);
                    List arrayList = new ArrayList(list);
                    banner.update(arrayList);
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        x.view().inject(this);
        setData();
        initRecyler();

        mSwipeLayout.setOnRefreshListener(this);
        banner.setImages(MyApplication.images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .setDelayTime(2500)
                .start();
        banner.setBannerAnimation(AccordionTransformer.class);
    }

    private void initRecyler() {
        adapter = new RecyclerAdapter(RecyclerAct.this,datas);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(RecyclerAct.this,LinearLayoutManager.VERTICAL,false));
        recyclerview.scrollToPosition(3);
        //分割线
        recyclerview.addItemDecoration(new DividerItemDecoration(RecyclerAct.this,DividerItemDecoration.VERTICAL));
    }

    @Event(value = {R.id.btnlist,R.id.btngrid,R.id.btnflow})
    private void onCLick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnlist:
                recyclerview.setLayoutManager(new LinearLayoutManager(RecyclerAct.this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.btngrid:
                recyclerview.setLayoutManager(new GridLayoutManager(RecyclerAct.this,3,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.btnflow:
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        };
    }

    /**
     * 添加数据
     */
    private void setData() {
        for (int i=0;i<10;i++)
        {
            if(i==1)
            {
                datas.add("countcountcountcountcountcountcountcountcountcountcountcountcountcountcountcount"+i);
            }
            else
                {
                    datas.add("count"+i);
                }
        }
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void OnBannerClick(int position) {

    }
}
