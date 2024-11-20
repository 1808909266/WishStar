package com.example.constellation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.constellation.R;
import com.example.constellation.db.UserDbHelper;
import com.example.constellation.enbity.Userinfo;

public class ChangePSWActivity extends AppCompatActivity {
    private EditText et_new_password;
    private EditText et_confirm_password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pswactivity);

        et_new_password =findViewById(R.id.et_new_password);
        et_confirm_password =findViewById(R.id.et_confirm_password);


        findViewById(R.id.btn_update_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_pwd =et_new_password.getText().toString();
                String confirm_pwd =et_confirm_password.getText().toString();
                if(TextUtils.isEmpty(new_pwd)||TextUtils.isEmpty(confirm_pwd)){
                    Toast.makeText(ChangePSWActivity.this, "信息不能为空", Toast.LENGTH_SHORT).show();
                }else if(!new_pwd.equals(confirm_pwd)){
                    Toast.makeText(ChangePSWActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                }else {

                    Userinfo userinfo =Userinfo.getUserinfo();
                    if (null!=userinfo){
                        int row = UserDbHelper.getInstance(ChangePSWActivity.this).updatePwd(userinfo.getUsername(), new_pwd);
                        if(row>0){
                            Toast.makeText(ChangePSWActivity.this, "密码修改成功，请重新登录", Toast.LENGTH_SHORT).show();
                            setResult(1000);
                            finish();
                        }else{
                            Toast.makeText(ChangePSWActivity.this, "密码修改失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });

        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}