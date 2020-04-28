package site.fuyu.stu.ddzb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

public class ChannelRvAdapter extends RecyclerView.Adapter<ChannelRvAdapter.ChannelRowHolder> {
    private ChannelLab lab= ChannelLab.getInstance();
    private ChannelClickListener listener;
    private Context context;

    ChannelRvAdapter(ChannelClickListener lis, Context context) {
       this.listener = lis;
       this.context = context;
    }

    /**
     * 当需要新的一行时，此方法负责创建这一行对应的对象，即ChannelRowHolder对象。
     *
     * @return holder
     */
    @NonNull
    @Override
    public ChannelRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_row, parent, false);
        return new ChannelRowHolder(rowView);
    }

    /**
     * 用于确定列表总共有几行（即多少个ChannelRowHold对象）
     *
     * @return 行数
     */
    @Override
    public int getItemCount() {
        return lab.getSize();
    }

    /**
     * 用于确定每一行的内容是什么，即填充行中各个视图的内容。
     *
     * @param holder dd
     * @param position  地址
     */
    @Override
    public void onBindViewHolder(@NonNull ChannelRowHolder holder, int position) {
        Log.d("DD", "onBindViewHolder Position=" + position);
        Channel c = lab.getChannel(position);
        holder.bind(c);
    }


    /**
     * 单行布局对应的Java控制类
     */
    class ChannelRowHolder extends RecyclerView.ViewHolder {
        private TextView title; //频道标题
        private TextView quality; //频道清晰度
        private ImageView cover1;
        private String coverUrl;

        ChannelRowHolder(@NonNull View row) {
            super(row);
            this.title = row.findViewById(R.id.channel_title);
            this.quality = row.findViewById(R.id.channel_quality);
            this.cover1 = row.findViewById(R.id.channel_cover);
            row.setOnClickListener((v)->{
                int position = getLayoutPosition();
                Log.d("DD1", "点击了"+position+"行");
                //TODO 调用实际的接口
                listener.onChannelClick(position);
            });
        }

        /**
         * 自定义方法，用于向内部的title提供数据
         *
         * @param c 提供数据
         */
        void bind(@NotNull Channel c) {
            this.title.setText(c.getTitle());
            this.quality.setText(c.getQuality());

            //获取上下文
            Glide.with(context)
                    .load(c.getCover())
                    .placeholder(R.drawable.cover)
//                    .apply(requestOptions)
                    .into(this.cover1);
        }
    }

    //定义新接口
    public interface ChannelClickListener{
        public void onChannelClick(int position);
    }

}
