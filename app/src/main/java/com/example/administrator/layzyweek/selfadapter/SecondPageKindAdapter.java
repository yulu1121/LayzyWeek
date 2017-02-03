package com.example.administrator.layzyweek.selfadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.activities.SecondCategoryActivity;
import com.example.administrator.layzyweek.entries.SecondKindEntry;
import com.example.administrator.layzyweek.utils.ImageLoader;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/21.
 */

public class SecondPageKindAdapter extends BaseAdapter {
    private List<SecondKindEntry.ResultBean> resultBeen;
    private Context context;
    private LayoutInflater inflater;
    public SecondPageKindAdapter(Context context,List<SecondKindEntry.ResultBean> resultBeen){
        this.context = context;
        this.resultBeen = resultBeen;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return resultBeen==null?0:resultBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return resultBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
      final ViewHolder viewHolder;
        if(view==null){
            view = inflater.inflate(R.layout.second_grid_item,parent,false);
            viewHolder = new ViewHolder(view);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        final SecondKindEntry.ResultBean bean = resultBeen.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondCategoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("category",bean.getName());
                bundle.putString("categoryName",bean.getCn_name());
                intent.putExtra("categoryBundle",bundle);
                context.startActivity(intent);
            }
        });
        String urling = bean.getIcon_view();
        viewHolder.imageView.setTag(urling);
        ImageLoader.loadImage(urling,300,150,new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(viewHolder.imageView.getTag())){
                    viewHolder.imageView.setImageBitmap(bitMap);
                }
            }
        });
        viewHolder.textView.setText(bean.getCn_name());
        return view;
    }
   class ViewHolder{
       ImageView imageView;
       TextView textView;
       public ViewHolder(View view){
           imageView = (ImageView) view.findViewById(R.id.second_item_image);
           textView = (TextView)view.findViewById(R.id.second_item_textView);
           view.setTag(this);
       }
   }
}
