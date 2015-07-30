package com.pi.airpi.gallery;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LC on 2015/7/29.
 */
public class GalleryManager {

    private List<PanoInfo> mPanoInfoList;
    private Context mContext;
    private DbUtils db;

    public GalleryManager(Context appContext){
        mContext = appContext;
        db = DbUtils.create(mContext);
        try {
            mPanoInfoList = db.findAll(Selector.from(PanoInfo.class));

        }catch(DbException e){
            LogUtils.e(e.getMessage(),e);
        }
        if(mPanoInfoList==null){
            mPanoInfoList = new ArrayList<PanoInfo>();
        }
    }
    public int getPanoInfoListCount(){
        return mPanoInfoList.size();
    }
    public PanoInfo getPanoInfo(int index){
        return mPanoInfoList.get(index);
    }

    public void addPanoInfo(String name,String path,int status) throws DbException{
        final PanoInfo panoInfo = new PanoInfo();
        panoInfo.setPanoName(name);
        panoInfo.setPanoPath(path);
        panoInfo.setStatus(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String createdTime = dateFormat.format(new Date());
        panoInfo.setCreatedTime(createdTime);
        db.saveBindingId(panoInfo);
    }

    public void stitchPano(int index){

    }

    public void stitchPano(PanoInfo panoInfo){

    }

    public void stopPano(int index) throws DbException{
        PanoInfo panoInfo = mPanoInfoList.get(index);
        stopPano(panoInfo);
    }

    public void stopPano(PanoInfo panoInfo) throws DbException{
        db.saveOrUpdate(panoInfo);
    }


    public void removePanoInfo(int index) throws DbException{
        PanoInfo panoInfo = mPanoInfoList.get(index);
        removePanoInfo(panoInfo);
    }

    public void removePanoInfo(PanoInfo panoInfo) throws DbException{
        mPanoInfoList.remove(panoInfo);
        db.delete(panoInfo);
    }
}
