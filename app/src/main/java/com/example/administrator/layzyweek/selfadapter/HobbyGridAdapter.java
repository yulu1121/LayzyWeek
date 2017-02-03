package com.example.administrator.layzyweek.selfadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.example.administrator.layzyweek.R;

import java.util.List;

/**
 *此方法并未调用
 * Created by Administrator on 2017/1/17.
 */

public class HobbyGridAdapter extends BaseAdapter {
    private List<CheckBox> chkList;
    private LayoutInflater inflater;
    private Context context;
    public HobbyGridAdapter(Context context,List<CheckBox> drawableList){
            this.context = context;
            this.chkList = drawableList;
            inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return null==chkList?0:chkList.size();
    }

    @Override
    public Object getItem(int position) {
        return chkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view  = inflater.inflate(R.layout.hooby_grid,parent,false);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.hobby_check);
        chkList.add(checkBox);
        return view;
    }
}
