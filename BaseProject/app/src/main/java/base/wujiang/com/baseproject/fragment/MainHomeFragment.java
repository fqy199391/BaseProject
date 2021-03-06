package base.wujiang.com.baseproject.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import base.wujiang.com.baseproject.MyApplication;
import base.wujiang.com.baseproject.R;
import base.wujiang.com.baseproject.base.ReObject;
import base.wujiang.com.baseproject.base.ReturnMap;
import base.wujiang.com.baseproject.greenDao.UserDao;
import base.wujiang.com.baseproject.task.GeneralQueryTask;
import base.wujiang.com.baseproject.util.JsonUtil;
import base.wujiang.com.baseproject.util.JsonUtilMVC;
import base.wujiang.com.baseproject.vo.Person;
import base.wujiang.com.baseproject.vo.User;


/**
 * 主fragment
 */
@ContentView(R.layout.f_main_home)
public class MainHomeFragment extends Fragment {


    @ViewInject(R.id.tvtitle)
    private TextView tvtitle;

    @ViewInject(R.id.sd)
    private SimpleDraweeView sdvFrescoJpeg;

    /**
     * 上下文
     */
    private Context context;
    /**
     * 当前View
     */
    private View fragment;

    /**
     * 请求的当前页
     */
    private int pageNumber = 1;

    /**
     * 每次查询的信息数量
     */
    private int pageSize = 10;

    /**
     * 用户Dao
     */
    private UserDao userDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context =getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //        return super.onCreateView(inflater, container, savedInstanceState);
        fragment = inflater.inflate(R.layout.f_main_home, container, false);
        getData();
        return x.view().inject(this,inflater,container);
    }

    private void getData() {
//        GeneralQueryTask task = new GeneralQueryTask(getActivity(), this, "hon", GeneralQueryTask.GeneralQueryType.honInfo, true);
//        String[] params = new String[] { "1", "10000", MyApplication.person.getUnitId(), "" };
//        task.loadData(params);
        GeneralQueryTask task = new GeneralQueryTask(getActivity(), this, "firemenService", GeneralQueryTask.GeneralQueryType.queryLDFireManInfos, true);
        String[] params = new String[] { pageNumber+"", pageSize+""};
        task.loadData(params);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initImage();
        userDao = MyApplication.instance.getDaoSession().getUserDao();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("用户"+i);
            userDao.insertOrReplace(user);
        }
        userDao.update(new User(Long.parseLong("1"),"xin"));
        List<User> users = queryAll();
        List<User> users2 = queryData("1");

        tvtitle.setText("主页");
    }

    public List queryAll(){
        List<User> students = userDao.loadAll();
        return students;
    }

    public List queryData(String s) {
        List<User> students = userDao.queryRaw(" where _id = ?",s);
        return students;
    }
    private void initImage() {
        // 加载质量配置
        ProgressiveJpegConfig jpegConfig = new ProgressiveJpegConfig() {
            @Override
            public int getNextScanNumberToDecode(int scanNumber) {
                return scanNumber + 2;
            }

            @Override
            public QualityInfo getQualityInfo(int scanNumber) {
                boolean isGoodEnough = (scanNumber >= 5);

                return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
            }
        };

        ImagePipelineConfig.newBuilder(getActivity()).setProgressiveJpegConfig(jpegConfig).build();

        // 获取图片URL
        Uri uri = Uri.parse("http://cdn.duitang.com/uploads/item/201303/12/20130312021353_45Qix.jpeg");

        // 获取图片请求
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(true).build();

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(true)

                .setOldController(sdvFrescoJpeg.getController())//使用oldController可以节省不必要的内存分配
                .build();
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        RoundingParams param = RoundingParams.asCircle();;


        GenericDraweeHierarchy hierarchy = builder.setRoundingParams(param).build();
        sdvFrescoJpeg.setHierarchy(hierarchy);
        // 1设置加载的控制
        sdvFrescoJpeg.setController(draweeController);
    }

    public void reloadData(ReturnMap ro)
    {
        JSONObject jobj = (JSONObject) ro.getData();
        JSONArray jarr = jobj.getJSONArray("persons");
        if (jarr != null)
        {
            List<Person> personList = JsonUtilMVC.getListFromJson(jarr, Person.class);
            Log.e("tag",personList.size()+"");
        }
    }
    public void reloadObjData(ReObject ro)
    {
        List<Person> personList = JsonUtil.getListFromJson(ro,Person.class);
        if (personList != null)
        {
        }
    }
    @Event( type = AdapterView.OnItemClickListener.class,value = {R.id.btHome})
    private void onItemClick(AdapterView<?> av, View view, int pos, long id)
    {

    }

}
