package com.mazouri.retrofit2tutorial.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mazouri.retrofit2tutorial.R;
import com.mazouri.retrofit2tutorial.models.Item;

import java.util.List;

/**
 * Created by wangdongdong on 16/9/6.
 */
public class UserAdapter extends BaseAdapter {

    private List<Item> users ;
    private Context context ;

    public UserAdapter (Context ctx, List<Item> items) {
        super();
        this.context = ctx ;
        this.users = items ;
    }

    @Override
    public int getCount() {
        return this.users.size();
    }

    @Override
    public long getItemId(int i) {
        return 0 ;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.cell, viewGroup, false);
        TextView tv = (TextView) v.findViewById(R.id.textview);
        Item user = users.get(i);
        tv.setText(user.getLogin());
        Log.d("Adapter", user.getLogin());
        return v;
    }
}
