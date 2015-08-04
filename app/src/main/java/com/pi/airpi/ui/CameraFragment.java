package com.pi.airpi.ui;

import android.app.Activity;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.pi.airpi1.R;
import com.pi.airpi.camera.PanoCamera;
import com.pi.airpi.gallery.GalleryManager;
import com.pi.airpi.gallery.GalleryService;
import com.pi.airpi.view.StreamView;

import java.io.IOException;


public class CameraFragment extends Fragment {
    private Context mAppContext;
    private GalleryManager mGalleryManager;

    private FrameLayout mPreviewLayout1;
    private FrameLayout mPreviewLayout2;
    private FrameLayout mPreviewLayout3;
    private FrameLayout mPreviewLayout4;

    private StreamView mStreamView1;
    private StreamView mStreamView2;
    private StreamView mStreamView3;
    private StreamView mStreamView4;

    private PanoCamera mCamera;
    private SurfaceView mPreview1;
    private SurfaceView mPreview2;
    private SurfaceView mPreview3;
    private SurfaceView mPreview4;

    private MediaCodec mediaCodec1;
    private MediaCodec mediaCodec2;
    private MediaCodec mediaCodec3;
    private MediaCodec mediaCodec4;

    private MediaFormat mediaFormat;

    private final int mWidth = 1280;
    private final int mHeight = 720;

    private VideoProcessThread mVideoThread;
    private ConnectDeviceThread mConnectThread;

    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtils.d("onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("onCreate");
        mCamera = new PanoCamera();
        /*try {
            mediaCodec1 = MediaCodec.createDecoderByType("video/avc");
            mediaCodec2 = MediaCodec.createDecoderByType("video/avc");
            mediaCodec3 = MediaCodec.createDecoderByType("video/avc");
            mediaCodec4 = MediaCodec.createDecoderByType("video/avc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaFormat = MediaFormat.createVideoFormat("video/avc", mWidth, mHeight);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.d("onCreateView");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        mAppContext = inflater.getContext().getApplicationContext();
        mStreamView1 = new StreamView(mAppContext);
        mStreamView2 = new StreamView(mAppContext);
        mStreamView3 = new StreamView(mAppContext);
        mStreamView4 = new StreamView(mAppContext);
        mPreviewLayout1 = (FrameLayout) view.findViewById(R.id.layout_preview1);
        mPreviewLayout2 = (FrameLayout) view.findViewById(R.id.layout_preview2);
        mPreviewLayout3 = (FrameLayout) view.findViewById(R.id.layout_preview3);
        mPreviewLayout4 = (FrameLayout) view.findViewById(R.id.layout_preview4);

        mPreviewLayout1.addView(mStreamView1);
        mPreviewLayout2.addView(mStreamView2);
        mPreviewLayout3.addView(mStreamView3);
        mPreviewLayout4.addView(mStreamView4);
        mStreamView1.setDisplayMode(StreamView.SIZE_BEST_FIT);
        mStreamView2.setDisplayMode(StreamView.SIZE_BEST_FIT);
        mStreamView3.setDisplayMode(StreamView.SIZE_BEST_FIT);
        mStreamView4.setDisplayMode(StreamView.SIZE_BEST_FIT);
       /* mPreview1 = (SurfaceView)view.findViewById(R.id.surface_preview1);
        mPreview2 = (SurfaceView)view.findViewById(R.id.surface_preview2);
        mPreview3= (SurfaceView)view.findViewById(R.id.surface_preview3);
        mPreview4 = (SurfaceView)view.findViewById(R.id.surface_preview4);*/

        /*mGalleryManager = GalleryService.getGalleryManager(mAppContext);*/
       /* mCamera = new PanoCamera();
        if(mCamera.connectDevice(mCamera.getConnectIp())){
            if(mCamera.setFormat(PanoCamera.CHD_FMT_MJPEG)){
                if(mCamera.setResolution(mWidth,mHeight)){
                    mCamera.startVideo();
                    mVideoThread = new VideoProcessThread();
                    mVideoThread.start();
                }else{
                    Toast.makeText(this.getActivity(),"设置分辨率失败",Toast.LENGTH_SHORT);
                }
            }else{
                Toast.makeText(this.getActivity(),"设置视频格式失败",Toast.LENGTH_SHORT);
            }

        }else{
            Toast.makeText(this.getActivity(),"相机连接失败",Toast.LENGTH_SHORT);
        }*/

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        mConnectThread = new ConnectDeviceThread();
        mConnectThread.start();
        LogUtils.d("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.d("onDetach");
    }

    class ConnectDeviceThread extends Thread {

        @Override
        public void run() {

            if(mCamera.connectDevice(mCamera.getConnectIp())){
                if(mCamera.setFormat(PanoCamera.CHD_FMT_MJPEG)){
                    if(mCamera.setResolution(mWidth,mHeight)){
                        mCamera.startVideo();
                        mVideoThread = new VideoProcessThread();
                        mVideoThread.start();
                    }else{
                       LogUtils.e("设置分辨率失败");
                    }
                }else{
                    LogUtils.e("设置视频格式失败");
                    //Toast.makeText(this.getActivity(),"设置视频格式失败",Toast.LENGTH_SHORT);
                }

            }else{
                LogUtils.e("相机连接失败");
                //Toast.makeText(this.getActivity(),"相机连接失败",Toast.LENGTH_SHORT);
            }
        }
    }

    class VideoProcessThread extends Thread {
        @Override
        public void run() {
            while (mCamera.videoStarted()) {
                mStreamView1.showVideoFrame(mCamera.getCameraData(1));
                mStreamView2.showVideoFrame(mCamera.getCameraData(2));
                mStreamView3.showVideoFrame(mCamera.getCameraData(3));
                mStreamView4.showVideoFrame(mCamera.getCameraData(4));
            }
        }
    }
}
