package com.demo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.R;
import com.demo.database.Users;
import com.demo.databinding.RawUsersListBinding;

import java.io.File;
import java.util.List;

/**
 * Created by Shabana Khatri on 02-04-2018.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {
    private Context mContext;
    private List<Users> list;

    public UsersListAdapter(Context mContext, List<Users> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RawUsersListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.raw_users_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Users user = list.get(position);
        holder.binding.setData(user);
        holder.binding.executePendingBindings();

        File imgFile = new File(list.get(position).getProfilePic());

        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.binding.imgProfilePic.setImageBitmap(myBitmap);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RawUsersListBinding binding;

        public ViewHolder(RawUsersListBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }
    }
}
