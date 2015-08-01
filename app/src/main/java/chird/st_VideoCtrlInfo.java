package chird;

public class st_VideoCtrlInfo {
	
	public static final int CHD_VCTRL_BRIGHTNESS	=	0;	// 亮度
	public static final int CHD_VCTRL_CONTRAST		=	1;	// 对比度
	public static final int CHD_VCTRL_SATURATION	=	2; 	// 饱和
	public static final int CHD_VCTRL_HUE			=	3; 	// 色调
	public static final int CHD_VCTRL_WHITE_BALANCE	=	4;	// 白平衡温度
	public static final int CHD_VCTRL_GAMMA			=	5;	// 伽马
	public static final int CHD_VCTRL_GAIN			=	6;	// 增益
	public static final int CHD_VCTRL_SHARPNESS		=	7;	// 清晰度
	public static final int CHD_VCTRL_BACKLIGHT 	=	8;	// 背光补偿
	public static final int CHD_VCTRL_EXPOSURE 		=	9;	// 曝光值

	// 设置摄像头控制参数参数
	public static int auto_valid;			// 是否支持自动调节（不可设置）
	public static int autoval; 				// 若支持自动调节，是否开启自动调节
	
	public static int val_valid; 			// 设备是否支持type指定的控制参数设置 
	/* 只有 autoval = 0 和 val_valid = 1  时 以下参数设置才会生效  */
	public static int minval; 				// 控制参数最小值 (不可设置)
	public static int curval; 				// 控制参数当前值  (调节基本是设置当前值)
	public static int maxval; 				// 控制参数最大值 (不可设置)
	public static int stepval;				// 控制参数步进值
	public static int defval; 				// 控制参数默认值

	public int vctrl_auto_valid;

}
