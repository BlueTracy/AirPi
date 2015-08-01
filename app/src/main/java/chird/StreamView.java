package chird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class StreamView extends View{
	private static final String ALBUM_PATH = null;
	private Paint m_Paint=null;                 // 画图paint
	private Bitmap bitmap = null;				

	private int x	  	= 0;
	private int y	    = 0;
	private int width 	= 800;					// 要显示的视频大小
	private int height 	= 480;
	private static int m_ScreenWidth = 0;		// 手机屏幕大小
	private static int m_ScreenHeight = 0;
	
	public StreamView(Context context) {
		super(context);
	}

	public StreamView(Context context, AttributeSet attrs) {
		super(context, attrs);
		m_Paint = new Paint();
	}

	/* 手机屏幕大小 */
	public void SetActivity(int width, int height){
		m_ScreenWidth  = width;
		m_ScreenHeight = height;
		
	//	bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);	
		
		if (bitmap == null)		return ;
		
		x = (m_ScreenWidth - width) 	/ 2;
		y = (m_ScreenHeight - height) 	/ 2;

		PicDraw(canvas, bitmap, m_Paint, x, y, x+width, y+height);		
	}
	
	/* 设置全屏显示 */
	public void ShowFullScreen(){
		width  = m_ScreenWidth;
		height = m_ScreenHeight;		
	}

	/* 设置要显示图片界面的大小 */
	public void ShowSetResolu(int w, int h){
		if (w > m_ScreenWidth){
			w = m_ScreenWidth;
		}else{
			width  = w;
		}
		
		if (h > m_ScreenHeight){
			height = m_ScreenHeight;
		}else{
			height = h;
		}
	}
	
	public void ShowBitmapFrame(Bitmap data){
		bitmap = data;
		postInvalidate();
	}

	static int PicDraw(Canvas canvas,Bitmap image,Paint paint_back,int x, int y, int width, int height){		
		/* 获取图片大小 */
		int iImageWidth = image.getWidth();
	    int iImageHeight = image.getHeight();
	    /* 加载图片大小 */
		Rect backRectFrom = new Rect(0, 0, iImageWidth, iImageHeight);
		/* 显示的界面大小 */
		Rect backRectTo = new Rect(x , y, width, height);
		/* 显示图片 */
		canvas.drawBitmap(image, backRectFrom, backRectTo, paint_back);

		return 0;
	}
		
}
