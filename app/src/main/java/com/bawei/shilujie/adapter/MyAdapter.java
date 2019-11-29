package com.bawei.shilujie.adapter;
/*
 *@auther:史陆杰
 *@Date: 2019/11/26
 *@Time:14:28
 *@Description:${DESCRIPTION}
 **/


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.shilujie.NetUtil;
import com.bawei.shilujie.R;
import com.bawei.shilujie.bean.Bean;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Bean.ListdataBean> list;

    public MyAdapter(List<Bean.ListdataBean> list) {

        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = list.get(position).getItemType();
        if (itemType == 1){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoulder houlder = null;
        int itemViewType = getItemViewType(i);
        if (view == null){
            if (itemViewType == 0){
                view = View.inflate(viewGroup.getContext(), R.layout.kaozuo,null);
            }else {
                view = View.inflate(viewGroup.getContext(), R.layout.xiangshang,null);
            }
            houlder = new ViewHoulder();
            houlder.images = view.findViewById(R.id.images);
            houlder.name = view.findViewById(R.id.name);
            view.setTag(houlder);
        }else {
            houlder = (ViewHoulder) view.getTag();
        }

        houlder.name.setText(list.get(i).getContent());
        houlder.images.setScaleType(ImageView.ScaleType.FIT_XY);
        NetUtil.getInstance().getPhoto(list.get(i).getImageurl(),houlder.images);
        return view;
    }
    class ViewHoulder{
        ImageView images;
        TextView name;
    }
}
