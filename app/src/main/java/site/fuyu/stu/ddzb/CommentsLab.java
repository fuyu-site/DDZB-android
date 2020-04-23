package site.fuyu.stu.ddzb;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CommentsLab {
    private static CommentsLab INSTANCE = null;
    List<Comments> data = new ArrayList<>();

    CommentsLab() {
        getData();
    }

    //    public static CommentsLab getInstance(){
//        if (INSTANCE==null){
//            INSTANCE = new CommentsLab();
//        }
//        return INSTANCE;
//    }
    public void getData() {
        Comments comments = new Comments();
        comments.setAuthor("张三");
        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
        data.add(comments);
        comments = new Comments();
        comments.setAuthor("张三");
        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
        data.add(comments);
        comments = new Comments();
        comments.setAuthor("张三");
        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
        data.add(comments);
        comments = new Comments();
        comments.setAuthor("张三");
        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
        data.add(comments);
        comments = new Comments();
        comments.setAuthor("张三");
        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
        data.add(comments);
        comments = new Comments();
        comments.setAuthor("张三");
        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
        data.add(comments);
        comments = new Comments();
        comments.setAuthor("张三");
        comments.setContent("awslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawslawsl");
        data.add(comments);
        Log.d("DD1", "getData: " + data);
    }

    int getSize() {
        return data.size();
    }

    Comments getComments(int position) {
        return data.get(position);
    }
}
