package com.pi.airpi.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pi.airpi1.R;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    //定义3个Fragment的对象
    private GalleryFragment mGalleryFragment;
    private CameraFragment mCameraFragment;
    private SettingFragment mSettingFragment;

    //帧布局对象,就是用来存放Fragment的容器
    private FrameLayout mFrameLayout;
    //定义底部导航栏的三个布局
    private RelativeLayout mGalleryLayout;
    private RelativeLayout mCameraLayout;
    private RelativeLayout mSettingsLayout;
    //定义底部导航栏中的ImageView与TextView
    private ImageView mGalleryImage;
    private ImageView mCameraImage;
    private ImageView mSettingsImage;
    private TextView mGalleryText;
    private TextView mSettingsText;
    private TextView mCameraText;
    //定义要用的颜色值
    private int whiteColor = 0xFFFFFFFF;
    private int grayColor = 0xFF7597B3;
    private int blueColor =0xFF0AB2FB;
    //定义FragmentManager对象
    FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        initViews();
        setChioceItem(0);
    }

    //完成组件的初始化
    private void initViews()
    {
        mGalleryImage = (ImageView) findViewById(R.id.gallery_image);
        mCameraImage = (ImageView) findViewById(R.id.camera_image);
        mSettingsImage = (ImageView) findViewById(R.id.setting_image);
        mGalleryText = (TextView) findViewById(R.id.gallery_text);
        mCameraText = (TextView) findViewById(R.id.camera_text);
        mSettingsText = (TextView) findViewById(R.id.setting_text);
        mGalleryLayout = (RelativeLayout) findViewById(R.id.gallery_layout);
        mCameraLayout = (RelativeLayout) findViewById(R.id.camera_layout);
        mSettingsLayout = (RelativeLayout) findViewById(R.id.setting_layout);
        mGalleryLayout.setOnClickListener(this);
        mCameraLayout.setOnClickListener(this);
        mSettingsLayout.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gallery_layout:
                setChioceItem(0);
                break;
            case R.id.camera_layout:
                setChioceItem(1);
                break;
            case R.id.setting_layout:
                setChioceItem(2);
                break;
            default:
                break;
        }
    }

    //定义一个选中一个item后的处理
    public void setChioceItem(int index)
    {
        //重置选项+隐藏所有Fragment
        FragmentTransaction transaction = fManager.beginTransaction();
        clearChioce();
        hideFragments(transaction);
        switch (index) {
            case 0:
                mGalleryImage.setImageResource(R.mipmap.ic_tabbar_gallery_pressed);
                mGalleryText.setTextColor(blueColor);
                if (mGalleryFragment == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    mGalleryFragment = new GalleryFragment();
                    transaction.add(R.id.content, mGalleryFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mGalleryFragment);
                }
                break;

            case 1:
                mCameraImage.setImageResource(R.mipmap.ic_tabbar_camera_pressed);
                mCameraText.setTextColor(blueColor);
                if (mCameraFragment == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    mCameraFragment = new CameraFragment();
                    transaction.add(R.id.content, mCameraFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mCameraFragment);
                }
                break;

            case 2:
                mSettingsImage.setImageResource(R.mipmap.ic_tabbar_settings_pressed);
                mSettingsText.setTextColor(blueColor);
                if (mSettingFragment == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    mSettingFragment = new SettingFragment();
                    transaction.add(R.id.content, mSettingFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(mSettingFragment);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (mGalleryFragment != null) {
            transaction.hide(mGalleryFragment);
        }
        if (mCameraFragment != null) {
            transaction.hide(mCameraFragment);
        }
        if (mSettingFragment != null) {
            transaction.hide(mSettingFragment);
        }
    }


    //定义一个重置所有选项的方法
    public void clearChioce()
    {
        mGalleryImage.setImageResource(R.mipmap.ic_tabbar_gallery);
        mGalleryLayout.setBackgroundColor(whiteColor);
        mGalleryText.setTextColor(grayColor);
        mCameraImage.setImageResource(R.mipmap.ic_tabbar_camera);
        mCameraLayout.setBackgroundColor(whiteColor);
        mCameraText.setTextColor(grayColor);
        mSettingsImage.setImageResource(R.mipmap.ic_tabbar_settings);
        mSettingsLayout.setBackgroundColor(whiteColor);
        mSettingsText.setTextColor(grayColor);
    }
}
