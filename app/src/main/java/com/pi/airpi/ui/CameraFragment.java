package com.pi.airpi.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.pi.airpi.R;
import com.pi.airpi.gallery.GalleryManager;
import com.pi.airpi.gallery.GalleryService;


public class CameraFragment extends Fragment {
    private Context mAppContext;
    private GalleryManager mGalleryManager;

    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        mAppContext = inflater.getContext().getApplicationContext();
        mGalleryManager = GalleryService.getGalleryManager(mAppContext);

        Button btn = (Button)view.findViewById(R.id.camera_add_pic);
        Button btn1 = (Button)view.findViewById(R.id.camera_del_pic);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mGalleryManager.addPanoInfo("测试", "https://drscdn.500px.org/photo/3348095/m%3D1170_k%3D1/534eebb0d58885e5690b7a800ca22f84", 0);
                }catch (DbException e) {
                    LogUtils.e(e.getMessage(), e);
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mGalleryManager.removePanoInfo(0);
                }catch (DbException e) {
                    LogUtils.e(e.getMessage(), e);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
