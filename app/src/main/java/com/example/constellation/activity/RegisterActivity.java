package com.example.constellation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.constellation.R;
import com.example.constellation.db.UserDbHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setListener();
    }

    private void initView() {
        et_password =findViewById(R.id.et_password);
        et_username =findViewById(R.id.et_username);
    }

    private void setListener() {
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_register1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =et_username.getText().toString();
                String password =et_password.getText().toString();

                //注册业务逻辑
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"请输入用户名或密码",Toast.LENGTH_SHORT).show();
                }else{
                    // 检查用户名是否已存在
                    boolean isUsernameExists = UserDbHelper.getInstance(RegisterActivity.this).isUsernameExists(username);
                    if (isUsernameExists) {
                        Toast.makeText(RegisterActivity.this, "用户名已存在，请选择其他用户名", Toast.LENGTH_SHORT).show();
                    } else {
                        int row = UserDbHelper.getInstance(RegisterActivity.this).register(username, password);
                        if (row > 0) {
                            Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


    }
}