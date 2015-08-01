package chird;

public class st_VideoAbilityInfo {
	
	/* 格式 format */
	public static final int CHD_FMT_YUYV 	= 0X01;
	public static final int CHD_FMT_MJPEG 	= 0x02;
	public static final int CHD_FMT_H264	= 0x03;
	
	public static int FormatNum = 0;
	public  	  int [] format = new int[5];
	public static int ResoluNum = 0;
	public        int [] width 	= new int[12];
	public        int [] height = new int[12];
	public        int [] maxfps = new int[12];	
	
	/* 获取当前设置的格式 返回String */
	public String GetFormatString(int format){	
		switch (format){
		case CHD_FMT_YUYV	: return "YUYV";
		case CHD_FMT_MJPEG	: return "JPEG";
		case CHD_FMT_H264	: return "H264";
		default				: return "OTHER";
		}
	}
	
	/* 获取当前设置的分辨率 返回String*/
	public String GetResoluString(int w, int h){
		return String.valueOf(w) + "x" + String.valueOf(h);
	}

}
