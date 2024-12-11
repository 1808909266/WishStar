package com.example.constellation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.constellation.R;
import com.example.constellation.enbity.Wish;
import com.example.constellation.db.WishDbOpenHelper;
import com.example.constellation.utils.ToastUtil;

public class EditActivity extends AppCompatActivity {
   private Wish wish;
    private EditText wishcontent;
    private Toolbar toolbar;
    private WishDbOpenHelper mWishDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initView();
        initData();
        setListener();
    }
    private void initView() {
        wishcontent = findViewById(R.id.wishcontent);
        toolbar = findViewById(R.id.toolbar);
    }
    private void setListener() {
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initData() {
        Intent intent = getIntent();
        wish = (Wish) intent.getSerializableExtra("wish");
        if (wish != null) {
            wishcontent.setText(wish.getWishcontent());
        }
        mWishDbOpenHelper = new WishDbOpenHelper(this);
    }
    public void save(View view) {
        String content = wishcontent.getText().toString();
        wish.setWishcontent(content);
        long rowId = mWishDbOpenHelper.updateData(wish);
        if (rowId != -1) {
            ToastUtil.toastShort(this, "修改成功！");
            this.finish();
        }else{
            ToastUtil.toastShort(this, "修改失败！");
        }
    }
}