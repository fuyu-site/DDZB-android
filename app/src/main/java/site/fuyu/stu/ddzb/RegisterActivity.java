package site.fuyu.stu.ddzb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {
    private final static String TAG = "DD1";
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UserLab.USER_REGISTER_SUCCESS:
                    register();
                    break;
                case UserLab.USER_REGISTER_FAIL:
                    Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    long b;
    private TextInputLayout birthday;
    private UserLab lab = UserLab.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        birthday = findViewById(R.id.r_birthday);
        TextInputLayout username = findViewById(R.id.r_username);
        TextInputLayout password1 = findViewById(R.id.r_password);
        TextInputLayout password2 = findViewById(R.id.r_password2);
        RadioGroup gender = findViewById(R.id.r_gender);
        RadioButton s_gender = findViewById(gender.getCheckedRadioButtonId()); //获取id对应的内容
        TextInputLayout phone = findViewById(R.id.r_phone);
        Button register = findViewById(R.id.r_register);

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        MaterialDatePicker<Long> picker = builder
                .setTitleText(R.string.birthday_title) //设置标题
                .build();
        //日历点击“确定”后的处理
        picker.addOnPositiveButtonClickListener(v -> {
            Log.d(TAG, "日历的结果是：" + v);
            b = v;
            Log.d(TAG, "标题是：" + picker.getHeaderText());
            Log.d(TAG, "标题是：" + b);
            Objects.requireNonNull(birthday.getEditText()).setText(picker.getHeaderText());
        });

        birthday.setEndIconOnClickListener(v -> {
            //弹出日历选择框
            Log.d(TAG, "生日图标被点击了！");
            picker.show(getSupportFragmentManager(), picker.toString());
        });

        register.setOnClickListener(v -> {
            User u = new User();
            String c_gender = s_gender.getText().toString(); //获取性别
            switch (c_gender) {
                case "男":
                    c_gender = "M";
                    break;
                case "女":
                    c_gender = "F";
                    break;
                case "保密":
                    c_gender = "U";
            }
            if (Objects.requireNonNull(username.getEditText()).getText().toString().length() > 0) {
                u.setUsername(Objects.requireNonNull(username.getEditText()).getText().toString());
                if (Objects.requireNonNull(password1.getEditText()).getText().toString().length() > 0 && Objects.requireNonNull(password2.getEditText()).getText().toString().length() > 0) {
                    //判断2个密码是否相等
                    if (Objects.requireNonNull(password2.getEditText()).getText().toString().equals(Objects.requireNonNull(password1.getEditText()).getText().toString())) {
                        u.setPassword(Objects.requireNonNull(password2.getEditText()).getText().toString());
                        u.setGender(c_gender);
                        Log.d(TAG, "onCreate: b" + b);
                        //LocalDateTime就是好用
                        LocalDateTime date = LocalDateTime.ofEpochSecond(b / 1000, 0, ZoneOffset.ofHours(8));
                        LocalDate dd = date == null ? null : date.toLocalDate(); //非NULL转换为LocalDate
                        u.setBirthday(dd);
                        u.setPhone(Objects.requireNonNull(phone.getEditText()).getText().toString());
                        Log.d(TAG, "onCreate: User" + u);
                        lab.register(u, handler);
                    } else {
                        Toast.makeText(RegisterActivity.this, "密码不一致！", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入密码！", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "请输入用户名！", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void register() {
        if (ClickUtil.isFastClick()) {  //过滤多次点击
            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
            Toast.makeText(RegisterActivity.this, "欢迎来到DDZB！", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}