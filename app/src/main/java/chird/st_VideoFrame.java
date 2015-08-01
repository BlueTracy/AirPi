package chird;

public class st_VideoFrame 
{
	/* 格式 format */
	public static final int CHD_FMT_YUYV 	= 0X01;
	public static final int CHD_FMT_MJPEG 	= 0x02;
	public static final int CHD_FMT_H264	= 0x03;
	
	
	public static int bexist	= 0;	// 摄像头是否存在
	
	public static int format	= 0;	// 格式
	
	public static int width		= 0;	// 宽
	public static int height	= 0;	// 高
	
	public static int fps		= 0;	// 实际帧数
	public static int BPS		= 0;	// 传输速率
	public static int timestamp	= 0;	// 时间戳
	public static int datalen	= 0;	// 一帧图像大小
	
}
