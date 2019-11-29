package com.bawei.shilujie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bawei.shilujie.base.BaseActivity;
import com.bawei.shilujie.fragment.HomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ImageView img;
    private TabLayout tab;
    private ViewPager vp;
    private List<String> list;
    private List<Fragment> fragmentList;

    @Override
    protected void initData() {

        //史陆杰月考技能
        list = new ArrayList<>();
        list.add("推荐");
        list.add("视频");
        list.add("实时");
        fragmentList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        HomeFragment homeFragment1 = new HomeFragment();
        HomeFragment homeFragment2 = new HomeFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(homeFragment1);
        fragmentList.add(homeFragment2);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }
        });
        tab.setupWithViewPager(vp);
    }

    @Override
    protected void initView() {
        img = findViewById(R.id.img);
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri data1 = data.getData();
//        Bitmap bitmap = MediaStore.Images.Media.getBitmap(,data1);
//        img.setImageBitmap(bitmap);
    }
}
