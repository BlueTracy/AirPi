package com.pi.airpi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.lidroid.xutils.BitmapUtils;
import com.pi.airpi1.R;
import com.pi.airpi.gallery.GalleryManager;
import com.pi.airpi.gallery.PanoInfo;

import java.util.List;

/**
 * Created by LC on 2015/7/29.
 */
public class PanoAdapter extends BaseSwipeAdapter {

    private Context mContext;
    private GalleryManager mGalleryManager;
    private BitmapUtils bitmapUtils;
    public PanoAdapter(Context mContext){
        this.mContext = mContext;

    }
    public PanoAdapter(Context mContext,GalleryManager mGalleryManager){
        this.mContext = mContext;
        this.mGalleryManager = mGalleryManager;
        bitmapUtils = new BitmapUtils(mContext);
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.gallery_item_swipe;
    }

    @Override
    public View generateView(int i, ViewGroup viewGroup) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.gallery_item ,null);
        SwipeLayout swipeLayout = (SwipeLayout)view.findViewById(getSwipeLayoutResourceId(i));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                //YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });
        return view;
    }

    @Override
    public void fillValues(int i, View view) {
        PanoInfo panoInfo = mGalleryManager.getPanoInfo(i);
        TextView panoName = (TextView)view.findViewById(R.id.item_tv_name);
        panoName.setText(panoInfo.getPanoName());
        ImageView panoImage = (ImageView)view.findViewById(R.id.item_iv_pano);
        String url = "https://drscdn.500px.org/photo/3348095/m%3D1170_k%3D1/534eebb0d58885e5690b7a800ca22f84";
        bitmapUtils.display(panoImage,panoInfo.getPanoPath());
    }

    @Override
    public int getCount() {
        if(mGalleryManager==null){
            return 0;
        }else{
            return mGalleryManager.getPanoInfoListCount();
        }

    }

    @Override
    public Object getItem(int i) {
        if(mGalleryManager!=null){
            return mGalleryManager.getPanoInfo(i);
        }else{
            return null;
        }

    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
