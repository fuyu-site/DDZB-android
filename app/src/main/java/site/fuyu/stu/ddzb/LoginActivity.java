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
    private Button login;
    private Button register;
    private Button findPassword;
    private UserLab lab = UserLab.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);

        login.setOnClickListener(v -> {
            String u = username.getEditText().getText().toString();
            String p = password.getEditText().getText().toString();
            lab.login(u, p, handler);
        });
    }

    private void loginSuccess() {
        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void loginFail() {
        Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
    }
}
