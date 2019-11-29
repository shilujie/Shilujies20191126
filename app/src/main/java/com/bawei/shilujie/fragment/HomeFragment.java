package com.bawei.shilujie.fragment;
/*
 *@auther:史陆杰
 *@Date: 2019/11/26
 *@Time:14:14
 *@Description:${DESCRIPTION}
 **/


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.bawei.shilujie.NetUtil;
import com.bawei.shilujie.R;
import com.bawei.shilujie.SecesActivity;
import com.bawei.shilujie.adapter.MyAdapter;
import com.bawei.shilujie.base.BaseFragment;
import com.bawei.shilujie.bean.Bean;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private XListView lv;
    private LinearLayout ll;
    private int page = 1;
    List<Bean.ListdataBean> list = new ArrayList<>();
    @Override
    protected void initView(View inflate) {
        ll = inflate.findViewById(R.id.ll);
        lv = inflate.findViewById(R.id.lv);
        lv.setPullLoadEnable(true);
        lv.setPullRefreshEnable(true);
        lv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        //点击跳转
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), SecesActivity.class);
                intent.putExtra("key","https://www.thepaper.cn/newsDetail_forward_4923534");
                startActivity(intent);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        if (NetUtil.getInstance().hasNet(getActivity())){
            ll.setVisibility(View.INVISIBLE);
            lv.setVisibility(View.VISIBLE);
            String url = "";
            if (page == 1){
                url = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
            }else if (page == 2){
                url = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai2.json";
            }else {
                url = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai3.json";
            }
            NetUtil.getInstance().get(url, new NetUtil.MyCallBack() {
                @Override
                public void doGet(String httpUrl) {
                    Bean bean = new Gson().fromJson(httpUrl, Bean.class);
                    List<Bean.ListdataBean> listdata = bean.getListdata();
                    list.addAll(listdata);
                    lv.setAdapter(new MyAdapter(list));
                }
            });
        }else {
            //没网显示图片
            ll.setVisibility(View.VISIBLE);
            lv.setVisibility(View.INVISIBLE);
        }
    }
}
