package com.pi.airpi.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.lidroid.xutils.util.LogUtils;

import java.util.Queue;
import java.util.LinkedList;

import java.util.Timer;
import java.util.TimerTask;

import chird.chd_Return;
import chird.chd_wmp_apis;
import chird.st_SearchInfo;
import chird.st_VideoFrame;

/**
 * Created by LC on 2015/7/31.
 */
public class PanoCamera {

    public static final int HANDLER_VIDEO = 1;
    public static final int HANDLER_PICTURE = 2;
    public static final int HANDLER_AUDIO = 3;
    public static final int HANDLER_SERIAL = 4;

    public static final int CHD_FMT_YUYV 	= 0X01;
    public static final int CHD_FMT_MJPEG 	= 0x02;
    public static final int CHD_FMT_H264	= 0x03;
    private chd_wmp_apis chdSdk;
    private st_SearchInfo deviceInfo;

    private long mHandle = -1;

    private ChdPollThread mPollThread;

    byte[] videoBuffer = new byte[3 * 1024 * 1024];
    byte[] picBuffer = new byte[3*1024*1024];

    st_VideoFrame videoFrame = new st_VideoFrame();
    st_VideoFrame picVideoFrame = new st_VideoFrame();

    Queue<Bitmap> firstCameraQueue;
    Queue<Bitmap> secondCameraQueue;
    Queue<Bitmap> thirdCameraQueue;
    Queue<Bitmap> fourthCameraQueue;

    Queue<Bitmap>[] cameraQueues;

    BitmapFactory.Options opts = new BitmapFactory.Options();


    private String[] mIpAddr = new String[50];
    private String mConnectIp = "192.168.100.254";

    private Timer mDevScanTimer;
    private Handler mDevScanTimerHandler;
    private Bitmap mBitmap = null;
    private boolean mThreadRun = false;

    private boolean mConnectFlag = false;

