package com.example.constellation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.constellation.R;
import com.example.constellation.enbity.Wish;
import com.example.constellation.db.WishDbOpenHelper;
import com.example.constellation.utils.ToastUtil;

public class AddActivity extends AppCompatActivity {
    private EditText wishcontent;
    private Toolbar toolbar;
    private WishDbOpenHelper mWishDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        wishcontent = findViewById(R.id.wishcontent);
        toolbar = findViewById(R.id.toolbar);
        mWishDbOpenHelper = new WishDbOpenHelper(this);
        setListener();


    }

    private void setListener() {
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void add(View view) {
        String content = wishcontent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "标题不能为空！", Toast.LENGTH_SHORT).show();
        return;
        }
        Wish wish =new Wish();
        wish.setWishcontent(content);
        long row = mWishDbOpenHelper.insertData(wish);
        if (row != -1) {
            ToastUtil.toastShort(this,"添加成功！");
            this.finish();
        }else {
            ToastUtil.toastShort(this,"添加失败！");
        }



    }
}