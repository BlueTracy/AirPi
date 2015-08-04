package com.pi.airpi.view;

/**
 * Created by LC on 2015/7/17.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class StreamView extends SurfaceView implements SurfaceHolder.Callback {
    public final static int POSITION_UPPER_LEFT = 9;
    public final static int POSITION_UPPER_RIGHT = 3;
    public final static int POSITION_LOWER_LEFT = 12;
    public final static int POSITION_LOWER_RIGHT = 6;

    public final static int SIZE_STANDARD = 1;
    public final static int SIZE_BEST_FIT = 4;
    public final static int SIZE_FULLSCREEN = 8;

    private boolean surfaceDone = false;

    private int dispWidth;
    private int dispHeight;
    private int displayMode;

    private SurfaceHolder mSurfaceHolder;

    Bitmap bm = null;
    BitmapFactory.Options opts = new BitmapFactory.Options();
    //opts.= 3;

    private Rect destRect(int bmw, int bmh) {
        int tempx;
        int tempy;
        if (displayMode == StreamView.SIZE_STANDARD) {
            tempx = (dispWidth / 2) - (bmw / 2);
            tempy = (dispHeight / 2) - (bmh / 2);
            return new Rect(tempx, tempy, bmw + tempx, bmh + tempy);
        }
        if (displayMode == StreamView.SIZE_BEST_FIT) {
            float bmasp = (float) bmw / (float) bmh;
            bmw = dispWidth;
            bmh = (int) (dispWidth / bmasp);
            if (bmh > dispHeight) {
                bmh = dispHeight;
                bmw = (int) (dispHeight * bmasp);
            }
            tempx = (dispWidth / 2) - (bmw / 2);
            tempy = (dispHeight / 2) - (bmh / 2);
            return new Rect(tempx, tempy, bmw + tempx, bmh + tempy);
        }
        if (displayMode == StreamView.SIZE_FULLSCREEN){
            return new Rect(0, 0, dispWidth, dispHeight);
        }

        return null;
    }

    public void setSurfaceSize(int width, int height) {
        synchronized (mSurfaceHolder) {
            dispWidth = width;
            dispHeight = height;
        }
    }

    private void init(Context context) {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        setFocusable(true);
        displayMode = StreamView.SIZE_STANDARD;
        dispWidth = getWidth();
        dispHeight = getHeight();
    }

    public void showVideoFrame(byte[] FrameData,int dataLength) {
        Rect destRect;
        Canvas c = null;
        Paint p = new Paint();
        if (surfaceDone) {
            try {
                c = mSurfaceHolder.lockCanvas();
                synchronized (mSurfaceHolder) {
                    try {
                        bm = null;
                        opts.inSampleSize = 2;
                        bm = BitmapFactory.decodeByteArray(FrameData, 0, dataLength,
                                opts);
                        destRect = destRect(bm.getWidth(), bm.getHeight());
                        c.drawColor(Color.BLACK);
                        c.drawBitmap(bm, null, destRect, p);

                    } catch (Exception e) {
                    }
                }
            } finally {
                if (c != null){
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }

            }
        }

    }

    public void showVideoFrame(Bitmap bitmap) {
        Rect destRect;
        Canvas c = null;
        Paint p = new Paint();
        if (surfaceDone) {
            try {
                c = mSurfaceHolder.lockCanvas();
                synchronized (mSurfaceHolder) {
                    try {
                        opts.inSampleSize = 2;
                        destRect = destRect(bitmap.getWidth(), bitmap.getHeight());
                        c.drawColor(Color.BLACK);
                        c.drawBitmap(bitmap, null, destRect, p);
                        bitmap = null;
                    } catch (Exception e) {
                    }
                }
            } finally {
                if (c != null){
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }

            }
        }

    }

    public StreamView(Context context) {
        super(context);
        init(context);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        setSurfaceSize(width, height);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceDone = true;

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceDone = false;

    }

    public void setDisplayMode(int s) {
        displayMode = s;
    }

}