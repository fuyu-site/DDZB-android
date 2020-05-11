package site.fuyu.stu.ddzb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class PictureActivity extends AppCompatActivity {
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Button xiazai = findViewById(R.id.avatar);
        avatar = findViewById(R.id.xiazai);
        xiazai.setOnClickListener(v -> Glide.with(this)
                .load("https://pan.fuyu.site/pic/pic001.png")
                .into(avatar));
    }
}
