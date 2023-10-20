package com.example.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<User> userList;

    public UserAdapter(Context context, int layout, ArrayList<User> congViecList) {
        this.context = context;
        this.layout = layout;
        this.userList = congViecList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtUsername, txtPassword;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtUsername= (TextView) view.findViewById(R.id.tv1);
            holder.txtPassword= (TextView) view.findViewById(R.id.tv2);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        User user = userList.get(i);
        holder.txtUsername.setText(user.getUsername());
        holder.txtPassword.setText(user.getPassword());

        return view;
    }
}