    private boolean mVideoFlag = false;

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case HANDLER_VIDEO:
                    break;
                case HANDLER_PICTURE:
                    break;
                case HANDLER_AUDIO:
                    break;
                case HANDLER_SERIAL:
                    break;
                default:
                    break;

            }
        }
    };

    public PanoCamera(){
        LogUtils.d("camera init");
        chdSdk = new chd_wmp_apis();
        deviceInfo = new st_SearchInfo();
      firstCameraQueue = new LinkedList<Bitmap>();
         secondCameraQueue = new LinkedList<Bitmap>();
        thirdCameraQueue = new LinkedList<Bitmap>();
        fourthCameraQueue = new LinkedList<Bitmap>();
         cameraQueues = new LinkedList[5];
        cameraQueues[1] = firstCameraQueue;
        cameraQueues[2] = secondCameraQueue;
        cameraQueues[3] = thirdCameraQueue;
        cameraQueues[4] = fourthCameraQueue;

    }

    public boolean videoStarted(){
        return mVideoFlag;
    }


    /* 搜索设备定时器启动函数 */
    private void scanDeviceTimerStart() {
        mDevScanTimer = new Timer();
        mDevScanTimer.schedule(new TimerTask() {
            public void run() {
                // 发送设备扫描信号
                mDevScanTimerHandler.sendMessage(new Message());
            }
        }, 1000, 3000);
    }


    //扫描设备
    public boolean scanDevice(){
        boolean  result = true;
        int retCode = chdSdk.CHD_WMP_ScanDevice_Init(3);
        if(retCode==chd_Return.CHD_RET_SUCCESS){
            scanDeviceTimerStart();
            mDevScanTimerHandler = new Handler() {
                public void handleMessage(Message msg) {
                    // 获取设备列表
                    st_SearchInfo dev = new st_SearchInfo();
                    int num = chdSdk.CHD_WMP_Scan_GetDeviceInfo(deviceInfo);
                    if(num==chd_Return.CHD_RET_FAILED){
                        LogUtils.e("CHD_WMP_Scan_GetDeviceInfo error");
                    }else if(num>0){
                        for(int i= 0 ;i<num;i++){
                            mIpAddr[i] = deviceInfo.ipaddr[i];
                        }
                        mConnectIp = mIpAddr[0];
                        stopScanDevice();
                    }else{
                    }

                }
            };
        }else{
            result = false;
        }
        return result;
    }

    //停止扫描设备
    public boolean stopScanDevice(){
        boolean result = true;
        mDevScanTimer.cancel();
        mDevScanTimer = null;
        int retCode = chdSdk.CHD_WMP_ScanDevice_UnInit();
        if(retCode!=chd_Return.CHD_RET_SUCCESS){
           result = false;
        }
        return result;
    }
    //连接设备
    public boolean connectDevice(String ipAddress){
        boolean result = true;
        if (mConnectFlag == true) {
            chdSdk.CHD_WMP_Disconnect(mHandle);
            mHandle = -1;
        }

        mHandle = chdSdk.CHD_WMP_ConnectDevice(ipAddress);
        if (mHandle >= 0) {
            mConnectFlag = true;
            mPollThread = new ChdPollThread();
            mThreadRun = true;
            mPollThread.start();
        } else {
            mConnectFlag = false;
            result = false;
        }

        return result;
    }
    //设置分辨率
    public boolean setResolution(final int width,final int height){
        boolean result = true;
        if(mConnectFlag){
            int retCode = chdSdk.CHD_WMP_Video_SetResolu(mHandle,width,height);
            if(retCode!=chd_Return.CHD_RET_SUCCESS){
                LogUtils.e("设置分辨率出错："+retCode);
                result = false;
            }
        }else{
            LogUtils.e("设备未连接");
            return false;
        }
        return result;
    }
    //设置视频格式
    public boolean setFormat(int format){
        boolean result = true;
        if(mConnectFlag){
            int retCode = chdSdk.CHD_WMP_Video_SetFormat(mHandle, format);
            if(retCode!=chd_Return.CHD_RET_SUCCESS){
                LogUtils.e("设置视频格式出错："+retCode);
                result = false;
            }
        }else{
            LogUtils.e("设备未连接");
            return false;
        }
        return result;
    }
    //开启视频流
    public boolean startVideo(){
        boolean result = true;
        if(mVideoFlag){
        }else{
            int ret = chdSdk.CHD_WMP_Video_Begin(mHandle);
            if (ret != 0) {
                result =false;
            } else {
                mVideoFlag = true;

            }
        }
        return result;
    }
    //停止视频流
    public void stopVideo(){
        if(mVideoFlag){
            chdSdk.CHD_WMP_Video_End(mHandle);
            mVideoFlag = false;
        }

    }

    public Bitmap getCameraData(int index){
        return cameraQueues[index].peek();
    }

    public String getConnectIp(){
        return mConnectIp;
    }

    // 数据监听线程
    class ChdPollThread extends Thread {
        int lasttime = 0, width = 0, height = 0;
        int num = 0, ret, type = 0;
        @Override
        public void run() {
            while (mThreadRun) {
               if (mHandle < 0) {
                    continue;
                }
                // 数据监听
                ret = chdSdk.CHD_WMP_Poll(mHandle, 2, 0);
                if (ret < 0) {
                    continue;
                }
                //视频数据
                if(ret == chdSdk.CHD_POLL_TYPE_VIDEO){
                    synchronized (videoFrame){
                        chdSdk.CHD_WMP_Video_RequestVideoData(mHandle,videoFrame,videoBuffer);
                       opts.inSampleSize = 2;
                        mBitmap  = BitmapFactory.decodeByteArray(videoBuffer, 0, videoFrame.datalen,
                                opts);
                       //chdSdk.CHD_WMP_Decode_MjpegToBitmap(mHandle,videoFrame.width,videoFrame.height,videoFrame.datalen,mBitmap,videoBuffer);
                        if(videoFrame.bexist>0){
                            cameraQueues[videoFrame.bexist].offer(mBitmap);
                        }


                    }

                }
                // 图像数据
                if (ret == chdSdk.CHD_POLL_TYPE_PICTURE){
                    synchronized(picVideoFrame){
                        ret = chdSdk.CHD_WMP_Video_RequestPicData(mHandle, picVideoFrame, picBuffer);
                        if (ret < 0){
                            continue;
                        }
                        if (picVideoFrame.width > 4096){
                            continue;
                        }
                        Message msg = new Message();
                        msg.what = chd_wmp_apis.CHD_POLL_TYPE_PICTURE;
                        mHandler.sendMessage(msg);
                    }

                }
                //音频数据
                if(ret == chdSdk.CHD_POLL_TYPE_AUDIO){

                }
                //串口数据
                if(ret==chdSdk.CHD_POLL_TYPE_SERIAL){

                }
            }
        }
    }
}
