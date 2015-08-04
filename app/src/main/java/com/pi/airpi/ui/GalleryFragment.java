package com.pi.airpi.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.BitmapUtils;
import com.pi.airpi1.R;
import com.pi.airpi.adapter.PanoAdapter;
import com.pi.airpi.gallery.GalleryManager;
import com.pi.airpi.gallery.GalleryService;
import com.pi.airpi.gallery.PanoInfo;
import com.pi.airpi.utils.BitmapHelp;

import java.util.List;


public class GalleryFragment extends Fragment {
    private PanoAdapter mPanoAdapter;
    private List<PanoInfo> mPanoInfoList;

    private ListView mListView;
    private Context mAppContext;
    private GalleryManager mGalleryManager;
    public static  BitmapUtils bitmapUtils;


    public GalleryFragment() {
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
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        mAppContext = inflater.getContext().getApplicationContext();
        mGalleryManager = GalleryService.getGalleryManager(mAppContext);
        mPanoAdapter = new PanoAdapter(mAppContext,mGalleryManager);
        bitmapUtils = BitmapHelp.getBitmapUtils(this.getActivity().getApplicationContext());
        mListView = (ListView)view.findViewById(R.id.gallery_listview);
        mListView.setAdapter(mPanoAdapter);
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        mPanoAdapter.notifyDataSetChanged();
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
