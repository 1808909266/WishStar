package com.example.constellation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.constellation.R;
import com.example.constellation.adapter.WishAdapter;
import com.example.constellation.enbity.Wish;
import com.example.constellation.db.WishDbOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WishActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mBtnAdd;
    private Toolbar toolbar;
    private List<Wish> mWish;
    private WishAdapter mWishAdapter;

    private WishDbOpenHelper mWishDbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish);
        toolbar = findViewById(R.id.toolbar);
        initView();
        initData();
        initEvent();
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
    @Override
    protected void onResume() {
        super.onResume();
        refreshDataFromDb();

    }

    private void refreshDataFromDb() {
        mWish = getDataFromDB();
        mWishAdapter.refreshData(mWish);
    }

    private void initEvent() {
        mWishAdapter =new WishAdapter(this,mWish);
        mRecyclerView.setAdapter(mWishAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void initData() {
        mWish = new ArrayList<>();
        mWishDbOpenHelper = new WishDbOpenHelper(this);

/**
 * for (int i=0;i <30;i++){
 *             Wish wish =new Wish();
 *             wish.setWishcontent("这是愿望"+i);
 *             mWish.add(wish);
 *         }
 */

   //     mWish = getDataFromDB();


    }

    private List<Wish> getDataFromDB() {
       return mWishDbOpenHelper.queryAllFromDb();

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rlv);
    }


    public void add(View view){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}