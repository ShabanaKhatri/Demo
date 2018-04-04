package com.demo;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.demo.adapter.UsersListAdapter;
import com.demo.database.DbHelper;
import com.demo.database.Users;
import com.demo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Context mContext;
    private List<Users> list;
    private UsersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {
        //set Actionbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.users));
        }
        mContext = this;
        list = new ArrayList<>();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        binding.recUsersList.setHasFixedSize(true);
        binding.recUsersList.setLayoutManager(manager);
        getUsersList();
        adapter = new UsersListAdapter(mContext, list);
        binding.recUsersList.setAdapter(adapter);

    }

    private void getUsersList() {
        DbHelper helper = new DbHelper(mContext);
        list = helper.getAllUsers();
        Log.e("Size", String.valueOf(list.size()));
    }
}