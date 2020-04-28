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

import java.util.Date;

public class CommentsRvAdapter extends RecyclerView.Adapter<CommentsRvAdapter.CommentsRowHolder> {
    private CommentsLab commentsLab = new CommentsLab();
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
        Comments comment = commentsLab.getComments(position);
        Log.d("DD1", "onBindViewHolder: " + comment);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return commentsLab.getSize();
    }

    class CommentsRowHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private TextView author;
        private Date dateTime;
        private ImageView cover;
        private int star;

        CommentsRowHolder(@NonNull View row) {
            super(row);
            this.content = row.findViewById(R.id.comment_content);
            this.author = row.findViewById(R.id.comment_author);
            this.cover = row.findViewById(R.id.comment_cover);
            Log.d("DD1", "R.id.comment_content: " + R.id.comment_content);
        }

        void bind(@NotNull Comments c) {
            Log.d("DD1", "bind: " + c.getAuthor());
            this.content.setText(c.getContent());
            Log.d("DD1", "bind: " + c.getAuthor());
            this.author.setText(c.getAuthor());

            Glide.with(context)
                    .load("https://pan.fuyu.site/pic/pic001.png")
                    .placeholder(R.drawable.cover)
//                    .apply(requestOptions)
                    .into(this.cover);
        }
    }
}
