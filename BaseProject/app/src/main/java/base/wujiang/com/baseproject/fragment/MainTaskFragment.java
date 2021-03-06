package base.wujiang.com.baseproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import base.wujiang.com.baseproject.R;


/**
 * 主fragment
 */
@ContentView(R.layout.f_main_task)
public class MainTaskFragment extends Fragment {


    @ViewInject(R.id.tvtitle)
    private TextView tvtitle;

    /**
     * 上下文
     */
    private Context context;
    /**
     * 当前View
     */
    private View fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context =getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //        return super.onCreateView(inflater, container, savedInstanceState);
        fragment = inflater.inflate(R.layout.f_main_task, container, false);
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvtitle.setText("主任务页");
    }
}
