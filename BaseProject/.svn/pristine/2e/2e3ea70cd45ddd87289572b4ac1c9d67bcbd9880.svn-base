package base.wujiang.com.baseproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import base.wujiang.com.baseproject.R;
import base.wujiang.com.baseproject.recycleview.RecyclerAct;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<String> datas = new ArrayList<>();

    public RecyclerAdapter(RecyclerAct context, List<String> datas) {
        this.context = context;
        this.datas.addAll(datas);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = View.inflate(context,R.layout.item_recyclerview,null);
        MyViewHolder holder = new MyViewHolder(itemView);
        x.view().inject(holder,itemView);
        return holder;
    }

    /**
     * 数据和view的绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String data = datas.get(position);
        holder.tvView.setText(data);
    }

    /**
     * 得到总数
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        @ViewInject(R.id.tv_r_view)
        private TextView tvView;

        @Event(R.id.tv_r_view)
        private void onTextClick(View view)
        {
            Toast.makeText(context,"图片  ===="+datas.get(getLayoutPosition()),Toast.LENGTH_SHORT).show();
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,datas.get(getLayoutPosition()),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
