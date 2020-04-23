package site.fuyu.stu.ddzb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PictureActivity extends AppCompatActivity {
    private ImageView avatar;
    private Button xiazai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        xiazai = findViewById(R.id.avatar);
        avatar = findViewById(R.id.xiazai);
        xiazai.setOnClickListener(v -> {
            Glide.with(this)
                    .load("https://pan.fuyu.site/pic/pic001.png")
                    .into(avatar);
        });
    }
}
