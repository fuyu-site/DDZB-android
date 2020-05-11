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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CommentsRvAdapter extends RecyclerView.Adapter<CommentsRvAdapter.CommentsRowHolder> {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("GMT+8:00"));
    //    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.forLanguageTag("GMT+8:00"));
    private CommentsLab commentsLab = CommentsLab.getInstance();
    private Context context;

    CommentsRvAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CommentsRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);
        return new CommentsRowHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsRowHolder holder, int position) {
        Log.d("DD", "onBindViewHolder Position=" + position);
        Comment comment = commentsLab.getComments(position);
        Log.d("DD1", "onBindViewHolder: " + comment);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return commentsLab.getSize();
    }

    class CommentsRowHolder extends RecyclerView.ViewHolder {
        private TextView content;  //评论内容
        private TextView author;  //评论作者
        private TextView dateTime;  //评论时间
        private ImageView cover;  //评论作者头像
        private int star;  //点赞数量

        CommentsRowHolder(@NonNull View row) {
            super(row);
            this.content = row.findViewById(R.id.comment_content);
            this.author = row.findViewById(R.id.comment_author);
            this.cover = row.findViewById(R.id.comment_cover);
            this.dateTime = row.findViewById(R.id.comment_time);
        }

        void bind(@NotNull Comment c) {
            Log.d("DD1", "bind: " + c.getAuthor());
            this.content.setText(c.getContent());
            Log.d("DD1", "bind: " + c.getAuthor());
            this.author.setText(c.getAuthor());
            this.dateTime.setText(dtf.format(c.getDateTime()));

            Glide.with(context)
                    .load("https://pan.fuyu.site/pic/pic001.png")
                    .placeholder(R.drawable.cover)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(this.cover);
        }
    }
}
