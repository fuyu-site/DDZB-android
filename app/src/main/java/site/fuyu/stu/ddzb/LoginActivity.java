package site.fuyu.stu.ddzb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UserLab.USER_LOGIN_SUCCESS:
                    loginSuccess();
                    break;
                case UserLab.USER_LOGIN_FAIL:
                    loginFail();
                    break;
            }
        }
    };
    private TextInputLayout username;
    private TextInputLayout password;
    private UserLab lab = UserLab.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button login = findViewById(R.id.login_button);
        Button register = findViewById(R.id.register_button);
        Button findPassword = findViewById(R.id.find_password);

        login.setOnClickListener(v -> {
            if (Objects.requireNonNull(username.getEditText()).getText().toString().length() > 0) {
                if (Objects.requireNonNull(password.getEditText()).getText().toString().length() > 0) {
                    lab.login(Objects.requireNonNull(username.getEditText()).getText().toString(), Objects.requireNonNull(password.getEditText()).getText().toString(), handler);
                } else {
                    Toast.makeText(LoginActivity.this, "请输入密码！", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "请输入用户名！", Toast.LENGTH_LONG).show();
            }
        });
        register.setOnClickListener(v -> register());
        findPassword.setOnClickListener(v -> findPassword());
    }

    private void loginSuccess() {
        if (ClickUtil.isFastClick()) {
            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    } //跳转主页面

    private void register() {
        if (ClickUtil.isFastClick()) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    } //进入注册界面

    private void findPassword() {
        if (ClickUtil.isFastClick()) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    } //进入找回密码界面

    private void loginFail() {
        Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
    } //登录失败提示
}
