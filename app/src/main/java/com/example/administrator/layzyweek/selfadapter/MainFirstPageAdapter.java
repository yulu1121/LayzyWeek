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
import com.example.administrator.layzyweek.activities.FirstFormationActivity;
import com.example.administrator.layzyweek.entries.FirstPage;
import com.example.administrator.layzyweek.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 *
 * Created by Administrator on 2017/1/18.
 */

public class MainFirstPageAdapter extends BaseAdapter {
    private Context context;
    private List<FirstPage.ResultBean> mlist;
    private LayoutInflater inflater;
    public MainFirstPageAdapter(Context context,List<FirstPage.ResultBean> list){
        this.context = context;
        this.mlist = list;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return null==mlist?0:mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mlist.get(position).getLeo_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        final ViewHolder viewHolder;
        if(null==view){
            view = inflater.inflate(R.layout.main_item_layout,parent,false);
            viewHolder = new ViewHolder(view);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final FirstPage.ResultBean bean = mlist.get(position);
        //向详情界面传递数据
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, FirstFormationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("leo_id",bean.getLeo_id());
                intent.putExtra("formation",bundle);
                context.startActivity(intent);
            }
        });
        viewHolder.mainTitle.setText(bean.getTitle());
        viewHolder.mainCompany.setText(bean.getPoi_name()+"·"+bean.getCategory());
        viewHolder.mainOverTime.setText(bean.getTime_info());
        viewHolder.clicksMain.setText(String.valueOf(bean.getCollected_num())+"人收藏");
        viewHolder.priceMain.setText("￥"+String.valueOf(bean.getPrice()));
        String imageUrlOne = bean.getFront_cover_image_list().get(0);
        viewHolder.mainImageView.setTag(imageUrlOne);
        ImageLoader.loadImage(imageUrlOne,450,150,new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(viewHolder.mainImageView.getTag())){
                    viewHolder.mainImageView.setImageBitmap(bitMap);
                }
            }
        });
        return view;
    }
    class ViewHolder{
         @BindView(R.id.main_backgroud)
         ImageView mainImageView;
         @BindView(R.id.main_title)
         TextView mainTitle;
         @BindView(R.id.main_company)
         TextView mainCompany;
         @BindView(R.id.overtime_main)
         TextView mainOverTime;
         @BindView(R.id.clicks_main)
         TextView clicksMain;
         @BindView(R.id.price_main)
         TextView priceMain;
        ViewHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
