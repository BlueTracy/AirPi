package chird;

public class st_VideoParamInfo {
	
	/* 格式 format */
	public static final int CHD_FMT_YUYV 	= 0X01;
	public static final int CHD_FMT_MJPEG 	= 0x02;
	public static final int CHD_FMT_H264	= 0x03;
	
	public static int format 	= 0;	
	public static int width 	= 0;	
	public static int height 	= 0;
	public static int fps 		= 0;
	public static int maxfps 	= 0;
	
	
	/* 获取当前设置的格式 返回String */
	public String GetFormatString(){	
		switch (format){
		case CHD_FMT_YUYV	: return "YUYV";
		case CHD_FMT_MJPEG	: return "JPEG";
		case CHD_FMT_H264	: return "H264";
		default				: return "other";
		}
	}
	
	
	/* 获取当前设置的分辨率 返回String*/
	public String GetResoluString(){
		return String.valueOf(width) + "x" + String.valueOf(height);
	}
}
